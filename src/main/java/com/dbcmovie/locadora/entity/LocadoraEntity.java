package com.dbcmovie.locadora.entity;


import com.dbcmovie.locadora.dto.UsuarioLocacaoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "locadora")
public class LocadoraEntity {
    private UsuarioLocacaoDto usuario;
    private LocalDateTime data;
    private String nomeFilme;
    private Double valorTotal;
}