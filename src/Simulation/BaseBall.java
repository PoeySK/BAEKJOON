package Simulation;

import java.io.*;
import java.util.*;

public class BaseBall {
    static int N, start, result;
    static int[][] info; // 선수 정보
    static int[] lineup = new int[10]; // 타순
    static boolean[] isVisit = new boolean[10]; // 순열 방문 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        info = new int[N + 1][10];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        /* 작동 */
        lineup[4] = 1; // 4번 타순은 1번 선수 고정
        run(1);

        /* 출력 */
        bw.write(result + "\n");
        br.close();
        bw.close();
    }

    private static void run(int idx) {
        if (idx == 10) { // 선수 선발 완료
            int total = 0;
            start = 1;
            for (int i = 1; i <= N; i++) { // 이닝
                int nowScore = play(i);
                total += nowScore;
            }

            result = Math.max(result, total);
            return;
        }

        if (idx == 4) { // 4번 타순은 정해짐
            run(idx + 1);
            return;
        }

        // 선수 선발 과정, 순열
        for (int i = 2; i <= 9; i++) {
            if (!isVisit[i]) {
                isVisit[i] = true;
                lineup[idx] = i;
                run(idx + 1);
                isVisit[i] = false;
            }
        }
    }

    private static int play(int inning) { // 경기 시작
        boolean[] base = new boolean[4]; // true, false 로 베이스 주자 유무 확인

        int score = 0;
        int outCount = 0;
        int idx = start;
        while (outCount != 3) {
            /* 탐색은 3루 -> ,,, -> 1루 순으로 탐색 */
            int count = 0;
            int hit = info[inning][lineup[idx]]; // 현재 타순의 hit
            switch (hit) {
                case 0: // out
                    outCount++;
                    break;
                case 1: // 안타
                    if (base[3]) {
                        count++;
                        base[3] = false;
                    }
                    for (int i = 2; i > 0; i--) {
                        if (base[i]) {
                            base[i] = false;
                            base[i + 1] = true;
                        }
                    }
                    base[1] = true; // 1루에 서있기
                    break;
                case 2: // 2루타
                    for (int i = 3; i > 1; i--) {
                        if (base[i]) {
                            count++;
                            base[i] = false;
                        }
                    }
                    if (base[1]) {
                        base[1] = false;
                        base[3] = true;
                    }
                    base[2] = true; // 2루에 서있기
                    break;
                case 3: // 3루타
                    for (int i = 3; i > 0; i--) {
                        if (base[i]) {
                            count++;
                            base[i] = false;
                        }
                    }
                    base[3] = true; // 3루에 서있기
                    break;
                case 4: // 홈런
                    for (int i = 1; i <= 3; i++) {
                        if (base[i]) {
                            base[i] = false;
                            count++;
                        }
                    }
                    count++; // 본인 포함
                    break;
            } // switch 문 끝

            score += count;
            idx++;
            if (idx == 10) { // 9번 타자까지 타석에 선 경우
                idx = 1;
            }
        } // while 문 끝
        start = idx;
        return score;
    }
}
