package com.comarch.szkolenia.sklep.exceptions;

public class BuyProductException extends RuntimeException {
    public BuyProductException (String message){
        super(message);
    }
}
