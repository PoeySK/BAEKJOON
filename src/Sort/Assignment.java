package Sort;

import java.io.*;
import java.util.*;

class SaveTime implements Comparable<SaveTime> {
    int start;
    int end;

    public SaveTime(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(SaveTime s) { // 끝나는 시간에 따른 오름차순 정렬
        if (this.end == s.end) {
            return this.start - s.start;
        }
        return this.end - s.end;
    }
}

public class Assignment {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 회의의 수

        ArrayList<SaveTime> array = new ArrayList<>();

        int count = 1; // 최대 사용 가능한 회의의 개수, 무조건 한개의 회의는 진행함.

        for (int i = 0; i < N; i++) {
            String[] time = br.readLine().split(" ");
            int startTime = Integer.parseInt(time[0]);
            int endTime = Integer.parseInt(time[1]);

            array.add(new SaveTime(startTime, endTime));
        }

        Collections.sort(array);

        int MinEndTime = array.get(0).end;
        for (int i = 1; i < N; i++) {
            if (MinEndTime <= array.get(i).start) {
                MinEndTime = array.get(i).end;
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
    }
}
/*
11
12 14
2 13
8 12
6 10
8 11
1 4
0 6
3 5
5 9
5 7
3 8

11
1 4
3 5
0 6
5 7
3 8
5 9
6 10
8 11
8 12
2 13
12 14
 */