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
    private UsuarioDto usuario;
    private LocalDateTime data;
    private Integer diaAlugado;
    private Double preco;
    private String nomeItem;
}
