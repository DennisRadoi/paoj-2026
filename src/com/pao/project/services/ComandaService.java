package com.pao.project.services;

import com.pao.project.models.Comanda;

import java.util.ArrayList;

public class ComandaService {
    private static ComandaService instanta = null;
    private ArrayList<Comanda> comenzi = null;

    private ComandaService(){
        comenzi = new ArrayList<>();
    }

    public static ComandaService getInstanta(){
        if(instanta == null){
            instanta = new ComandaService();
        }
        return instanta;
    }
}
