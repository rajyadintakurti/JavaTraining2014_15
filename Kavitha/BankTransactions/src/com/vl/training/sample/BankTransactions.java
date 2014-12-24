package com.vl.training.sample;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

class Transactions extends Thread {
    private Transactions t;
    private static Map<String, Integer> m = new HashMap<String, Integer>();
    private File f;
    private Scanner sc;
    public Transactions(File f) throws FileNotFoundException {
        sc = new Scanner(f);
    }
    public static synchronized Map getAmount(File f) {
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                Integer totalAmount = 0;
                String accNo = sc.next();
                String transType = sc.next();
                Integer amount = sc.nextInt();
                Integer total = m.get(accNo);
                if (total == null) {
                    m.put(accNo, amount);
                } else { 
                    if (transType.equals("WD")) {
                        totalAmount = total - amount;
                    }
                    if (transType.equals("D")) {
                        totalAmount = amount + total;
                    }
                    m.put(accNo, totalAmount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }
    public static void displayAmount() {
        Set<Map.Entry<String, Integer>> s = m.entrySet();
        System.out.println("AccNo  Amount");
        for (Map.Entry<String, Integer> mtr:s) {
            System.out.println(mtr.getKey() + "     " + mtr.getValue());
        }
    }
}

public class BankTransactions {
    Scanner sc = new Scanner(System.in);
    Transactions t;
    Thread threads;
    public void countFiles(File file) {
        int count = 0;
        final File []files;
        files = file.listFiles();
        for (final File f:files) {
            if (f.isDirectory()) {
                countFiles(f);
            } else {
                count++;
                threads= new Thread(new Runnable() {
                    public void run() {
                        Map m = t.getAmount(f);
                    }                 
                });
                threads.start();
                try {
                    threads.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        /*threads = new Thread[count];
        for (int i =0; i < count; i++) {
             threads[i] = new Thread(new Runnable() {
                 public void run() {
                     System.out.println("Hello");
                     Map m = t.getAmount(files[i]);
                 }                 
             });
             threads[i].start();
             try {
                 threads[i].join();
             } catch (IOException e) {
                 e.printStackTrace();
        }*/
    }
    public static void main(final String []args) throws FileNotFoundException {
        if (args.length > 0) {
            File f= new File(args[0]);
            BankTransactions bt = new BankTransactions();
            bt.countFiles(f);
            Transactions.displayAmount();
            
        } else {
            System.out.println("Please attach input file");
        }
    }
}
