package Greedy;

import java.io.*;
import java.util.*;

public class Change {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int money = Integer.parseInt(br.readLine());

        int c = 1000 - money;

        int count = 0;

        int fiveHundred = c / 500;
        int hundred = (c % 500) / 100;
        int fifty = ((c % 500) % 100) / 50;
        int ten = (((c % 500) % 100) % 50) / 10;
        int five = ((((c % 500) % 100) % 50) % 10) / 5;
        int one = ((((c % 500) % 100) % 50) % 10) % 5;

        count = fiveHundred + hundred + fifty + ten + five + one;

        bw.write(count + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}
