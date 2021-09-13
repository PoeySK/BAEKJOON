package Euclidean;

import java.io.*;
import java.util.*;

public class HateCoin {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ArrayList<Long> denoArray = new ArrayList<>();
        ArrayList<Long> moleArray = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            long molecules = Integer.parseInt(s[0]);
            long denominator = Integer.parseInt(s[1]);
            /*
             * 큰 값에서 작은 값을 나눠야 하기 때문에
             * maxValue와 minValue를 찾음.
             */
            long maxValue = Math.max(molecules, denominator);
            long minValue = Math.min(molecules, denominator);

            denoArray.add(denominator);
            moleArray.add(molecules);

            // 약분이 가능한 지 알기 위한 부분
            long g = GCD(maxValue, minValue);

            if (g > 1) { // 약분이 가능할 경우 기존 값을 삭제하고 약분한 값을 추가
                denoArray.remove(i);
                denoArray.add(denominator / g);
                moleArray.remove(i);
                moleArray.add(molecules / g);
            }


        }
        // 오름차순 정렬
        Collections.sort(denoArray);
        Collections.sort(moleArray);

        long m = moleArray.get(0);
        long d = denoArray.get(0);
        if (N == 1) {
            m = moleArray.get(0);
            d = denoArray.get(0);
        } else if (N == 2) {
            if (moleArray.get(0) == 1) {
                m = 1;
                d = LCM(denoArray.get(1), denoArray.get(0));
            } else {
                m = GCD(moleArray.get(1), moleArray.get(0));
                d = LCM(denoArray.get(1), denoArray.get(0));
            }
        } else { // 3개 이상일 경우
            /*
             * 재귀를 사용하여 최종 최소공배수와 최대공약수를 구한다.
             * 분자값중 최솟값이 1일 경우는 무조건 1이기 때문에 1로 따로 처리해주었다.
             */
            if (moleArray.get(0) == 1) {
                m = 1;
                for (int i = 1; i < N; i++) {
                    long max = Math.max(d, denoArray.get(i));
                    long min = Math.min(d, denoArray.get(i));
                    d = LCM(max, min);
                }
            } else {
                for (int i = 1; i < N; i++) {
                    long dmax = Math.max(d, denoArray.get(i));
                    long dmin = Math.min(d, denoArray.get(i));
                    d = LCM(dmax, dmin);

                    if (m != 1) {
                        long mmax = Math.max(m, moleArray.get(i));
                        long mmin = Math.min(m, moleArray.get(i));
                        m = GCD(mmax, mmin);
                    }
                }
            }
        }

        bw.write(m + " " + d + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    // 유클리드 호제법 사용
    public static long GCD(long a, long b) {
        long r;
        while (true) {
            r = a % b;
            a = b;
            b = r;

            if (r == 0) {
                break;
            }
        }
        return a;
    }

    public static long LCM(long a, long b) {
        long mul = a * b;
        return mul / GCD(a, b);
    }
}
/*
4
2 7
6 7
3 5
7 13

 */