package com.pao.laboratory06.exercise3;

public interface PlataOnline {
    public void autentificare(String user, String parola);
    public double consultareSold();
    public boolean efectuarePlata(double suma);
}
