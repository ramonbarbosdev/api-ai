package com.model;

import javax.validation.constraints.NotNull;

public record RenameConversationRequest(
        @NotNull
        String conversationId,

        @NotNull
        String title
) {
}
