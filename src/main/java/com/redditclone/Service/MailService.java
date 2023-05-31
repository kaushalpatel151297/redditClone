package com.redditclone.Service;

import com.redditclone.Exception.SpringBootApplicationException;
import com.redditclone.Model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j

public class MailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailContentBuilder mailContentBuilder;
    @Async
    public void sednMail(NotificationEmail notificationEmail)
    {
        MimeMessagePreparator messagePreparator = mimeMessage ->
        {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("kaushalpatel1512@gmail.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activate Email Address!");
        }
        catch (MailException e)
        {
            throw  new SpringBootApplicationException("Exception occurred when sending mail to " +notificationEmail.getRecipient(), e);
        }
        }
    }


