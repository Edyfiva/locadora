package com.dbcmovie.locadora.repository;

import com.dbcmovie.locadora.entity.LocadoraEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocadoraRepository extends MongoRepository<LocadoraEntity, String> {

}
