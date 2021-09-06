package String;

import java.io.*;
import java.util.*;

public class Baka {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split("");
        int time = 0;
        int value = 0;
        for (String s : str) {
            switch (s) {
                case "A":
                case "B":
                case "C":
                    value = 3;
                    break;
                case "D":
                case "E":
                case "F":
                    value = 4;
                    break;
                case "G":
                case "H":
                case "I":
                    value = 5;
                    break;
                case "J":
                case "K":
                case "L":
                    value = 6;
                    break;
                case "M":
                case "N":
                case "O":
                    value = 7;
                    break;
                case "P":
                case "Q":
                case "R":
                case "S":
                    value = 8;
                    break;
                case "T":
                case "U":
                case "V":
                    value = 9;
                    break;
                case "W":
                case "X":
                case "Y":
                case "Z":
                    value = 10;
                    break;
                default:
                    break;
            }
            time += value;
        }

        System.out.println(time);
    }
}
//868242 / 9 7 9 3 5 3 /