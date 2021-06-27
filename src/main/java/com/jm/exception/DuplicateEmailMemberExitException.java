package com.jm.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateEmailMemberExitException extends DuplicateKeyException {
    public DuplicateEmailMemberExitException(String msg) {
        super(msg);
    }

    public DuplicateEmailMemberExitException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
