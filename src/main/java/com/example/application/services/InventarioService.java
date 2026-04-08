package com.example.application.services;

import com.example.application.data.Inventario;
import com.example.application.data.Movimiento;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

import java.io.File;
import java.util.*;

@Service
public class InventarioService {

    private final ObjectMapper objectMapper;
    private final File archivoJson = new File("src/main/resources/Inventario.json");

    private TreeMap<Long, Inventario> catalogoMain = new TreeMap<>();

    private TreeSet<Inventario> rankingBajoStock = new TreeSet<>(
            Comparator.comparingDouble(Inventario::getStock)
                    .thenComparing(Inventario::getCodigo));

    private ArrayList<Movimiento> historialMovimientos = new ArrayList<>();

    public InventarioService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        if (archivoJson.exists()) {
            try {
                List<Inventario> productosGuardados = objectMapper.readValue(archivoJson,
                        new TypeReference<List<Inventario>>() {
                        });
                for (Inventario p : productosGuardados) {
                    catalogoMain.put(p.getCodigo(), p);
                    rankingBajoStock.add(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            guardarDatos();
        }
    }

    private void guardarDatos() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivoJson, catalogoMain.values());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- MÉTODOS DE OPERACIÓN ---

    public void registrarNuevoProducto(Inventario producto) {
        // Aseguramos que no exista un producto con el mismo código
        if (!catalogoMain.containsKey(producto.getCodigo())) {
            catalogoMain.put(producto.getCodigo(), producto);
            rankingBajoStock.add(producto);

            // registrarMovimiento(producto.getCodigo(), "ENTRADA", producto.getStock(),
            // "Registro Inicial");
            guardarDatos();
        }
    }

    public void actualizarStock(Long codigo, int variacion, String motivo) {
        Inventario producto = catalogoMain.get(codigo);

        if (producto != null) {
            // ¡IMPORTANTE! Para actualizar un objeto en un TreeSet, debemos sacarlo y
            // volverlo a meter
            // Si le cambias el stock mientras está adentro, el árbol se rompe.
            rankingBajoStock.remove(producto);

            int nuevoStock = producto.getStock() + variacion;
            producto.setStock(Math.max(0, nuevoStock));

            rankingBajoStock.add(producto); // Lo re-insertamos para que se reubique en el ranking

            // Determinar si es entrada o salida
            String tipo = variacion > producto.getStock() ? "ENTRADA" : "SALIDA";
            registrarMovimiento(codigo, tipo, Math.abs(variacion), motivo);

            guardarDatos();
        }
    }

    private void registrarMovimiento(Long codigo, String tipo, int cantidad, String motivo) {
        Movimiento mov = new Movimiento(codigo, tipo, cantidad, motivo);
        historialMovimientos.add(mov);
    }

    public Inventario buscarProducto(Long codigo) {
        return catalogoMain.get(codigo);
    }

    public List<Inventario> obtenerCatalogoCompleto() {
        return new ArrayList<>(catalogoMain.values());
    }

    public List<Inventario> obtenerTop10BajoStock() {
        return rankingBajoStock.stream().toList();
    }

    public List<Movimiento> obtenerHistorial() {
        return historialMovimientos;
    }
}