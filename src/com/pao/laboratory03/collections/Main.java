package com.pao.laboratory03.collections;

import java.util.*;


/**
 * Exercițiul 1 — Colecții: HashMap și TreeMap
 *
 * Creează în acest main:
 *
 * PARTEA A — HashMap (frecvența cuvintelor)
 * 1. Declară un array de String-uri:
 *    String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
 * 2. Creează un HashMap<String, Integer> care contorizează de câte ori apare fiecare cuvânt.
 *    - Parcurge array-ul și folosește put() + getOrDefault() pentru a incrementa contorul.
 * 3. Afișează map-ul.
 * 4. Verifică dacă există cheia "rust" cu containsKey().
 * 5. Afișează DOAR cheile (keySet()), apoi DOAR valorile (values()).
 * 6. Parcurge map-ul cu entrySet() și afișează "cheia -> valoarea" pentru fiecare intrare.
 *
 * PARTEA B — TreeMap (sortare automată)
 * 7. Creează un TreeMap<String, Integer> din același HashMap (constructor cu argument).
 * 8. Afișează TreeMap-ul — observă ordinea alfabetică a cheilor.
 * 9. Folosește firstKey() și lastKey() pentru a afișa prima și ultima cheie.
 *
 * PARTEA C — Map cu obiecte
 * 10. Creează un HashMap<String, List<String>> care asociază materii cu liste de studenți.
 *     Exemplu: "PAOJ" -> ["Ana", "Mihai", "Ion"], "BD" -> ["Ana", "Elena"]
 * 11. Afișează toți studenții de la materia "PAOJ".
 * 12. Adaugă un student nou la "BD" și afișează lista actualizată.
 *
 * Output așteptat (orientativ — ordinea HashMap poate varia):
 *
 * === PARTEA A: HashMap — frecvența cuvintelor ===
 * Frecvență: {python=2, c++=2, java=3, rust=1, go=1}
 * Conține 'rust'? true
 * Chei: [python, c++, java, rust, go]
 * Valori: [2, 2, 3, 1, 1]
 * python -> 2
 * c++ -> 2
 * java -> 3
 * rust -> 1
 * go -> 1
 *
 * === PARTEA B: TreeMap — sortare automată ===
 * Sortat: {c++=2, go=1, java=3, python=2, rust=1}
 * Prima cheie: c++
 * Ultima cheie: rust
 *
 * === PARTEA C: Map cu obiecte ===
 * Studenți la PAOJ: [Ana, Mihai, Ion]
 * Studenți la BD (actualizat): [Ana, Elena, George]
 */
public class Main {
    public static void main(String[] args) {
        String[] words = {"java", "python", "java", "c++", "python", "java", "rust", "c++", "go"};
        Map<String, Integer> frecventa = new HashMap<>();
        for(String s : words){
            frecventa.put(s, frecventa.getOrDefault(s, 0) + 1);
        }
        System.out.println("PARTEA A");
        System.out.println("Frecventa" + frecventa);

        boolean eRust = false;
        if(frecventa.containsKey("rust")) {eRust = true;};
        System.out.println("Contine rust? " + eRust);
        System.out.println("Chei " + frecventa.keySet());
        System.out.println("Valori " + frecventa.values());

        for (Map.Entry<String, Integer> intrare : frecventa.entrySet()) {
            System.out.println(intrare.getKey() + " -> " + intrare.getValue());
        }

        TreeMap<String, Integer> sortedFrecv = new TreeMap<>(frecventa);
        System.out.println("PARTEA B");
        System.out.println("Sortat: " + sortedFrecv);
        System.out.println("Prima cheie: " + sortedFrecv.firstKey());
        System.out.println("Ultima cheie: " + sortedFrecv.lastKey());

        System.out.println("Partea C");

        Map<String, List<String>> studenti= new HashMap<>();
        studenti.put("PAOJ", new ArrayList<>(Arrays.asList("Ana", "Mihai", "Ion")));
        studenti.put("BD", new ArrayList<>(Arrays.asList("Ana", "Elena")));
        List<String> paoj = new ArrayList<>();
        paoj.add("Ana");
        paoj.add("Mihai");
        paoj.add("Ion");
        studenti.put("PAOJ", paoj);

        List<String> bd = new ArrayList<>();
        bd.add("Ana");
        bd.add("Elena");
        studenti.put("BD", bd);
        System.out.println("Studenti la PAOJ " + studenti.get("PAOJ"));
        studenti.get("BD").add("Geroge");
        System.out.println("Studenti la BD(actualizat) " + studenti.get("BD"));

    }
}

