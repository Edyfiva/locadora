package com.dbcmovie.locadora.service;

import com.dbcmovie.locadora.dto.LocadoraDto;
import com.dbcmovie.locadora.exception.RegraDeNegocioException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String from;

    private final JavaMailSender emailSender;


    public void sendEmailUsuario(LocadoraDto locadoraDto) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(locadoraDto.getUsuario().getEmail());
            mimeMessageHelper.setSubject("Informações sobre os dias faltantes de locação");
            mimeMessageHelper.setText(geContentFromTemplateUsuario(locadoraDto), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | RegraDeNegocioException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }


    public String geContentFromTemplateUsuario(LocadoraDto locadoraDto) throws IOException, TemplateException, RegraDeNegocioException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", locadoraDto.getUsuario().getNome());
        dados.put("filme", locadoraDto.getFilme().getNome());
        dados.put("valor", locadoraDto.getValorTotal());
        dados.put("dias", locadoraDto.getQtdDiasLocacao());
        dados.put("data", locadoraDto.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        dados.put("email", from);
        Template template = null;

        if(locadoraDto.getQtdDiasLocacao() == 0) {
            template = fmConfiguration.getTemplate("email-marketing-template.html");
        }else if((locadoraDto.getQtdDiasLocacao() > 0)){
            template = fmConfiguration.getTemplate("dias-faltantes email-template.html");
        }

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
    }

}