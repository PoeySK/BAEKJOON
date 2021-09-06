package Sort;

import java.io.*;
import java.util.*;

public class Statistics {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] array = new int[N];
        int[] count = new int[8001]; // 절대값4000 -> *2 에러로 인한 +1
        ArrayList<Integer> save = new ArrayList<>();

        int sum = 0; // 1번을 구하기위한 모두 더한 값
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            array[i] = num;
            sum += array[i];
            count[array[i] + 4000]++;
        }
        double avg = (double) sum / N;
        System.out.println(Math.round(avg)); // 1번

        Arrays.sort(array);

        System.out.println(array[N / 2]); // 2번


        int max = 0;
        for (int i = 0; i < N; i++) { // 음수는 1~4000까지 저장 / 양수는 4000을 더해주어 4001~8000까지 저장
            max = Math.max(max, count[array[i] + 4000]);
        }

        for(int i=0; i<N; i++){
            if(max == count[array[i]+4000]){
                save.add(array[i]);
                count[array[i]+4000]--;
            }
        }

        if(save.size() > 1){
            System.out.println(save.get(1));
        } else {
            System.out.println(save.get(0));
        }

        System.out.println(Math.abs(array[array.length - 1] - array[0])); // 4번
    }
}
