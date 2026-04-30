package com.pao.laboratory09.exercise2;

import com.pao.laboratory09.exercise1.TipTranzactie;
import com.pao.laboratory09.exercise1.Tranzactie;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "paoj-2026/output/lab09_ex2.bin";
    private static final int RECORD_SIZE = 32;

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        int n = scanner.nextInt();
        List<Tranzactie> tranzactii = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            TipTranzactie tipTranzactie = TipTranzactie.valueOf(scanner.next());

            Tranzactie tranzactie = new Tranzactie(id, suma, data, tipTranzactie);
            tranzactii.add(tranzactie);
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            ByteBuffer buffer = ByteBuffer.allocate(RECORD_SIZE).order(ByteOrder.LITTLE_ENDIAN);
            for (Tranzactie t : tranzactii) {
                dos.write(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(t.getId()).array());

                dos.write(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putDouble(t.getSuma()).array());

                byte[] dataBytes = new byte[10];
                Arrays.fill(dataBytes, (byte) ' ');
                byte[] rawData = t.getData().getBytes();
                System.arraycopy(rawData, 0, dataBytes, 0, Math.min(rawData.length, 10));
                dos.write(dataBytes);

                dos.writeByte(t.getTip() == TipTranzactie.CREDIT ? 0 : 1);

                dos.writeByte(0);

                dos.write(new byte[8]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (RandomAccessFile raf = new RandomAccessFile(OUTPUT_FILE, "rw")) {
            while (scanner.hasNext()) {
                String comanda = scanner.next();

                if (comanda.equals("READ")) {
                    int i = scanner.nextInt();
                    afiseazaInregistrare(raf, i);
                } else if (comanda.equals("UPDATE")) {
                    int idx = scanner.nextInt();
                    String status = scanner.next();
                    byte statusByte = getStatusByte(status);

                    raf.seek(idx * RECORD_SIZE + 23);
                    raf.writeByte(statusByte);
                    System.out.println("Updated [" + idx + "]: " + status);
                } else if (comanda.equals("PRINT_ALL")) {
                    long lung = raf.length();
                    int nrReg = (int) lung / RECORD_SIZE;
                    for (int i = 0; i < nrReg; i++) {
                        afiseazaInregistrare(raf, i);
                    }
                }
            }

        }
    }

    private static byte getStatusByte(String status) {
        switch (status) {
            case "PENDING": return 0;
            case "PROCESSED": return 1;
            case "REJECTED": return 2;
            default: return 0;
        }
    }

    private static void afiseazaInregistrare(RandomAccessFile raf, int i) throws IOException {
        raf.seek(i * RECORD_SIZE);
        byte[] buffer = new byte[RECORD_SIZE];
        raf.readFully(buffer);

        ByteBuffer bb = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN);

        int id = bb.getInt();
        double suma = bb.getDouble();

        byte[] dataBytes = new byte[10];
        bb.get(dataBytes);
        String data = new String(dataBytes).trim();

        byte tipB = bb.get();
        byte statB = bb.get();

        String tipStr = (tipB == 0) ? "CREDIT" : "DEBIT";
        String statStr = switch (statB) {
            case 1 -> "PROCESSED";
            case 2 -> "REJECTED";
            default -> "PENDING";
        };

        System.out.format(Locale.US, "[%d] id=%d data=%s tip=%s suma=%.2f RON status=%s\n",
                i, id, data, tipStr, suma, statStr);
    }
}
