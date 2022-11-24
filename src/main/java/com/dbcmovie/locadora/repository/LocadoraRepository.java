package com.dbcmovie.locadora.repository;

import com.dbcmovie.locadora.entity.LocadoraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocadoraRepository extends MongoRepository<LocadoraEntity, String> {
//    List<LocadoraDto> findByPreco(Double preco);
//
//    @Aggregation(pipeline = {
//            "{'$group': {'_id':  '$preco', 'quantidade' : {'$sum': 1}}}"
//    })
//    List<PrecoDto> listQuantidadePreco();
}
