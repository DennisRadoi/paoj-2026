package com.pao.project.models;

import java.util.Comparator;

public class ComparatorComanda implements Comparator<Comanda> {
    public int compare(Comanda c1, Comanda c2){
        int egalitate = Double.compare(c2.getPretTotal(), c1.getPretTotal());
        if(egalitate != 0){
            return egalitate;
        }
        return Integer.compare(c1.getId(), c2.getId());
    }
}
