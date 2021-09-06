package Stack;

import java.io.*;
import java.util.*;

public class Obig {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] NGE = br.readLine().split(" ");

        int[] first = new int[N];

        for (int i=0; i<N; i++){
            first[i] = Integer.parseInt(NGE[i]);
        }

        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<N; i++){
            /* 스택이 비어있지 않고
             * first 배열에 인덱스를 하나 넣고 비교 하나 넣고 비교를 반복한다.
             * 이렇게 되면 자동으로 i번째보다 이전의 인덱스 들이 저장되어 비교를 할 수 있다.
             * 만약 이전 인덱스의 first보다 i번째 first의 값이 더 크면
             * 그 때 인덱스를 pop하여 i번째의 값으로 바꿔준다.
             * ex) 3 5 2 7이라고 가정하자.
             * 그러면 stack안에는 0이 저장 된다.
             * 이제 first[stack.peek()] 은 frist[0]이 된다.
             * 그러면 아래의 조건에 따라 first[0] < first[1]이 될 수 있다.
             * 그때 인덱스 0의 위치에 인덱스 1의 값을 넣어 준다.
             * 반복이다.
             * 만약 2개이상이 스택에 저장되면 그 스택이 다 빠져나갈 때까지 반복한다.
             */
            while(!stack.isEmpty() && first[stack.peek()] < first[i]){
                first[stack.pop()] = first[i];
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            first[stack.pop()] = -1;
        }

        for(int i=0; i<N; i++){
            bw.write(first[i] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}