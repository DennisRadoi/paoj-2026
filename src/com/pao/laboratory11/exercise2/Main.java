package com.pao.laboratory11.exercise2;
import com.pao.laboratory11.exercise1.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] strings) {
        Scanner s = new Scanner(System.in).useLocale(Locale.US);;
        int n = s.nextInt();
        List<Transaction> transactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = s.nextInt();
            BigDecimal amount = s.nextBigDecimal();
            LocalDate date = LocalDate.parse(s.next());
            String country = s.next();
            String channel = s.next();
            String accountId = s.next();
            Transaction t = new Transaction(id, amount, date, country, channel, 0, false, accountId);
            transactii.add(t);
        }

        int q = s.nextInt();
        for (int i = 0; i < q; i++) {
            String comanda = s.next();
            if (comanda.equalsIgnoreCase("REPORT_MONTH")) {
                String luna = s.next();
                List<Transaction> lista = transactii.stream().
                        filter(t -> t.getDate().toString().substring(0, 7).equalsIgnoreCase(luna))
                        .collect(Collectors.toUnmodifiableList());

                BigDecimal total = lista.stream()
                        .map(Transaction::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP);
                System.out.printf("MONTH %s total=%s count=%d%n", luna, total, lista.size());
            }
            else if (comanda.equalsIgnoreCase("REPORT_ACCOUNT")) {
                String accountId = s.next();
                List<Transaction> lista = transactii.stream()
                        .filter(t -> t.getAccountId().equalsIgnoreCase(accountId)).collect(Collectors.toUnmodifiableList());
                BigDecimal total = lista.stream()
                        .map(Transaction::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .setScale(2, RoundingMode.HALF_UP);

                System.out.printf("ACCOUNT %s total=%s count=%d%n", accountId, total, lista.size());
            }
            else if (comanda.equalsIgnoreCase("TOP_CHANNELS")) {
                int k = s.nextInt();
                transactii.stream()
                        .collect(Collectors.groupingBy(Transaction::getChannel, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder())
                                .thenComparing(Map.Entry.comparingByKey()))
                        .limit(k)
                        .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
            }
        }
    }
}
