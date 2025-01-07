package Greedy;

import java.io.*;
import java.util.*;

public class Airport {
    static int G, P;
    static int[] gates, airplanes, points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        G = Integer.parseInt(st.nextToken());
        gates = new int[G + 1];

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        airplanes = new int[P];
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            airplanes[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        /* 작동 */
        points = new int[G + 1];
        for (int i = 1; i < G + 1; i++) {
            points[i] = i;
        }
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int count = 0;
        for (int i = 0; i < P; i++) {
            int gate = airplanes[i];
            boolean isSuccess = false;
            if (points[gate] > 0) {
                for (int j = points[gate]; j > 0; j--) {
                    if (gates[j] > 0) continue;
                    gates[j] = gate;
                    points[gate] = j;
                    isSuccess = true;
                    break;
                }
            } else {
                isSuccess = true;
                gates[gate] = gate;
                points[gate]--;
            }
            if (!isSuccess) return count;
            count++;
        }

        return count;
    }
}
