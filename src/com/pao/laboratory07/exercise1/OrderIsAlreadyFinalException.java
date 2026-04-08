package com.pao.laboratory07.exercise1;

public class OrderIsAlreadyFinalException extends RuntimeException {
    public OrderIsAlreadyFinalException() {
        super("Comanda este deja intr o stare finala.");
    }
}
