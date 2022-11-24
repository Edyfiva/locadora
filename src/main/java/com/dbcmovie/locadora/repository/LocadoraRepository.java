package com.dbcmovie.locadora.repository;

import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.dto.PrecoDto;
import com.dbcmovie.locadora.entity.LocadoraEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocadoraRepository extends MongoRepository<LocadoraEntity, String> {
    List<LocadoraDto> findByPreco(Double preco);

    @Aggregation(pipeline = {
            "{'$group': {'_id':  '$preco', 'quantidade' : {'$sum': 1}}}"
    })
    List<PrecoDto> listQuantidadePreco();
}
