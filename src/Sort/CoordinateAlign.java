package Sort;

import java.io.*;
import java.util.*;

class SaveCoord implements Comparable<SaveCoord>{
    int x;
    int y;

    public SaveCoord(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(SaveCoord o) {
        if(this.x == o.x){
            return this.y - o.y;
        }
        return this.x - o.x;
    }
}

public class CoordinateAlign {
    static int N; // 좌표 개수

    static ArrayList<SaveCoord> coor; // 좌표저장
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        coor = new ArrayList<>();
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);

            coor.add(new SaveCoord(x, y));
        }

        Collections.sort(coor);

        for(int i=0; i<N; i++){
            bw.write(coor.get(i).x + " " + coor.get(i).y + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}