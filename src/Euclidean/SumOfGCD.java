package Euclidean;

import java.io.*;
import java.util.*;

public class SumOfGCD {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 각 테스트 케이스마다 수의 개수는 임의로 정하기 때문에 리스트 배열 선언.
        ArrayList<Integer>[] array = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            array[i] = new ArrayList<>();
            String[] s = br.readLine().split(" ");

            int testcase = Integer.parseInt(s[0]);
            for (int j = 1; j <= testcase; j++) {
                array[i].add(Integer.parseInt(s[j]));
            }
        }
        for (int i = 0; i < N; i++) {
            Collections.sort(array[i]); // 오름차순 정렬
        }

        int list_index = 0; // 리스트의 인덱스 | array[___]
        int m = 1; // 큰값부터 내림차순으로 찾기위해 최대인덱스에서 변수를 이용해 빼줌.
        int result;
        int array_index = 0; // 배열의 인덱스 | array[list_index].get(___);
        long[] sum = new long[N];
        while (true) {
            int max = array[list_index].get(array[list_index].size() - m);
            int min = array[list_index].get(array_index);


            while (true) {
                result = max % min;
                max = min;
                min = result;

                if (result == 0) { // 나머지가 0이 나오면 두 값의 비교가 끝남.
                    array_index++;
                    sum[list_index] += max;
                    if (array_index == array[list_index].size() - m) { // 배열의 인덱스와 최대인덱스의 값이 같으면 다음 최대인덱스의 값으로 비교
                        m++;
                        array_index = 0;
                        if (array[list_index].size() == m) { // 최대인덱스를 다찾으면 다음 리스트로 넘어감.
                            list_index++;
                            m = 1;
                        }
                    }
                    break;
                }
            }
            if (list_index == N) {
                break;
            }
        }
        for (int i = 0; i < N; i++) {
            bw.write(sum[i] + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }
}