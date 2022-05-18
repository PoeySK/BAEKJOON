package BruteForcing;

import java.io.*;

public class Minecraft {
    static int N, M, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        B = Integer.parseInt(s[2]);

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(input[j]);
            }
        }

        build(arr, B);
        br.close();
        bw.close();
    }

    public static void build(int[][] array, int block) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int inventory; // 현재 저장되어있는 블럭의 개수
        int minTime = Integer.MAX_VALUE;
        int h = 0;
        for (int k = 0; k <= 256; k++) { // k : 원하는 높이 / 0 ~ 256 모두 탐색
            inventory = block;
            int place = 0; // 설치할 블럭의 개수
            int delete = 0; // 제거할 블럭의 개수
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (array[i][j] > k) { // k 보다 현재 블럭의 높이가 높기 때문에 블럭을 깎아준다.
                        int diff = array[i][j] - k;
                        delete += diff;
                        inventory += diff; // 제거한 블럭을 인벤토리에 추가해줌.
                    } else if (array[i][j] < k) { // k 보다 현재 블럭의 높이가 낮기 때문에 블럭을 쌓아준다.
                        int diff = k - array[i][j];
                        place += diff;
                        inventory -= diff; // 필요한 블럭을 설치한 만큼 현재 가지고 있는 블럭을 제거해줌.
                    }
                }
            }
            if (inventory >= 0) { // 현재 인벤토리에 있는 블럭의 개수가 0보다 커야 평평한 땅을 만듦.
                int time = delete * 2 + place;
                if (minTime >= time) {
                    if(h > k) { // 높이가 더 높은 것을 채택
                        continue;
                    }
                    minTime = time;
                    h = k;
                }
            }
        }
        bw.write(minTime + " " + h + "\n");
        bw.flush();
    }
}
/*
3 4 11
29 51 54 44
22 44 32 62
25 38 16 2

 */