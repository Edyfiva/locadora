package com.dbcmovie.locadora.service;

import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.dto.UsuarioDto;
import com.dbcmovie.locadora.entity.LocadoraEntity;
import com.dbcmovie.locadora.repository.LocadoraRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocadoraService {

    private final LocadoraRepository locadoraRepository;

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    private static final Logger logSchedule = LoggerFactory.getLogger((ScheduledTask.class));

    @KafkaListener(
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"${kafka.partition}"})}
    )
    public void consumirEventoLocacao(@Payload String mensagem) throws JsonProcessingException {
        LocadoraDto locacaoDto = objectMapper.readValue(mensagem, LocadoraDto.class);
        log.info("Locação: Nome do Usário: {}, Email do Usuário: {}, Idade: {}, Nome do filme: {}, Data: {}, Dias alugados: {}",
                locacaoDto.getUsuario().getNome(),
                locacaoDto.getUsuario().getEmail(),
                locacaoDto.getUsuario().getIdade(),
                locacaoDto.getNomeItem(),
                locacaoDto.getData(),
                locacaoDto.getDiaAlugado());
        LocadoraEntity locacaoEntity = objectMapper.convertValue(locacaoDto, LocadoraEntity.class);


        locadoraRepository.save(locacaoEntity);
        log.info("Locação salva com sucesso");
    }

    public List<LocadoraDto> list() {
        return locadoraRepository.findAll().stream()
                .map(locadoraEntity -> objectMapper.convertValue(locadoraEntity, LocadoraDto.class))
                .toList();
    }

//    @Scheduled(cron = "0 0 * * * *")
//    public void reportarEmailLocacao(UsuarioDto usuarioDto, LocadoraDto locadoraDto) {
//        emailService.sendEmailUsuario(usuarioDto, locadoraDto);
//    }
}