package Set;

import java.io.*;
import java.util.*;

public class ChaSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] n = br.readLine().split(" ");

        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();
        Set<Integer> cha = new TreeSet<>();

        String[] aput = br.readLine().split(" ");
        String[] bput = br.readLine().split(" ");

        for(int i=0; i<Integer.parseInt(n[0]); i++){
            a.add(Integer.parseInt(aput[i]));
        }
        for(int i=0; i<Integer.parseInt(n[1]); i++){
            b.add(Integer.parseInt(bput[i]));
        }

        a.removeAll(b);
        System.out.println(a.size());

        for(int chaput : a){
            cha.add(chaput);
        }

        Iterator<Integer> chaprint = cha.iterator();

        while(chaprint.hasNext()){
            System.out.print(chaprint.next() + " ");
        }


    }
}