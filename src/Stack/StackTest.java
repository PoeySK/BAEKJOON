package Stack;

import java.io.*;
import java.util.*;

public class StackTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        ArrayList<Integer> Stack_array = new ArrayList<Integer>();
        int Last_element = 0;

        for(int i = 0; i < num; i++){
            String[] s = br.readLine().split(" ");

            if(s[0].equals("top")){ //제일 위에 있는 거 출력
                if(Stack_array.size() > 0) {
                    Last_element = Stack_array.get(Stack_array.size() - 1);
                    System.out.println(Last_element);
                } else
                    System.out.println("-1");
            }

            else if(s[0].equals("pop")){ // 빼낸 수를 출력
                if(Stack_array.size() > 0) {
                    Last_element = Stack_array.get(Stack_array.size() - 1);
                    System.out.println(Last_element);
                    Stack_array.remove(Stack_array.size() - 1);
                } else
                    System.out.println("-1");
            }

            else if(s[0].equals("empty")){ // 배열 안에 있으면 0 없으면 1
                if(Stack_array.size() != 0)
                    System.out.println("0");
                else
                    System.out.println("1");
            }

            else if(s[0].equals("size")){ // 배열의 크기 출력
                System.out.println(Stack_array.size());
            }

            else{ //배열 안에 넣기
                Stack_array.add(Integer.valueOf(s[1]));
            }

        }
    }
}
