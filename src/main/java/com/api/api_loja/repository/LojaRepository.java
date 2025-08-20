package com.api.api_loja.repository;

import com.api.api_loja.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LojaRepository extends
        JpaRepository<ProdutoModel, UUID> {
        //List<ProdutoModel> findByNome(String nome);
    List<ProdutoModel> findByNomeContainingIgnoreCase(String nome);
}
