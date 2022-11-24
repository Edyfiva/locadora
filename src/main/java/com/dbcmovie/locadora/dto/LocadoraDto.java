package com.dbcmovie.locadora.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocadoraDto {
    private String idLocadora;
    private String nomePessoa;
    private Double preco;
    private String nomeItem;
    private boolean disponibilidade;
}
