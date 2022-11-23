package com.dbcmovie.locadora.controller;


import com.dbcmovie.locadora.dto.LocadoraCreateDto;
import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.dto.PrecoDto;
import com.dbcmovie.locadora.service.LocadoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/locadora")
public class LocadoraController {

    private final LocadoraService locadoraService;

    @Operation(summary = "Alugar Item", description = "Alugar um item no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Alugado com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/alugar")
    public ResponseEntity<LocadoraDto> create(@Valid @RequestBody LocadoraCreateDto locadoraCreateDto) {
        return ResponseEntity.ok(locadoraService.alugar(locadoraCreateDto));
    }

    @Operation(summary = "Quantidade dos preços", description = "Quantidade dos precos no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/quantidade-precos")
    public ResponseEntity<List<PrecoDto>> quantidadePrecos() {
        return ResponseEntity.ok(locadoraService.quantidadePrecos());
    }

    @Operation(summary = "Procurar por preço", description = "Procurar os preços no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Listou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/lista-precos/{preco}")
    public ResponseEntity<List<LocadoraDto>> listFindPreco(@PathVariable(name = "preco") Double preco) {
        return ResponseEntity.ok(locadoraService.findByPreco(preco));
    }


}
