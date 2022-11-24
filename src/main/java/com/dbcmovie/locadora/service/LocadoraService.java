package com.dbcmovie.locadora.service;

import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.entity.LocadoraEntity;
import com.dbcmovie.locadora.repository.LocadoraRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocadoraService {

    private final LocadoraRepository locadoraRepository;

    private final ObjectMapper objectMapper;

    @KafkaListener(
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"${kafka.partition}"})}
    )
    public void consumirEventoLocacao(@Payload String mensagem) throws JsonProcessingException {
        LocadoraDto locacaoDto = objectMapper.readValue(mensagem, LocadoraDto.class);
        log.info("Locação: Id: {}, Nome: {}, Preço: {}, Item: {}, Disponibilidade: {}", locacaoDto.getIdLocadora(), locacaoDto.getNomePessoa(), locacaoDto.getPreco(), locacaoDto.getNomeItem(), locacaoDto.isDisponibilidade());
        LocadoraEntity locacaoEntity = objectMapper.convertValue(locacaoDto, LocadoraEntity.class);


        locadoraRepository.save(locacaoEntity);
        log.info("Locação salva com sucesso");
    }
}