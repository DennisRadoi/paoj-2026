package com.pao.laboratory05.audit;

import com.pao.laboratory05.audit.Angajat;
import java.time.LocalDateTime;
import java.util.Arrays;

public class AngajatService {
    private static AngajatService instanta = null;
    private Angajat[] angajati;
    private AuditEntry[] auditLog;
    private AngajatService(){
        this.angajati = new Angajat[0];
        this.auditLog = new AuditEntry[0];
    }

    private static class Holder {
        private static final AngajatService instanta = new AngajatService();
    }
    public static AngajatService getInstance(){
        return Holder.instanta;
    }

    private void logAction(String action, String target){
        AuditEntry e = new AuditEntry(action, target, LocalDateTime.now().toString());
        AuditEntry[] copy = new AuditEntry[this.auditLog.length + 1];
        System.arraycopy(this.auditLog, 0, copy, 0, this.auditLog.length);
        copy[this.auditLog.length] = e;
        this.auditLog = copy;
    }

    public void addAngajat(Angajat a){
        Angajat[] copy = new Angajat[this.angajati.length + 1];
        System.arraycopy(this.angajati, 0, copy, 0, this.angajati.length);
        copy[this.angajati.length] = a;
        this.angajati = copy;
        System.out.println("Angajatul " + a.getNume() + " a fost adaugat cu succes");
        logAction("ADD", a.getNume());
    }

    public void printAll(){
        for(Angajat a : this.angajati){
            System.out.println(a);
        }
    }

    public void listBySalary(){
        Angajat[] copy = this.angajati.clone();
        Arrays.sort(copy);
        for(Angajat a : copy){
            System.out.println(a);
        }
    }

    public void findByDepartament(String numeDept){
        int ok = 0;
        logAction("FIND_BY_DEPT", numeDept);
        for(Angajat a : this.angajati){
            if(a.getDepartament().nume().equalsIgnoreCase(numeDept)){
                System.out.println(a);
                ok = 1;
            }
        }
        if(ok == 0){
            System.out.println("NICIUN ANGAJAT IN DEPARTAMENTUL " + numeDept);
        }
    }
    public void printAuditLog(){
        for(AuditEntry e : this.auditLog){
            System.out.println(e);
        }

    }
}
