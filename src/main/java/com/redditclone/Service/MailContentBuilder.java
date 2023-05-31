package com.redditclone.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@AllArgsConstructor
@Service
public class MailContentBuilder {


    @Autowired
    private SpringTemplateEngine springTemplateEngine;



//    public String build(String message) throws ClassNotFoundException{
//        Context context = new Context();
//        context.setVariable("message", message);
//        return springTemplateEngine.process("mailTemplate", context);
//    }
    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return springTemplateEngine.process("mailTemplate", context);
    }
}
