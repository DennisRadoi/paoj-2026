package com.pao.laboratory11.exercise3;

import java.util.List;
import java.util.stream.Collector;

public class Main {
    public static Collector<Transaction, ?, Snapshot> toSnapshot(int topN) {
        class Agg { /* mutable maps, total, list */ }
        return Collector.of(
                Agg::new,
                (agg, tx) -> { /* accumulate */ },
                (a,b) -> { /* combine for parallel */ return a; },
                agg -> { /* finisher -> build Snapshot, compute topN */ }
        );
    }
    public static void main(String[] args) {
        List<Transaction> data = List.of(/* câteva tranzacții hardcodate */);
        Snapshot snap = data.stream().collect(CustomCollectors.toSnapshot(5));

        snap.getTopTransactions().forEach(System.out::println);

        snap.getCountByCountry().entrySet().stream() /* sort & print */;

        snap.getCountByChannel().entrySet().stream() /* sort & print */;
    }
}
