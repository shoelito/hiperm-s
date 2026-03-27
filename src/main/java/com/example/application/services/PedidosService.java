package com.example.application.services;

import com.example.application.data.Pedidos;
import com.example.application.data.PedidosRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PedidosService {

    private final PedidosRepository repository;

    public PedidosService(PedidosRepository repository) {
        this.repository = repository;
    }

    public Optional<Pedidos> get(Long id) {
        return repository.findById(id);
    }

    public Pedidos save(Pedidos entity) {
        return repository.save(entity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<Pedidos> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<Pedidos> list(Pageable pageable, Specification<Pedidos> filter) {
        return repository.findAll(filter, pageable);
    }

    public int count() {
        return (int) repository.count();
    }

}
