package com.api.api_loja.services;

import com.api.api_loja.dtos.LojaDto;
import com.api.api_loja.models.LojaModel;
import com.api.api_loja.repository.LojaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LojaService {
    private LojaRepository lojaRepository;
    public LojaService(LojaRepository lojaRepository) {
        this.lojaRepository = lojaRepository;
    }

    public LojaModel create(LojaDto dto) {
        LojaModel loja = new LojaModel();
        loja.setNome(dto.getNome());
        loja.setDescricao(dto.getDescricao());
        loja.setPreco(dto.getPreco());
        return lojaRepository.save(loja);
    }

    public List<LojaModel> listar() {
        return lojaRepository.findAll();
    }

    public LojaModel atualizar(LojaDto dto, UUID id) {
        LojaModel existente = lojaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Loja  não encontrado"));
        existente.setNome(dto.getNome());
        existente.setDescricao(dto.getDescricao());
        existente.setPreco(dto.getPreco());
        return lojaRepository.save(existente);
    }

    public void delete(UUID id) {
        LojaModel existente = lojaRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Loja  não encontrado"));
        lojaRepository.deleteById(existente.getId());
    }

    public List<LojaModel> buscarPorNome(String nomeBusca) {
        return lojaRepository.findByNomeContainingIgnoreCase(nomeBusca);
    }
}
