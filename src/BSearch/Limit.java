package BSearch;

import java.io.*;

public class Limit {
    static int N,M, factory1, factory2;
    static int[] factory;
    static int[][] dis;
    static long[] C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        factory = new int[N];

        for(int i =0; i<N; i++){ // 섬의 개수 저장
            factory[i] = (i + 1);
        }
        dis = new int[M][2];
        for(int i=0; i<M; i++){ // 섬 간의 가능 무게 저장
            String[] weightSave = br.readLine().split(" ");

            for(int j=0; j<2; i++) {
                dis[i][j] = Integer.parseInt(weightSave[j]);
            }

            C[i] = Long.parseLong(weightSave[2]);
        }
        for(int i=0; i<2; i++){
            String[] island = br.readLine().split(" ");

            factory1 = Integer.parseInt(island[0]);
            factory2 = Integer.parseInt(island[1]);
        }
        bfs();
    }
    public static void bfs(){
        for(int i=0; i<M; i++){
            for(int j=0; j<2; j++){
                if(i == factory1 && j == factory2){

                }
            }
        }
    }

    static void getWeight(){

    }
}
