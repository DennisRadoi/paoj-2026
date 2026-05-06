package com.pao.laboratory11.exercise1;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    private static final BigDecimal AMOUNT_LOW_THRESHOLD = new BigDecimal("1000.00");
    private static final BigDecimal AMOUNT_HIGH_THRESHOLD = new BigDecimal("5000.00");
    private static final Set<String> RISKY_COUNTRIES = Set.of("NG", "RU", "UA", "CN", "BR");
    private static final Set<String> SUSPICIOUS_CHANNELS = Set.of("WEB", "MOBILE", "APP", "CRYPTO");
    private static final int FLAG_THRESHOLD = 60;

    private static Predicate<Transaction> amountOver() {
        return t -> t.getAmount().compareTo(AMOUNT_LOW_THRESHOLD) > 0;
    }

    private static Predicate<Transaction> countryInRisk() {
        return t -> RISKY_COUNTRIES.contains(t.getCountry());
    }

    private static Predicate<Transaction> channelSuspicious() {
        return t -> SUSPICIOUS_CHANNELS.contains(t.getChannel());
    }

    private static Predicate<Transaction> flaggedRule() {
        return amountOver().or(countryInRisk()).or(channelSuspicious());
    }

    private static final Comparator<Transaction> risk_order = Comparator
            .comparingInt(Transaction::getScore).reversed()
            .thenComparing(Transaction::getAmount, Comparator.reverseOrder())
            .thenComparing(Transaction::getDate)
            .thenComparingInt(Transaction::getId);

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        List<Transaction> transactii = new ArrayList<>();
        Map<Integer, Transaction> dict = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int id = s.nextInt();
            BigDecimal amount = s.nextBigDecimal();
            LocalDate date = LocalDate.parse(s.next());
            String country = s.next();
            String channel = s.next();

            Transaction temp = new Transaction(id, amount, date, country, channel, 0, false);
            int score = calculeazaScor(temp);
            boolean flagged = score >= FLAG_THRESHOLD && flaggedRule().test(temp);
            Transaction t = new Transaction(id, amount, date, country, channel, score, flagged);
            transactii.add(t);
            dict.put(id, t);
        }

        int q = s.nextInt();
        for (int i = 0; i < q; i++) {
            String command = s.next();
            if (command.equalsIgnoreCase("CHECK")) {
                int id = s.nextInt();
                Transaction t = dict.get(id);
                if (t == null) {
                    System.out.println("CHECK " + id + " => NOT_FOUND");
                } else {
                    System.out.println("CHECK " + id + " => " + t.verdict() + " score=" + t.getScore());
                }
            } else if (command.equalsIgnoreCase("LIST_FLAGGED")) {
                List<Transaction> flagged = new ArrayList<>();
                for (Transaction t : transactii) {
                    if (t.isFlagged()) {
                        flagged.add(t);
                    }
                }
                flagged.sort(risk_order);
                if (flagged.isEmpty()) {
                    System.out.println("NONE");
                } else {
                    for (Transaction t : flagged) {
                        System.out.println("[" + t.getId() + "] FLAG score=" + t.getScore());
                    }
                }
            } else if (command.equalsIgnoreCase("TOP_RISK")) {
                int k = s.nextInt();

                List<Transaction> sortate = new ArrayList<>(transactii);
                sortate.sort(risk_order);
                int limit = Math.min(k, sortate.size());
                for (int j = 0; j < limit; j++) {
                    Transaction t = sortate.get(j);
                    System.out.println("[" + t.getId() + "] " + t.verdict() + " score=" + t.getScore());
                }
            } else {
                System.out.println("ERR UNKNOWN_COMMAND");
            }
        }
    }

    private static int calculeazaScor(Transaction t) {
        int amountScore = computeAmountScore(t.getAmount());
        int countryScore = RISKY_COUNTRIES.contains(t.getCountry()) ? 40 : 0;
        int channelScore = switch (t.getChannel()) {
            case "WEB", "ATM", "CRYPTO" -> 20;
            case "APP" -> 15;
            case "POS" -> 25;
            default -> 0;
        };

        int bonus = 0;
        if ("CRYPTO".equals(t.getChannel()) && t.getAmount().compareTo(AMOUNT_HIGH_THRESHOLD) >= 0) {
            bonus = 30;
        } else if ("KP".equals(t.getCountry()) && "CRYPTO".equals(t.getChannel())) {
            bonus = 40;
        }

        return amountScore + countryScore + channelScore + bonus;
    }

    private static int computeAmountScore(BigDecimal amount) {
        if (amount.compareTo(AMOUNT_HIGH_THRESHOLD) >= 0) {
            return 50;
        }
        if (amount.compareTo(AMOUNT_LOW_THRESHOLD) >= 0) {
            return 35;
        }
        return 0;
    }
}
