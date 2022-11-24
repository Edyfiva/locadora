package com.dbcmovie.locadora.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocadoraCreateDto {
    private UsuarioLocacaoDto usuario;
    private LocalDateTime data;
    private String nomeFilme;
    private Double precoFilme;
    private Integer qtdDiasLocacao;
}
