package com.dbcmovie.locadora.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "locadora")
public class LocadoraDto {


    private String idLocadora;
    private String nomePessoa;
    private Double preco;
    private String nomeItem;
    private boolean disponibilidade;
}
