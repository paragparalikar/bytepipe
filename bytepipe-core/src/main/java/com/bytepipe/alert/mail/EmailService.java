package com.bytepipe.alert.mail;

import com.bytepipe.Brand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final Brand brand;
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public void dispatch(String to, String subject, EmailTemplate emailTemplate, Context context){
        context.setVariable("brand", brand);
        final MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            final String content = templateEngine.process(emailTemplate.getFileName(), context);
            messageHelper.setText(content, true);
        };
        try {
            javaMailSender.send(messagePreparator);
        } catch (MailException e) {
            log.error("EmailService.dispatch: Error!", e);
        }
    }

}
