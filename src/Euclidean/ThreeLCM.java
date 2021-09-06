package Euclidean;

import java.io.*;
import java.util.*;

class SaveLCM {
    int max;
    int min;
    int save;

    public SaveLCM(int max, int min, int save) {
        this.max = max;
        this.min = min;
        this.save = save;
    }
}

public class ThreeLCM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<SaveLCM> array = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            int a = Math.max(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            int b = Math.min(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            int save = a * b;

            array.add(new SaveLCM(a, b, save));
        }

        int result;
        int index = 0;
        while (true) {
            result = array.get(index).max % array.get(index).min;
            array.get(index).max = array.get(index).min;
            array.get(index).min = result;

            if (array.get(index).min == 0) {
                bw.write((array.get(index).save / array.get(index).max) + "\n");
                index++;
            }
            if (array.get(array.size() - 1).min == 0) {
                break;
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}