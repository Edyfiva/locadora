package com.dbcmovie.locadora.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocadoraCreateDto {
    private UsuarioLocacaoDto usuario;
    private FilmeDisponivelDto filme;
    private Integer qtdDiasLocacao;
    private LocalDateTime data;
}
