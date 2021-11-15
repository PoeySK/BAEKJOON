package etc;
import java.io.*;
import java.util.*;

public class ThreeNumber {
    static int[] num;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        num = new int[input.length];
        for(int i = 0; i < 3; i++){ // 세 수로 고정 됨
            num[i] = Integer.parseInt(input[i]);
        }
        Arrays.sort(num); // 오름차순 정렬

        bw.write(num[1] + "\n"); // index == 1 두번째 수

        bw.flush();
        br.close();
        bw.close();
    }

}