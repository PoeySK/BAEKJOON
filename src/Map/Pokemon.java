package Map;

import java.util.*;
import java.io.*;

public class Pokemon {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] N = br.readLine().split(" ");

        int dictionary = Integer.parseInt(N[0]);
        int question = Integer.parseInt(N[1]);

        Map<String, String> save = new HashMap<>();

        int index = 1;
        for(int i=0; i<dictionary; i++){
            String pokemon = br.readLine();
            String stringIndex = String.valueOf(index);
            save.put(pokemon, stringIndex);
            save.put(stringIndex, pokemon);
            index++;
        }

        for(int i=0; i<question; i++){
            String q = br.readLine();
            System.out.println(save.get(q));
        }

    }
}