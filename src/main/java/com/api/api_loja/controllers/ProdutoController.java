package com.api.api_loja.controllers;

import com.api.api_loja.dtos.ProdutoDto;
import com.api.api_loja.models.ProdutoModel;
import com.api.api_loja.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {
  private final ProdutoService produtoService;
  public ProdutoController(ProdutoService produtoService) {
      this.produtoService = produtoService;
  }
    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(
            @RequestBody @Valid ProdutoDto dto){
      //if(bindingResult.hasErrors()) {
          //var errors = bindingResult.getFieldErrors().stream().
          //        map(fe -> Map.of("mensagem", fe.getDefaultMessage())).toList();
          //return ResponseEntity.badRequest().body(Map.of("mensagem", errors));
          // retorno padrao
          //return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        ProdutoModel produtoSalvo = produtoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/listar")
    public List<ProdutoModel> listar(){
      return produtoService.listar();
    }

    @PostMapping("/editar/{id}")
    public ResponseEntity<?> editar(
    @RequestBody @Valid ProdutoDto dto,
    @PathVariable(value = "id") UUID id
    ){
      try{
          ProdutoModel produtoEditado = produtoService.atualizar(dto,id);
          return ResponseEntity.status(HttpStatus.CREATED).body(produtoEditado);
      } catch (Exception e){
          //Retorna error 500 com a mensagem de erro para o front
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: "+ e.getMessage());
      }
    }

    @PostMapping("/apagar/{id}")
    public ResponseEntity<?> apagar(@PathVariable UUID id){
      try{
          produtoService.delete(id);
          return ResponseEntity.ok("Produto apagado com sucesso!");
      } catch (Exception e){
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deu ruim: "+ e.getMessage());
      }
    }
    @GetMapping("/buscar")
    public List<ProdutoModel> buscar(
            @RequestParam String nomeBusca
    ){
        return produtoService.buscarPorNome(nomeBusca);
    }

    


}
