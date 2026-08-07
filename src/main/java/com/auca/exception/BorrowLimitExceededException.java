package com.auca.exception;

public class BorrowLimitExceededException extends RuntimeException {

    public BorrowLimitExceededException() {
        super();
    }

    public BorrowLimitExceededException(String message) {
        super(message);
    }
}
