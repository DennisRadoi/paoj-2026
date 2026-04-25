package com.pao.project.models;

public enum StatusComanda {
    INITIALIZATA, IN_PREPARARE, IN_LIVRARE, LIVRATA, RETURNATA,
    ANULATA;

    public boolean eFinal(){
        return this == LIVRATA || this == RETURNATA || this == ANULATA;
    }
    private StatusComanda(){
    }

}
