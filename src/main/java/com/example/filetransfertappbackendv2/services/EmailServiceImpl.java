package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.Transfert;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;
    private TemplateRendering templateRendering;

    @Value("${spring.mail.username}")
    private String sender;

    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateRendering templateRendering) {
        this.javaMailSender = javaMailSender;
        this.templateRendering = templateRendering;
    }

    @Override
    public void sendToSender(Transfert transfert) {
        String body = renderHtmlCoreForSender(transfert);
        sendMail(body, transfert.getSender().getEmail(), transfert.getTitle());
    }

    @Override
    public void sendToReceiver(Transfert transfert) {
        String body = renderHtmlCoreForReceiver(transfert);
        sendMail(body, transfert.getReceiver().getEmail(), transfert.getTitle());
    }

    private void sendMail(String body, String receiver, String subject){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setText(body, true);
            mimeMessageHelper.setSubject(subject);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Context preparingContextForReceiver(Transfert transfert){
        Context context = new Context();
        String date = new SimpleDateFormat("dd/MM/yyy")
                            .format(transfert.getExpirationDate());
        List<String> nameOfFiles = transfert.getFiles()
                                    .stream()
                                    .map(file->file.getName())
                                    .collect(Collectors.toList());
        context.setVariable("sender", transfert.getSender().getEmail());
        context.setVariable("numberOfItems", transfert.getFiles().size());
        context.setVariable("size", 0);
        context.setVariable("dateOfExpressing", date);
        context.setVariable("path", transfert.getPath());
        context.setVariable("names", nameOfFiles);
        return context;
    }

    private String renderHtmlCoreForReceiver(Transfert transfert){
        Context context = preparingContextForReceiver(transfert);
        return templateRendering.render("mailTemplateReceiver", context);
    }

    private Context preparingContextForSender(Transfert transfert){
        Context context = new Context();
        context.setVariable("receiver", transfert.getReceiver().getEmail());
        return context;
    }

    private String renderHtmlCoreForSender(Transfert transfert){
        Context context = preparingContextForSender(transfert);
        return templateRendering.render("mailTemplateSender", context);
    }
}
