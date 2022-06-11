package com.femio.sale.exceptions;

public class SaleNotFoundException extends RuntimeException{
    public SaleNotFoundException(String message) {
        super(message);
    }
}
