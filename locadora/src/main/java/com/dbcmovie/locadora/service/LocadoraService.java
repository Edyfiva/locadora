package com.dbcmovie.locadora.service;

import com.dbcmovie.locadora.dto.LocadoraCreateDto;
import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.dto.PrecoDto;
import com.dbcmovie.locadora.entity.LocadoraEntity;
import com.dbcmovie.locadora.exception.RegraDeNegocioException;
import com.dbcmovie.locadora.repository.LocadoraRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocadoraService {

    private final LocadoraRepository locadoraRepository;
    private final ObjectMapper objectMapper;

    public LocadoraDto alugar(LocadoraCreateDto locadoraCreateDto) {
        LocadoraEntity locadoraEntity = objectMapper.convertValue(locadoraCreateDto, LocadoraEntity.class);
        Integer diasLocacao = locadoraEntity.getDiasLocacao();
        Double preco = locadoraEntity.getPreco();
        Double result = preco * diasLocacao;
        locadoraEntity.setPreco(result);
        return objectMapper.convertValue(locadoraRepository.save(locadoraEntity), LocadoraDto.class);
    }


    public List<LocadoraDto> list() throws RegraDeNegocioException {
        return locadoraRepository.findAll().stream()
                .map(locadoraEntity -> objectMapper.convertValue(locadoraEntity, LocadoraDto.class))
                .toList();
    }

    public List<PrecoDto> quantidadePrecos() {
        return locadoraRepository.listQuantidadePreco();
    }

    public List<LocadoraDto> findByPreco(Double preco) {
        return locadoraRepository.findByPreco(preco);
    }

}