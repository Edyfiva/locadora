package com.dbcmovie.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocadoraDto {
    private UsuarioLocacaoDto usuario;
    private FilmeDisponivelDto filme;
    private Integer qtdDiasLocacao;
    private LocalDateTime data;
    private Double valorTotal;
}
