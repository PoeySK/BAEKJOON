package QueueAndDeque;

import java.io.*;
import java.util.*;

public class AC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        for (int i = 0; i < T; i++) {
            Deque<Integer> dq = new LinkedList<>();
            String[] str = br.readLine().split(""); // 수행 함수
            int n = Integer.parseInt(br.readLine()); // 덱의 크기
            String[] arrayStr = br.readLine().replace("[", "").replace("]", "").split(","); // "["와 "]"는 지워주고 ","기준으로 수를 나눔.

            for (int j = 0; j < n; j++) {
                dq.addLast(Integer.parseInt(arrayStr[j])); // 덱에 저장
            }

            for (int j = 0; j < str.length; j++) {
                if (str[j].equals("R")) { // 리버싱
                    Collections.reverse((List<Integer>) dq);
                } else if (str[j].equals("D")) { // 딜리트
                    if (dq.size()>0) { // 비어있는데 버리는 함수를 사용하면 에러 발생.
                        dq.removeFirst();
                    } else {
                        break;
                    }
                }

            }
            if (!dq.isEmpty()) {
                bw.write(dq + "\n");
            } else {
                bw.write("error\n");
            }
            bw.flush();
        }
        br.close();
        bw.close();

    }
}