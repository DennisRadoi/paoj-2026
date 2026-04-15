package com.pao.project.models;

public enum StatusComanda {
    INITIALIZATA, IN_PREPARARE, IN_LIVRARE, LIVRATA, RETURNATA,
    ANULATA;

    public StatusComanda getUrm(StatusComanda s){
        return 0;
    }

    public boolean eFinal(){
        return this == LIVRATA || this == RETURNATA || this == ANULATA;
    }
    private StatusComanda(){
    }

}
