//package com.dbcmovie.locadora.service;
//
//import com.dbcmovie.locadora.dto.LocadoraDto;
//import com.dbcmovie.locadora.dto.UsuarioLocacaoDto;
//import com.dbcmovie.locadora.exception.RegraDeNegocioException;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final freemarker.template.Configuration fmConfiguration;
//
//    @Value("${spring.mail.username}")
//    private String from;
//
//    private final JavaMailSender emailSender;
//
//
//    public void sendEmailUsuario(UsuarioLocacaoDto usuarioLocacaoDto, LocadoraDto locadoraDto) {
//        MimeMessage mimeMessage = emailSender.createMimeMessage();
//        try {
//
//            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
//
//            mimeMessageHelper.setFrom(from);
//            mimeMessageHelper.setTo(usuarioLocacaoDto.getEmail());
//            mimeMessageHelper.setSubject("subject");
//            mimeMessageHelper.setText(geContentFromTemplateUsuario(usuarioLocacaoDto, locadoraDto), true);
//
//            emailSender.send(mimeMessageHelper.getMimeMessage());
//        } catch (MessagingException | IOException  | RegraDeNegocioException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    public String geContentFromTemplateUsuario(UsuarioLocacaoDto usuarioLocacaoDto, LocadoraDto locadoraDto) throws IOException, TemplateException, RegraDeNegocioException {
//        Map<String, Object> dados = new HashMap<>();
//        dados.put("nome", usuarioLocacaoDto.getNome());
//        dados.put("id", usuarioLocacaoDto.getIdUsuario());
//        dados.put("idade", usuarioLocacaoDto.getIdade());
//        dados.put("emailUsuario", usuarioLocacaoDto.getEmail());
//        dados.put("email", from);
//        dados.put("nome do item", locadoraDto.getNomeItem());
//        dados.put("valor total da locação", locadoraDto.getPreco());
//        dados.put("total de dias alugados", locadoraDto.getDiaAlugado());
//        Template template = null;
//
//        template = fmConfiguration.getTemplate("emailcreate-template.html");
//
//        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
//    }
//
//}