package BSearch;

import java.io.*;

public class Judge {
    static long[] timeTable;
    static long N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Long.parseLong(s[0]);
        M = Long.parseLong(s[1]);

        timeTable = new long[(int) N];

        long max = 0;
        for (int i = 0; i < N; i++) {
            timeTable[i] = Integer.parseInt(br.readLine());
            max = Math.max(timeTable[i], max);
        }

        System.out.println(getTime(max));

    }

    static long getTime(long max) { // 최대 시간
        long low = 1; //제일 적게 걸리는 시간, 1분이 최소
        long high = max * M; // 제일 많이 걸리는 시간, 가장 큰 시간과 인원수를 곱함
        long mid;
        long time = high; // 최종 시간

        while (low <= high) {
            mid = (low + high) / 2;

            if (isCheck(mid)) { // 중간 시간을 확인함.
                time = Math.min(mid, time);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return time;
    }

    static boolean isCheck(long mid) { // 그 시간이 모든 인원을 나눠서 더한 인원 수를 비교함.
        long check = 0;
        for (int i = 0; i < N; i++) {
            check += mid / timeTable[i];
        }
        if (check >= M) { // check가 총 인원수보다 많으면 high값을 줄이고, 같으면 그 시간을 출력.
            return true;
        } else { // check가 총 인원수보다 적으면 low값을 증가.
            return false;
        }
    }

}

// low = 1, high = 60 / mid = 30 / 4 3 = 7 / true
// low = 1, high = 31 / mid = 15 / 2 1 = 3 / false
// low = 16, high = 31 / mid = 24 / 3 2 = 5 / false
// low = 25, high = 31 / mid = 28 / 4 2 = 6 / trueㅇ
// low = 25, high = 27 / mid = 26 / 3 2 = 5 / false
// low = 27, high = 27 / mid = 27 / 3 2 = 5 / false


// ? ? ? ? ? ? ? , 11 / low = 1, high = 90 / mid = 45
// ? ? ? ? ? ? ? , 11 / low = 1, high = 44 / mid = 22
// 3 1 3 1 1 5 2 , 16 / low = 1, high = 21 / mid = 11
// 2 1 2 1 0 4 2 , 10 / low = 6, high = 10 / mid = 8 ㅇ
// 2 0 2 1 0 3 1 , 9  / low = 6, high = 7  / mid = 6
// 2 0 2 1 0 3 1 , 9  / low = 7, high = 7  / mid = 7