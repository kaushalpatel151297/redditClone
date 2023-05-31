package com.redditclone.Exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class SpringBootApplicationException extends RuntimeException {



    public SpringBootApplicationException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }
    public SpringBootApplicationException(String exMessage) {
        super(exMessage);
    }




}
