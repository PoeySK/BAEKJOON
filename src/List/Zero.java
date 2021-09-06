package List;

import java.io.*;
import java.util.*;
public class Zero {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> array = new ArrayList<Integer>();


        for(int i=0; i<n; i++){
            int s = Integer.parseInt(br.readLine());
            if(s != 0){
                array.add(s);
            } else{
                array.remove(array.size()-1);
            }
        }
        int sum = 0;
        for (int i=0; i<array.size(); i++){
            sum += array.get(i);
        }
        System.out.println(sum);

    }

}
