package com.dbcmovie.locadora.controller;

import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.service.LocadoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/locadora")
@RestController
public class LocadoraController {

    private final LocadoraService locadoraService;


    @Operation(summary = "Listar os items da locadora", description = "Lista todos os Itens da locadora consumido do Kakfa")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Retorna a lista com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<LocadoraDto>> listAll() {
        return ResponseEntity.ok(locadoraService.list());
    }
}
