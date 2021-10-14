package Sort;

import java.io.*;
import java.util.*;

class SaveWS implements Comparable<SaveWS> {
    String word; // 단어
    int len; // 단어의 길이

    public SaveWS(String word, int len) {
        this.word = word;
        this.len = len;
    }

    @Override
    public int compareTo(SaveWS s) {
        if (this.len == s.len) { // 단어의 길이가 같을 경우
            return this.word.compareTo(s.word); // 알파벳순
        }
        return this.len - s.len;
    }
}

public class WordSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<SaveWS> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            array.add(new SaveWS(input, input.length()));
        }
        Collections.sort(array);

        /*
        * 정렬된 상태에선 같은 단어가 연속적으로 나옴
        * 앞의 단어와 지금 단어만 비교하면 공통 단어 확인 가능
        */
        String saveWord = array.get(0).word;
        bw.write(array.get(0).word + "\n");
        for (int i = 1; i < N; i++) {
            if (!saveWord.equals(array.get(i).word)) {
                bw.write(array.get(i).word + "\n");
                saveWord = array.get(i).word;
            }
        }
        bw.flush();
        br.close();
        bw.close();

    }
}