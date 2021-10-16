package QueueAndDeque;

import java.io.*;
import java.util.*;

public class AC {
    static boolean isError;
    static boolean isReverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        for (int i = 0; i < T; i++) {
            isError = false; // 초기화
            isReverse = false; // 초기화
            Deque<Integer> dq = new LinkedList<>();
            String[] str = br.readLine().split(""); // 수행 함수

            int n = Integer.parseInt(br.readLine()); // 덱의 크기
            String[] arrayStr = br.readLine().replace("[", "").replace("]", "").split(","); // "["와 "]"는 지워주고 ","기준으로 수를 나눔.

            for (int j = 0; j < n; j++) {
                dq.addLast(Integer.parseInt(arrayStr[j])); // 덱에 저장
            }

            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("R")) { // 리버싱
                    if (isReverse) {
                        isReverse = false;
                    } else {
                        isReverse = true;
                    }
                } else if (str[j].equals("D")) { // 딜리트
                    if (!dq.isEmpty()) {
                        Delete(dq);
                    } else {
                        isError = true; // 값이 없는데 삭제 시도
                        break; // 이미 에러가 난 상태
                    }
                }

            }
            print(dq);

        }
        bw.flush();
        br.close();
        bw.close();

    }

    // delete 구간
    static void Delete(Deque<Integer> dq) {
        if (isReverse) { // 뒤집어져 있을 경우
            dq.removeLast();
        } else { // 안뒤집어져 있을 경우
            dq.removeFirst();
        }
    }

    // 출력부
    static void print(Deque<Integer> dq) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // dq에 값이 없을 때 삭제한 경우
        if (isError) {
            bw.write("error\n");
            bw.flush();
            return;
        }
        bw.write("[");
        if (isReverse) { // 뒤집어져 있을 경우
            while (!dq.isEmpty()) {
                bw.write(dq.pollLast() + "");
                if (dq.size() != 0) { // 마지막 값이 나오면 ,를 붙이지 않음
                    bw.write(",");
                }
            }
        } else {
            while (!dq.isEmpty()) {
                bw.write(dq.pollFirst() + "");
                if (dq.size() != 0) { // 마지막 값이 나오면 ,를 붙이지 않음
                    bw.write(",");
                }
            }
        }
        bw.write("]\n");
        bw.flush();
    }
}