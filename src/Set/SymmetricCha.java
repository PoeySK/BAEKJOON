package Set;

import java.io.*;
import java.util.*;

public class SymmetricCha {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] n = br.readLine().split(" ");

        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();
        Set<Integer> save_a = new HashSet<>();
        Set<Integer> save_b = new HashSet<>();

        String[] aput = br.readLine().split(" ");
        String[] bput = br.readLine().split(" ");

        for(int i=0; i<Integer.parseInt(n[0]); i++){
            a.add(Integer.parseInt(aput[i]));
        }
        for(int i=0; i<Integer.parseInt(n[1]); i++){
            b.add(Integer.parseInt(bput[i]));
        }

        Iterator<Integer> ai = a.iterator();
        Iterator<Integer> bi = b.iterator();

        while(ai.hasNext()){
            save_a.add(ai.next());
        }
        while(bi.hasNext()){
            save_b.add(bi.next());
        }

        save_a.removeAll(b);
        save_b.removeAll(a);

        System.out.print(save_a.size() + save_b.size());


    }
}