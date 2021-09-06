package Sort;

import java.io.*;
import java.util.*;

public class ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 사람 수
        String[] P = br.readLine().split(" "); // 한명당 걸리는 시간
        
        int[] person = new int[N];

        for (int i = 0; i < N; i++) {
            person[i] = Integer.parseInt(P[i]);
        }

        Arrays.sort(person); // 오름차순 정렬, 앞의 수가 작아야 최종 값이 작아짐

        int[] sum = new int[N]; // 한 사람당 걸리는 시간 저장
        int finallySum = 0; // 모든 사람이 걸린 시간 합
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) { // 한명씩 증가
                sum[i] += person[j]; // 자신과 앞에 있는 사람들이 걸리는 시간을 모두 더해줌.
            }
            
            finallySum += sum[i]; // 한 사람당 걸린 시간을 더해줌.
        }

        System.out.println(finallySum);
    }

}