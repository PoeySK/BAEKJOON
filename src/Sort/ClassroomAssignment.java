package Sort;

import java.io.*;
import java.util.*;

class SaveCA implements Comparable<SaveCA> {
    int startTime;
    int endTime;

    public SaveCA(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int compareTo(SaveCA s) {
        return this.startTime - s.startTime;
    }
}

public class ClassroomAssignment {
    static int N; // 강의 개수
    static ArrayList<SaveCA> ag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        ag = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int sTime = Integer.parseInt(input[0]); // 시작 시간
            int eTime = Integer.parseInt(input[1]); // 종료 시간

            ag.add(new SaveCA(sTime, eTime));
        }

        Collections.sort(ag); // 강의 시작 시간에 따른 오름차순

        // 강의 종료 시간에 따른 오름차순
        PriorityQueue<SaveCA> pq = new PriorityQueue<>(new Comparator<SaveCA>() {
            @Override
            public int compare(SaveCA s1, SaveCA s2) {
                return s1.endTime - s2.endTime;
            }
        });
        pq.offer(ag.get(0)); // 가장 빨리 시작하는 강의를 넣어줌.
        for (int i = 1; i < N; i++) {
            int end = pq.peek().endTime; // 시간이 가장빠른 강의의 종료 시간을 저장.
            /*
             * 종료 시간보다 큰 시작 시간이 있으면 이전 값은 삭제 후
             * 큰 값을 넣어줌.
             * 조건문을 충족하지 않으면 그냥 pq에 넣어줌.
            */
            if(end <= ag.get(i).startTime){
                pq.poll();
            }

            pq.offer(ag.get(i));
        }

        bw.write(pq.size() + "\n"); // pq의 사이즈는 곧 강의실의 개수
        
        bw.flush();
        br.close();
        bw.close();
    }
}