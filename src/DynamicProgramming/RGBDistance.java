package DynamicProgramming;

import java.io.*;

public class RGBDistance {
    static int N; // 집의 개수
    static int[][] RGB; // rgb input
    static int[][] s; // solution table

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        RGB = new int[N][3];

        for (int i = 0; i < N; i++) {
            String[] rgb = br.readLine().split(" ");
            int red = Integer.parseInt(rgb[0]);
            int green = Integer.parseInt(rgb[1]);
            int blue = Integer.parseInt(rgb[2]);

            RGB[i][0] = red;
            RGB[i][1] = green;
            RGB[i][2] = blue;
        }

        s = new int[N][3];
        dp();

        int firstMin = Math.min(s[N - 1][0], s[N - 1][1]);
        int resultMin = Math.min(firstMin, s[N - 1][2]);
        bw.write(resultMin + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dp() {
        /*
         * 맞닿는 면은 더할 수 없기 때문에 RGB 3경우를 따로 처리해줌.
         */
        for (int i = 0; i < N; i++) {
            if (i == 0) { // 초기값 지정
                s[i][0] = RGB[i][0];
                s[i][1] = RGB[i][1];
                s[i][2] = RGB[i][2];
            } else {
                int min;
                // j == 0
                min = Math.min(s[i - 1][1], s[i - 1][2]);
                s[i][0] = min + RGB[i][0];
                // j == 1
                min = Math.min(s[i - 1][0], s[i - 1][2]);
                s[i][1] = min + RGB[i][1];
                // j == 2
                min = Math.min(s[i - 1][0], s[i - 1][1]);
                s[i][2] = min + RGB[i][2];

            }
        }
    }
}