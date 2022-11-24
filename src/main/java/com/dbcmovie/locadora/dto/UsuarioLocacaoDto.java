package com.dbcmovie.locadora.dto;

import lombok.Data;

@Data
public class UsuarioLocacaoDto {
    private String nome;
    private Integer idade;
    private String email;
}