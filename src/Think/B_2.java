package Think;

import java.io.*;
import java.util.*;

class SaveB_2 implements Comparable<SaveB_2> {
    int classroom;
    String name;

    public SaveB_2(int classroom, String name) {
        this.classroom = classroom;
        this.name = name;
    }
    
    /* 정렬 순서
    * 1. classroom
    * 2. name.length
    * 3. name.dir
    */
    @Override
    public int compareTo(SaveB_2 s) {
        if (this.classroom == s.classroom) {
            if (this.name.length() == s.name.length()) {
                return this.name.compareTo(s.name);
            }
            return this.name.length() - s.name.length();
        }
        return this.classroom - s.classroom;
    }
}

public class B_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        int N = Integer.parseInt(input1[0]); // 학급 수
        int M = Integer.parseInt(input1[1]); // 학급당 신청 가능한 인원 수

        ArrayList<SaveB_2> array = new ArrayList<>();
        int[] classroomCount = new int[N + 1];
        Arrays.fill(classroomCount, M); // 카운트 배열에 최대 인원 수를 채워줌.

        String input;
        int count = 0;
        while ((input = br.readLine()) != null) {
            String[] input2 = input.split(" ");
            if ((input2[0].equals("0") && input2[1].equals("0")) || count == 500) { // 종료 시점
                break;
            }

            int classroom = Integer.parseInt(input2[0]);
            String name = input2[1];

            classroomCount[classroom]--; // 그 학급에 인원이 들어오면 카운트를 줄임.

            if (classroomCount[classroom] >= 0) { // 그 학급의 카운트가 0이면 더이상 신청 불가
                array.add(new SaveB_2(classroom, name));
                count++;
            }
        }

        Collections.sort(array);

        ArrayList<SaveB_2> white = new ArrayList<>();
        ArrayList<SaveB_2> blue = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            int num = array.get(i).classroom;
            if (num % 2 == 1) { // odd, blue
                blue.add(new SaveB_2(num, array.get(i).name));
            } else { // even, white
                white.add(new SaveB_2(num, array.get(i).name));
            }
        }

        for (int i = 0; i < blue.size(); i++) {
            bw.write(blue.get(i).classroom + " " + blue.get(i).name + "\n");
        }
        for (int i = 0; i < white.size(); i++) {
            bw.write(white.get(i).classroom + " " + white.get(i).name + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

}