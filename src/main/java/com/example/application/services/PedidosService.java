package com.example.application.services;

import com.example.application.data.Pedidos;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidosService {

    private final ObjectMapper objectMapper;
    private final File archivoJson = new File("src/main/resources/pedidos.json");
    private List<Pedidos> pedidosCache = new ArrayList<>();

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
                pedidosCache = objectMapper.readValue(archivoJson, new TypeReference<List<Pedidos>>() {
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pedidosCache = new ArrayList<>();
            guardarDatos();
        }
    }

    private void guardarDatos() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(archivoJson, pedidosCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pedidos> findAll() {
        return pedidosCache;
    }

    public Optional<Pedidos> get(Long id) {
        return pedidosCache.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Pedidos save(Pedidos entity) {
        if (entity.getId() == null) {

            long nextId = pedidosCache.stream().mapToLong(Pedidos::getId).max().orElse(0) + 1;

            entity.setId(nextId);

            pedidosCache.add(entity);

        } else {
            delete(entity.getId());
            pedidosCache.add(entity);
        }
        guardarDatos();
        return entity;
    }

    public void delete(Long id) {
        pedidosCache.removeIf(p -> p.getId().equals(id));
        guardarDatos();
    }
}