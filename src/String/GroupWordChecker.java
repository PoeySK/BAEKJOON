package String;

import java.io.*;
import java.util.*;

public class GroupWordChecker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Character>> array = new ArrayList<>(); // 단어별 저장

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split("");
            array.add(new ArrayList<>());
            for (int j = 0; j < s.length; j++) {
                array.get(i).add(s[j].charAt(0)); // 단어 하나당 리스트 하나를 부여
            }
        }

        int[] alpha; // 알파벳 검사
        int count = 0;
        for (int i = 0; i < N; i++) {
            boolean isCheck = true;
            alpha = new int[26]; // 단어를 검사할때 마다 초기화.
            for (int j = 0; j < array.get(i).size(); j++) {
                alpha[array.get(i).get(j) - 97]++; // 소문자 a의 아스키코드는 97이므로 97를 빼주어 0으로 만듦.
                if (alpha[array.get(i).get(j) - 97] > 1) { // 값이 1이 넘으면 앞에서 한번 거쳤기 때문에 검사
                    // 그 직전의 값과 알파벳이 다르면 isCheck를 false로 값을 변경
                    if(array.get(i).size() > 1 && array.get(i).get(j - 1) - 97 != array.get(i).get(j) - 97){
                        isCheck = false;
                    }
                }
            }

            if(isCheck){
                count++;
            }
        }
        bw.write(count + "\n");
        bw.flush();
        br.close();
        bw.close();
    }
}