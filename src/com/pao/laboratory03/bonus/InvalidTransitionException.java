package com.pao.laboratory03.bonus;

public class InvalidTransitionException extends RuntimeException {
    private final Status from, to;
    public InvalidTransitionException(Status from, Status to) {
        super("nu se poate trece din " + from + " in " + to);
        this.from = from;
        this.to = to;
    }
    public String getMessage() {
        return super.getMessage();
    }
}
