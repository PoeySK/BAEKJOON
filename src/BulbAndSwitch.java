import java.io.*;
import java.util.*;

public class BulbAndSwitch {
    static int N, result = -1;
    static String init, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        init = st.nextToken();
        st = new StringTokenizer(br.readLine());
        target = st.nextToken();
        br.close();

        /* 작동 */
        run();

        /* 출력 */
        bw.write(result + "\n");
        bw.close();
    }

    private static void run() {
        // 첫번째 스위치 사용
        char[] useFirst = init.toCharArray();
        useFirst[0] = useFirst[0] == '0' ? '1' : '0';
        useFirst[1] = useFirst[1] == '0' ? '1' : '0';
        int cntUse = process(useFirst, 1);
        boolean isCheckUse = isCheck(useFirst);

        // 첫번째 스위치 미사용
        char[] nonUseFirst = init.toCharArray();
        int cntNonUse = process(nonUseFirst, 0);
        boolean isCheckNonUse = isCheck(nonUseFirst);

        // check
        if (isCheckUse && isCheckNonUse) {
            result = Math.min(cntUse, cntNonUse);
            return;
        }
        if (isCheckUse) result = cntUse;
        if (isCheckNonUse) result = cntNonUse;
    }

    private static boolean isCheck(char[] check) {
        for (int i = 0; i < N; i++) {
            if (check[i] != target.charAt(i)) return false;
        }
        return true;
    }

    private static int process(char[] start, int cnt) {
        for (int i = 1; i < N; i++) {
            if (start[i - 1] != target.charAt(i - 1)) {
                cnt++;
                onClick(i, start);
            }
        }

        return cnt;
    }

    private static void onClick(int idx, char[] start) {
        for (int i = idx - 1; i <= idx + 1; i++) {
            if (i == -1 || i == N) continue;
            start[i] = start[i] == '0' ? '1' : '0';
        }
    }
}
