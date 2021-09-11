package Sort;

import java.io.*;
import java.util.*;

// startsWith() 함수 사용 버전
public class PhoneNumberList {
    static String[] numList; // 맨 앞에 0이 올 수도 있기 때문에 String으로 배열을 선언
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine()); // 번호 개수
            numList = new String[n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine();

                numList[i] = s;
            }

            Arrays.sort(numList); // 오름차순 정렬

            if (isCheck()) { // true가 반환되면 중복된 것이 없음.
                bw.write("YES\n");
            } else { // false가 반환되면 중복된 것이 있음.
                bw.write("NO\n");
            }

        }

        bw.flush();
        br.close();
        bw.close();
    }

    public static boolean isCheck() {
        for (int i = 0; i < n - 1; i++) {
            /*
            * a.startWith(b): b에 있는 문자열이 a에 포함되어 있으면 true를 반환
            * 오름차순 정렬이기 때문에 옆만 검사를 해주면 됨.
            * (ex1. [12 123 54 127] | 이렇게 되어 있으면 정렬은 [12 123 127 54] 로 된다.
            * (ex2. [12 564 123 12 127] | 이렇게 되어 있으면 정렬은 [12 12 123 127 564]로 되어 바로 왼쪽만 비교하면 된다.
             */
            if (numList[i + 1].startsWith(numList[i])) {
                return false;
            }
        }

        return true;
    }

}
/*

1
3
911
97654599
91156877


1
5
113
12340
123440
12345
98346

 */