package com.rix.stock_tracker.exceptions;

public class DuplicateCategoryException extends com.rix.stock_tracker.exceptions.BusinessException {
    public DuplicateCategoryException() {
    }

    public DuplicateCategoryException(String message) {
        super(message);
    }

    public DuplicateCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateCategoryException(Throwable cause) {
        super(cause);
    }

    public DuplicateCategoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
