package com.example.application.services;

import com.example.application.data.Pedidos;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.*;

@Service
public class PedidosService {

    private final ObjectMapper objectMapper;
    private final File archivoJson = new File("src/main/resources/pedidos.json");

    private PriorityQueue<Pedidos> colaUrgentes = new PriorityQueue<Pedidos>(Comparator.comparing(Pedidos::getId));
    private Queue<Pedidos> colaNormales = new LinkedList<>();
    private Stack<Pedidos> historialAtendidos = new Stack<>();

    public PedidosService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }

    private void cargarDatos() {
        if (archivoJson.exists()) {
            try {
                List<Pedidos> todosLosPedidos = objectMapper.readValue(archivoJson, new TypeReference<List<Pedidos>>() {
                });

                for (Pedidos p : todosLosPedidos) {
                    if ("Completado".equalsIgnoreCase(p.getEstado())) {
                        historialAtendidos.push(p);
                    } else if ("Urgente".equalsIgnoreCase(p.getPrioridad())) {
                        colaUrgentes.offer(p);
                    } else {
                        colaNormales.offer(p);
                    }
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
            // Listas unificadas para guardar
            List<Pedidos> todos = new ArrayList<>();
            todos.addAll(colaUrgentes);
            todos.addAll(colaNormales);
            todos.addAll(historialAtendidos);

            // objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivoJson, todos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarPedido(Pedidos pedido) {
        // Autogenerar ID
        long nextId = obtenerTodosLosPedidos().stream().mapToLong(Pedidos::getId).max().orElse(0) + 1;
        pedido.setId(nextId);
        pedido.setEstado("Pendiente");

        if ("Urgente".equalsIgnoreCase(pedido.getPrioridad())) {
            colaUrgentes.offer(pedido);
        } else {
            colaNormales.offer(pedido);
        }
        guardarDatos();
    }

    public Pedidos atenderSiguiente() {
        Pedidos pedidoAtendido = null;

        if (!colaUrgentes.isEmpty()) {
            pedidoAtendido = colaUrgentes.poll();
        } else if (!colaNormales.isEmpty()) {
            pedidoAtendido = colaNormales.poll();
        }

        if (pedidoAtendido != null) {
            pedidoAtendido.setEstado("Completado");
            historialAtendidos.push(pedidoAtendido);
            guardarDatos();
        }

        return pedidoAtendido;
    }

    public boolean deshacerUltimoAtendido() {
        if (!historialAtendidos.isEmpty()) {
            Pedidos ultimoCompletado = historialAtendidos.pop();
            ultimoCompletado.setEstado("Pendiente");

            if ("Urgente".equalsIgnoreCase(ultimoCompletado.getPrioridad())) {
                colaUrgentes.offer(ultimoCompletado);
            } else {
                colaNormales.offer(ultimoCompletado);
            }

            guardarDatos();
            return true;
        }
        return false;
    }

    public List<Pedidos> obtenerPedidosGrid() {
        List<Pedidos> listaOrdenada = new ArrayList<>();

        listaOrdenada.addAll(colaUrgentes.stream().sorted(Comparator.comparing(Pedidos::getId)).toList());
        listaOrdenada.addAll(colaNormales);

        return listaOrdenada;
    }

    private List<Pedidos> obtenerTodosLosPedidos() {
        List<Pedidos> todos = new ArrayList<>();
        todos.addAll(colaUrgentes);
        todos.addAll(colaNormales);
        todos.addAll(historialAtendidos);
        return todos;
    }
}