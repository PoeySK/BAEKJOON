package Map;

import java.io.*;
import java.util.*;

public class Overtaking {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> firstCar = new HashMap<>();

        for (int i = 0; i < N; i++) { // 들어가는 차 입력
            String fs = br.readLine();

            firstCar.put(fs, i);
        }

        int[] lastCar = new int[N]; // 나가는 차 저장공간

        for (int i = 0; i < N; i++) { // 나가는 차 입력
            String ls = br.readLine();
            lastCar[i] = firstCar.get(ls); // 나가는 차의 인덱스 저장
        }

        int count = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (lastCar[i] > lastCar[j]) { // i는 앞차 j는 뒷차 i>j가 되면 뒷차가 앞차를 추월한 것임.
                    count++;
                    break;
                }
            }
        }

        System.out.println(count);

    }
}