package BruteForcing;

import java.io.*;
import java.util.*;

public class MakingPassword {
    static int L, C; // 암호 길이, 주어진 알파벳
    static char[] alpha, array; // 입력된 알파벳, 저장할 암호
    static boolean[] visit; // 방문 여부
    static ArrayList<String> solution;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        L = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        String[] s = br.readLine().split(" ");

        visit = new boolean[C];
        array = new char[L];
        alpha = new char[C];
        for (int i = 0; i < C; i++) {
            alpha[i] = s[i].charAt(0);
        }
        Arrays.sort(alpha);

        solution = new ArrayList<>();
        making(0, 0);

        for (int i = 0; i < solution.size(); i++) {
            bw.write(solution.get(i) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();

    }

    public static void making(int index, int count) { // index를 이용해 현재 탐색중인 배열의 위치를 저장, count를 이용해 단어의 길이를 잼.
        if (count == L) { // 암호길이 일치
            if (check()) { // 조건이 맞으면 solution 배열에 저장
                String str = new String(array);
                solution.add(str);
            }
            return;
        }

        for (int i = index; i < C; i++) { // 모든 암호의 경우를 검사
            if (visit[i]) { // 검사했던 암호일 경우 넘어감.
                continue;
            }
            visit[i] = true;
            array[count] = alpha[i];
            making(i, count + 1);
            visit[i] = false;
        }
    }

    public static boolean check() { // 최소 모음 1개, 최소 자음 2개 검사
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < L; i++) {
            if (array[i] == 'a' || array[i] == 'e' || array[i] == 'i' || array[i] == 'o' || array[i] == 'u') {
                vowelCount++;
            } else {
                consonantCount++;
            }

        }
        return vowelCount > 0 && consonantCount > 1;
    }
}
