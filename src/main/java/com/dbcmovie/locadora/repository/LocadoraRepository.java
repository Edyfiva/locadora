package com.dbcmovie.locadora.repository;

import com.dbcmovie.locadora.entity.LocadoraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocadoraRepository extends MongoRepository<LocadoraEntity, String> {
    @Query("{'qtdDiasLocacao':{$gte:0}}")
    List<LocadoraEntity> buscarLocacaoMaiorQueZero();
//
//    @Query("{'_id':  ?0} {$set: { 'qtdDiasLocacao': ?1}}")
//    void atualizarQtdDiasLocacao(String idLocadora, Integer qtdDiasLocacao);
}
