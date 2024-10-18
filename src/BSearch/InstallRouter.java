package BSearch;

import java.io.*;
import java.util.*;

public class InstallRouter {
    static int N, C;
    static int[] homes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        homes = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            homes[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(homes);
        br.close();

        /* 작동 */
        int result = run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static int run() {
        int low = 0;
        int high = homes[homes.length - 1] - homes[0];
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            int count = 1; // 공유기 설치 개수
            int check = mid + homes[0];
            for (int i = 1; i < N; i++) { // 위치별 공유기 설치
                if (homes[i] >= check) { // 설치 가능
                    check = mid + homes[i];
                    count++;
                }
            }

            if (count >= C) {
                low = mid + 1;
            } else { // count < C
                high = mid - 1;
            }
        }

        return low - 1; // 마지막 mid + 1
    }
}
