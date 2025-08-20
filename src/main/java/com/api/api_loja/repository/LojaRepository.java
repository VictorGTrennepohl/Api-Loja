package com.api.api_loja.repository;

import com.api.api_loja.models.LojaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LojaRepository extends
        JpaRepository<LojaModel, UUID> {
        //List<ProdutoModel> findByNome(String nome);
    List<LojaModel> findByNomeContainingIgnoreCase(String nome);
}
