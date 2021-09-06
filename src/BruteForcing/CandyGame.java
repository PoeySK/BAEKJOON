package BruteForcing;

import java.io.*;
import java.util.*;

public class CandyGame {
    static String[][] array;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        array = new String[N][N];

        for (int i = 0; i < N; i++) { // 배열에 문자 입력
            String[] s = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                array[i][j] = s[j];
            }
        }

        int rowCount = 1;
        int colCount = 1;
        int rowMaxCount = 0;
        int colMaxCount = 0;
        int maxCount = 0;

        for (int i = 0; i < N; i++) { // 연속성 찾기 (행)
            for (int k = 1; k < N; k++) {
                String temp1 = array[i][k - 1];
                array[i][k - 1] = array[i][k];
                array[i][k] = temp1;

                rowCount = rowCheck();
                colCount = colCheck();

                rowMaxCount = Math.max(rowCount, colCount);

                for(int q=0;q<N;q++){
                    for(int w=0; w<N; w++){
                        System.out.print(array[q][w]);
                    }
                    System.out.println();
                }
                System.out.println("row : " + rowCount + " / col : " + colCount);

                String temp2 = array[i][k - 1];
                array[i][k - 1] = array[i][k];
                array[i][k] = temp2;
            }

        }

        for (int j = 0; j < N; j++) { // 연속성 찾기 (열)
            for (int k = 1; k < N; k++) {
                String temp1 = array[k - 1][j];
                array[k - 1][j] = array[k][j];
                array[k][j] = temp1;

                rowCount = rowCheck();
                colCount = colCheck();

                colMaxCount = Math.max(rowCount, colCount);

                for(int q=0;q<N;q++){
                    for(int w=0; w<N; w++){
                        System.out.print(array[q][w]);
                    }
                    System.out.println();
                }
                System.out.println("row" + rowCount +" / col : " + colCount);

                String temp2 = array[k - 1][j];
                array[k - 1][j] = array[k][j];
                array[k][j] = temp2;
            }
        }

        maxCount = Math.max(rowMaxCount, colMaxCount);

        bw.write(maxCount + "\n");
        bw.flush();

    }

    static int rowCheck() {
        int maxCount = 0;

        for (int i = 0; i < N; i++) {
            int count = 1;
            for (int j = 1; j < N; j++) {
                if (array[i][j - 1].equals(array[i][j])) {
                    count++;
                } else {
                    count = 1;
                }
            }
            maxCount = Math.max(count, maxCount);
        }

        return maxCount;
    }

    static int colCheck() {
        int maxCount = 0;

        for (int j = 0; j < N; j++) {
            int count = 1;
            for (int i = 1; i < N; i++) {
                if (array[i - 1][j].equals(array[i][j])) {
                    count++;
                } else {
                    count = 1;
                }
            }
            maxCount = Math.max(count, maxCount);
        }

        return maxCount;
    }
}
