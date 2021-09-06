package String;

import java.io.*;
import java.util.*;

public class AmountOfWord {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        String[] s = br.readLine().trim().split(" "); 스페이스바 한 칸을 기준으로 s에 넣어줌. ("a   b" 3개가 들어가면 가운데 공백을 읽게 됨.
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 모든 공백을 무시함.
        System.out.println(st.countTokens());
    }
}
