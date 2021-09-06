package Map;

import java.io.*;
import java.util.*;

public class BestSell {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> title = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (title.containsKey(str)) {
                title.replace(str, title.get(str) + 1);
            } else {
                title.put(str, 1);
            }
        }

        Iterator<String> title_i = title.keySet().iterator();
        String key;
        Integer value;
        int max = 0;
        while (title_i.hasNext()) {
            key = title_i.next();
            value = title.get(key);
            if (max < value) {
                max = value;
            }
        }


        ArrayList<String> title_array = new ArrayList<String>(title.keySet());
        Collections.sort(title_array);
        for (int i = 0; i < title_array.size(); i++) {
            if(title.get(title_array.get(i)) == max){
                System.out.println(title_array.get(i));
                break;
            }
        }


    }
}