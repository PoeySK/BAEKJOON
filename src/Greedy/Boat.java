package Greedy;

import java.io.*;
import java.util.*;

public class Boat {
    static int N, M, result;
    static ArrayList<Integer> crane = new ArrayList<>();
    static ArrayList<Integer> box = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            crane.add(Integer.parseInt(input[i]));
        }

        M = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            box.add(Integer.parseInt(input[i]));
        }
        br.close();

        /* 작동 */
        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());
        if (crane.get(0) < box.get(0)) { // 모든 박스를 못 옮기는 경우
            bw.write("-1\n");
            bw.close();
            return;
        }
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        while (!box.isEmpty()) {
            int idx = 0;
            result++;
            for (int i = 0; i < N; i++) {
                if(idx >= box.size()) {
                    break;
                }
                if (box.get(idx) <= crane.get(i)) {
                    box.remove(idx);
                    if (box.isEmpty()) { // 다 옮긴 경우
                        return;
                    }
                } else {
                    while(true) { // 옮길 수 있는 박스 찾기
                        idx++;
                        if(idx == box.size()) { // 현재 크레인으로 옮길 박스가 없는 경우
                            break;
                        }
                        if(box.get(idx) <= crane.get(i)) {
                            box.remove(idx);
                            break;
                        }
                    }
                }
            }
        }
    }
}
