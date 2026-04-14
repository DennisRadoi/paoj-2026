package com.pao.project.services;

import com.pao.project.models.Utilizator;

import java.util.ArrayList;

public class UtilizatorService {
    private static UtilizatorService instanta = null;
    private ArrayList<Utilizator> utilizatori;

    private UtilizatorService(){
        utilizatori = new ArrayList<>();
    }

    public static UtilizatorService getInstanta(){
        if(instanta == null){
            instanta = new UtilizatorService();
        }
        return instanta;
    }
}
