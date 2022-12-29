package Greedy;

import java.io.*;
import java.util.*;

public class Ship {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 크레인 개수
        String[] sn = br.readLine().split(" "); // 크레인 무게

        int M = Integer.parseInt(br.readLine()); // 화물 개수
        String[] sm = br.readLine().split(" "); // 화물 무게

        Integer[] crane = new Integer[N];
        ArrayList<Integer> cargo = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            crane[i] = Integer.parseInt(sn[i]);
        }
        for (int i = 0; i < M; i++) {
            cargo.add(Integer.parseInt(sm[i]));
        }
        Arrays.sort(crane, Collections.reverseOrder());
        cargo.sort(Collections.reverseOrder());

        if (crane[0] < cargo.get(0)) { // 화물의 무게가 매우 큰 경우
            bw.write("-1");
            bw.flush();
            br.close();
            bw.close();

            return;
        }

        int count = 0;

        while (!cargo.isEmpty()) {
            int index = 0;
            for (int i = 0; i < N; i++) {
                if (cargo.isEmpty() || cargo.size() <= index) { // 비거나 cargo보다 큰 범위를 탐색하는 경우
                    break;
                }
                int now = cargo.get(index);
                if (crane[i] >= now) { // 지워지면 자동으로 원소가 밀림
                    cargo.remove(index);
                } else { // 지우지 못하면 index를 늘림
                    index++;
                    i--;
                }
            }
            count++;
        }

        bw.write(count + "\n");

        bw.flush();
        br.close();
        bw.close();
    }
}