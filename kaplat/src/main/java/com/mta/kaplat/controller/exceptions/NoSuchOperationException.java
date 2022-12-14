package com.mta.kaplat.controller.exceptions;

import com.mta.kaplat.constant.Constants;

public class NoSuchOperationException extends RuntimeException {

    public NoSuchOperationException(String opr) {
        super(Constants.ERROR_NO_OPERATION + opr);
    }
}
