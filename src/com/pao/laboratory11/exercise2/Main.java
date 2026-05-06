package com.pao.laboratory11.exercise2;
import com.pao.laboratory11.exercise1.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public void main() {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        List<Transaction> transactii = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = s.nextInt();
            BigDecimal amount = s.nextBigDecimal();
            LocalDate date = LocalDate.parse(s.next());
            String country = s.next();
            String channel = s.next();
            Transaction t = new Transaction(id, amount, date, country, channel, 0, false);
            transactii.add(t);
        }
    }
}
