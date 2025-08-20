package com.api.api_loja.controllers;

import com.api.api_loja.dtos.LojaDto;
import com.api.api_loja.models.LojaModel;
import com.api.api_loja.services.LojaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/lojas")
public class LojaController {
  private final LojaService lojaService;
  public LojaController(LojaService lojaService) {
      this.lojaService = lojaService;
  }
    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid LojaDto dto){
      //if(bindingResult.hasErrors()) {
          //var errors = bindingResult.getFieldErrors().stream().
          //        map(fe -> Map.of("mensagem", fe.getDefaultMessage())).toList();
          //return ResponseEntity.badRequest().body(Map.of("mensagem", errors));
          // retorno padrao
          //return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        LojaModel lojaSalvo = lojaService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(lojaSalvo);
    }

    @GetMapping("/listar")
    public List<LojaModel> listar(){
      return lojaService.listar();
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(
    @RequestBody @Valid LojaDto dto,
    @PathVariable(value = "id") UUID id
    ){
      try{
          LojaModel lojaEditado = lojaService.atualizar(dto,id);
          return ResponseEntity.status(HttpStatus.CREATED).body(lojaEditado);
      } catch (Exception e){
          //Retorna error 500 com a mensagem de erro para o front
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
      }
    }

    @PostMapping("/apagar/{id}")
    public ResponseEntity<?> apagar(@PathVariable UUID id){
      try{
          lojaService.delete(id);
          return ResponseEntity.ok("loja apagado com sucesso!");
      } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu ruim: "+ e.getMessage());
      }
    }
    @GetMapping("/buscar")
    public List<LojaModel> buscar(
            @RequestParam String nomeBusca
    ){
        return lojaService.buscarPorNome(nomeBusca);
    }

    


}
