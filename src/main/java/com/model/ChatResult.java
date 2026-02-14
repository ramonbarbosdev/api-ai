package com.model;

public record ChatResult(

        String content,

        Integer promptTokens,

        Integer completionTokens,

        Integer totalTokens

) {}