package Greedy;

import java.io.*;
import java.util.*;

public class Sensor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        br.close();
        int[] coordinate = new int[n];
        for (int i = 0; i < n; i++) {
            coordinate[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(coordinate);

        int[] distance = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            int c = coordinate[i + 1] - coordinate[i];
            if (c == 0){ // 같은 좌표가 있는 경우 넣지 않음
                continue;
            }
            distance[i] = c;
        }
        Arrays.sort(distance);

        int rlt = 0;
        for (int i = 0; i <= distance.length - k; i++) { // 거리가 짧은 것들을 더해줌, 집중국 개수에 맞도록
            rlt += distance[i];
        }

        bw.write(rlt + "\n");

        bw.flush();
        bw.close();
    }
}
