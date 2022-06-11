package com.femio.productbarcode.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message)  {
        super(message);
    }
}
