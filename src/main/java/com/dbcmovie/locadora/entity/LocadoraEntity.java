package com.dbcmovie.locadora.entity;


import com.dbcmovie.locadora.dto.UsuarioDto;
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
    private UsuarioDto usuario;
    private LocalDateTime data;
    private Integer diaAlugado;
    private Double preco;
    private String nomeItem;
}