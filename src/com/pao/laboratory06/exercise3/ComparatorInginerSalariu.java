package com.pao.laboratory06.exercise3;
import java.util.Comparator
public class ComparatorInginerSalariu implements Comparator<Inginer> {
    public int compare(Inginer i1, Inginer i2){
        return Double.compare(i2.consultareSold(), i1.consultareSold());
    }
}
