package com.pao.laboratory05.angajati;

public class Angajat implements Comparable<Angajat>{
    private String nume;
    private Departament departament;
    private double salariu;

    public Angajat(Departament departament, double salariu, String nume) {
        this.departament = departament;
        this.salariu = salariu;
        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }

    public Departament getDepartament() {
        return departament;
    }

    public double getSalariu() {
        return salariu;
    }

    public String toString() {
        return "Angajat{" +
                "nume='" + nume + '\'' +
                ", departament=" + departament +
                ", salariu=" + salariu +
                '}';
    }

    public int compareTo(Angajat other) {
        return Double.compare(other.salariu, this.salariu);
    }
}
