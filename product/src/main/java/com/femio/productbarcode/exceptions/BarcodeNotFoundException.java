package com.femio.productbarcode.exceptions;

public class BarcodeNotFoundException extends RuntimeException{
    public BarcodeNotFoundException(String message) {
        super(message);
    }
}
