package Stack;

import java.io.*;
import java.util.*;

public class World {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while((input = br.readLine()) != null){
            if(input.equals(".")){
                break;
            }
            String[] str = input.split("");

            Stack<String> pa = new Stack<>();

            for (int i=0; i<str.length; i++) {
                if (str[i].equals("(")) {
                    pa.push(str[i]);
                } else if (str[i].equals(")")) {
                    if(!pa.isEmpty() && pa.peek().equals("(")) {
                        pa.pop();
                    } else {
                        pa.push(str[i]);
                    }
                }
                if (str[i].equals("[")) {
                    pa.push(str[i]);
                } else if (str[i].equals("]")) {
                    if (!pa.isEmpty() && pa.peek().equals("[")) {
                        pa.pop();
                    } else {
                        pa.push(str[i]);
                    }
                }
            }
            if(pa.isEmpty()){
                System.out.println("yes");
            } else {
                System.out.println("no");
            }

        }

    }
}
//2,