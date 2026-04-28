package com.pao.laboratory09.exercise1;

import java.io.Serializable;

public class Tranzactie implements Serializable {
    private int id;
    private double suma;
    private String data;
    private String contSursa;
    private String ContDestinatie;
    private TipTranzitie tip;
    transient String note;
    private static final long serialVersionUID = 1L;
}
