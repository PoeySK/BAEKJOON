package DynamicProgramming;

import java.io.*;
import java.util.*;

public class CombineFile {
    static int K;
    static int[] files, total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = Integer.parseInt(st.nextToken());
        while (TC-- > 0) {
            /* 입력 */
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            files = new int[K];
            total = new int[K + 1]; // 누적합
            for (int i = 1; i <= K; i++) {
                files[i - 1] = Integer.parseInt(st.nextToken());
                total[i] = total[i - 1] + files[i - 1];
            }

            /* 작동 */
            int result = run();

            /* 출력 */
            bw.write(result + "\n");
        }
        br.close();
        bw.close();
    }

    private static int run() {
        int[][] sol = new int[K + 1][K + 1];
        for (int s = 1; s < K; s++) {
            for (int i = 1; i <= K - s; i++) {
                int now = i + s;
                sol[i][now] = Integer.MAX_VALUE;
                for (int j = i; j < now; j++) {
                    int section = total[now] - total[i - 1]; // 구간 합
                    sol[i][now] = Math.min(sol[i][now], sol[i][j] + sol[j + 1][now] + section);
                }
            }
        }

        return sol[1][K];
    }
}

/*
- testcase 2 -
  1  21  3  4  5  35  5  4  3  5  98  21  14  17  32
   \/     \/  /    \   \/    \/    \   \    \/   /
   22     7  /      \   9    8      \   \   31  /
     \     \/        \   \ /         \   \ /   /
      \   12          \  17           \   52  /
       \ /             \/              \   \ /
       34              52               \  84
         \            /                  \/
          \          /                  182
           \        /                   /
            \      /                   /
             \    /                   /
              \  /                   /
               \/                   /
               86                  /
                \                 /
                 \               /
                  \             /
                   \           /
                    \         /
                     \       /
                      \     /
                       \   /
                        \ /
                        268

22 + 7 + 9 + 8 + 31 + 12 + 17 + 52 + 34 + 52 + 84 + 182 + 86 + 268 = 864

0	1	21	3	4	5	35	5	4	3	5	98	21	14	17	32
--------------------------------------------------------------
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0
0	0	22	47	58	75	144	189	206	222	247	431	571	634	716	864
0	0	0	24	35	52	120	165	182	198	223	406	546	609	691	839
0	0	0	0	7	19	66	111	128	144	164	326	466	529	611	739
0	0	0	0	0	9	53	98	115	128	148	307	447	510	592	717
0	0	0	0	0	0	40	85	98	111	131	286	426	489	571	692
0	0	0	0	0	0	0	40	53	66	86	236	376	439	521	637
0	0	0	0	0	0	0	0	9	19	34	149	285	334	399	515
0	0	0	0	0	0	0	0	0	7	19	129	260	309	374	490
0	0	0	0	0	0	0	0	0	0	8	114	241	290	355	471
0	0	0	0	0	0	0	0	0	0	0	103	227	276	341	457
0	0	0	0	0	0	0	0	0	0	0	0	119	168	233	349
0	0	0	0	0	0	0	0	0	0	0	0	0	35	83	167
0	0	0	0	0	0	0	0	0	0	0	0	0	0	31	94
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	49
0	0	0	0	0	0	0	0	0	0	0	0	0	0	0	0

 */