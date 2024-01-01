package String;

import java.io.*;
import java.util.*;


public class WordStudy {
    static class Save {
        String key;
        int value;

        public Save(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> word = new HashMap<>();

        String[] s = br.readLine().split("");

        for (int i = 0; i < s.length; i++) {
            if (word.containsKey(s[i].toUpperCase())) {
                word.replace(s[i].toUpperCase(), word.get(s[i].toUpperCase()) + 1);
            } else {
                word.put(s[i].toUpperCase(), 1);
            }
        }

        Iterator<String> iter = word.keySet().iterator();
        ArrayList<Save> array = new ArrayList<>();

        while (iter.hasNext()) {
            String key = iter.next();
            Integer value = word.get(key);
            array.add(new Save(key, value));
        }
        Comparator<Save> com = new Comparator<Save>() {
            @Override
            public int compare(Save s1, Save s2) {
                return s2.value - s1.value;
            }
        };

        Collections.sort(array, com);

        if (array.size() > 1) {
            if (array.get(0).value == array.get(1).value) {
                System.out.println("?");
            } else {
                System.out.println(array.get(0).key);
            }
        } else {
            System.out.println(array.get(0).key);
        }

    }
}