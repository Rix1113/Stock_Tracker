package com.rix.stock_tracker.exceptions;

public class DeletingNonExistentObject extends com.rix.stock_tracker.exceptions.BusinessException {
    public DeletingNonExistentObject() {
    }

    public DeletingNonExistentObject(String message) {
        super(message);
    }

    public DeletingNonExistentObject(String message, Throwable cause) {
        super(message, cause);
    }

    public DeletingNonExistentObject(Throwable cause) {
        super(cause);
    }

    public DeletingNonExistentObject(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
