package com.pao.laboratory07.exercise1;

public class CannotRevertInitialOrderStateException extends Exception {
  public CannotRevertInitialOrderStateException() {
    super("Nu poti anula starea initiala a comenzii");
  }
}