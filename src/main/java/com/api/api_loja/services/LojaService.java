package com.api.api_loja.services;

import com.api.produto.dtos.ProdutoDto;
import com.api.produto.models.ProdutoModel;
import com.api.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LojaService {
    private ProdutoRepository produtoRepository;
    public LojaService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoModel create(ProdutoDto dto) {
        ProdutoModel produto = new ProdutoModel();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> listar() {
        return produtoRepository.findAll();
    }

    public ProdutoModel atualizar(ProdutoDto dto, UUID id) {
        ProdutoModel existente = produtoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Produto  não encontrado"));
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());
        existente.setPreco(dto.getPreco());
        return produtoRepository.save(existente);
    }

    public void delete(UUID id) {
        ProdutoModel existente = produtoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Produto  não encontrado"));
        produtoRepository.deleteById(existente.getId());
    }

    public List<ProdutoModel> buscarPorNome(String nomeBusca) {
        return produtoRepository.findByNomeContainingIgnoreCase(nomeBusca);
    }
}
