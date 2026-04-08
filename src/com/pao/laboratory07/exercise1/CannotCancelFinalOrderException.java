package com.pao.laboratory07.exercise1;

public class CannotCancelFinalOrderException extends Exception {
    public CannotCancelFinalOrderException() {
        super("Nu se poate anula o comanda in stare finala.");
    }
}
