package Stack;

import java.io.*;
import java.util.*;

public class IronPipe {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = br.readLine().split("");

        Stack<String> stack = new Stack<>();

        int piece = 0; // 조각 개수
        int count = 0;
        ArrayList<Integer> countSave = new ArrayList<>();

        for (int i = 0; i < s.length; i++) {
            if (s[i].equals("(")) {
                stack.push(s[i]);
                count++;
                countSave.add(count);
            } else if (s[i].equals(")")) {
                count--;
                if (!stack.isEmpty()) {
                    stack.pop();
                    if (countSave.size() > 2) { // 카운트 저장 사이즈가 2이하 일때는 0일 수 밖에 없음. 그래서 2이상 일 때 아래의 조건문을 실행
                        if (countSave.get(countSave.size() - 1) < countSave.get(countSave.size() - 2)) { // 만약 사이즈가 작아지면 한 파이프의 끝이기 때문에 1개만 늘리기
                            piece++;
                        } else { // 사이즈의 변동이 없으면 모든 파이프를 자르기 때문에 스택의 사이즈만큼 추가
                            piece += count;
                        }
                    } else {
                        piece += count;
                    }
                    countSave.add(count); // 나중에 써야 이전의 값이랑 비교 가능
                }
            }
        }
        if (count == 0)
            System.out.println(piece);
        else
            System.out.println("error");

    }
}