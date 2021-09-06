package Map;

import java.io.*;
import java.util.*;

public class Ecology {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<String, Integer> tree = new HashMap<>();

        int total = 0;
        String input = "";
        while((input = br.readLine()) != null){
            String t = input;
            if(tree.containsKey(t)){
                tree.replace(t, tree.get(t) + 1);
            } else {
                tree.put(t, 1);
            }
            total++;
        }
        Object[] array = tree.keySet().toArray();
        Arrays.sort(array);

        for(Object s : array){
            String name = (String) s;
            Integer count = tree.get(name);
            double x = count/(double)total * 100;
            bw.write(name + " " + String.format("%.4f", x) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();

    }
}