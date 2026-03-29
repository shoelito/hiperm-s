package com.example.application.services;

import com.example.application.data.Inventario;

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

    public InventarioService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        cargarDatos();
    }
}