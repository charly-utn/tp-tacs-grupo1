package org.tptacs.presentation.responseModels;

import lombok.Getter;

@Getter
public class ResponseCreateUser extends ResponseGeneric {
    private String userId;

    public ResponseCreateUser(String userId) {
        super();
        this.userId = userId;
    }
}
