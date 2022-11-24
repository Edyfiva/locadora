package com.dbcmovie.locadora.service;

import com.dbcmovie.locadora.dto.LocadoraCreateDto;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LocadoraService {

    private final LocadoraRepository locadoraRepository;

    private final EmailService emailService;
    private final ObjectMapper objectMapper;


    private LocadoraCreateDto locacaoCreateDto;

    @KafkaListener(
            clientIdPrefix = "${spring.kafka.consumer.client-id}",
            groupId = "${spring.kafka.consumer.group-id}",
            topicPartitions = {@TopicPartition(topic = "${kafka.topic}", partitions = {"${kafka.partition}"})}
    )
    public void consumirEventoLocacao(@Payload String mensagem) throws JsonProcessingException {
        locacaoCreateDto = objectMapper.readValue(mensagem, LocadoraCreateDto.class);
        log.info("Nova locação recebida\n ### Dados Locação ###\n" +
                        "Nome do Usário: {}\n" +
                        "Email do Usuário: {}\n" +
                        "Idade: {}\n" +
                        "Nome do filme: {}\n" +
                        "Data: {}\n" +
                        "Quantidade de dias alugado: {}",
                locacaoCreateDto.getUsuario().getNome(),
                locacaoCreateDto.getUsuario().getEmail(),
                locacaoCreateDto.getUsuario().getIdade(),
                locacaoCreateDto.getNomeFilme(),
                locacaoCreateDto.getData(),
                locacaoCreateDto.getQtdDiasLocacao());
        Double valorTotalLocacao = locacaoCreateDto.getQtdDiasLocacao() * locacaoCreateDto.getPrecoFilme();
        LocadoraEntity locacaoEntity = objectMapper.convertValue(locacaoCreateDto, LocadoraEntity.class);
        locacaoEntity.setValorTotal(valorTotalLocacao);

        locadoraRepository.save(locacaoEntity);
        log.info("Locação salva com sucesso");
    }

    public List<LocadoraDto> list() {
        return locadoraRepository.findAll().stream()
                .map(locadoraEntity -> objectMapper.convertValue(locadoraEntity, LocadoraDto.class))
                .toList();
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void reportarEmailLocacao() {
        emailService.sendEmailUsuario(objectMapper.convertValue(locacaoCreateDto, LocadoraDto.class));
    }
}