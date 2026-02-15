package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConversationLimitException extends RuntimeException {

    public ConversationLimitException() {

        super("Esta conversa já possui uma pergunta. Crie uma nova conversa.");

    }

}