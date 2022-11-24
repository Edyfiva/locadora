package com.dbcmovie.locadora.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private String nome;
    private Integer idade;
    private String email;
    private Integer idUsuario;
}