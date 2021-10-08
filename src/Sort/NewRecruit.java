package Sort;

import java.io.*;
import java.util.*;

class SaveNR implements Comparable<SaveNR> {
    int document;
    int interview;

    public SaveNR(int interview, int document) {
        this.document = document;
        this.interview = interview;
    }

    @Override
    public int compareTo(SaveNR s) {
        return this.document - s.document;
    }
}

public class NewRecruit {
    static int N;
    static ArrayList<SaveNR> score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int testcase = Integer.parseInt(br.readLine()); // 테스트 케이스
        while (testcase-- > 0) {
            N = Integer.parseInt(br.readLine()); // 지원자의 수
            int count = 1;
            score = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String[] input = br.readLine().split(" ");
                int document = Integer.parseInt(input[0]); // 서류 점수
                int interview = Integer.parseInt(input[1]); // 면접 점수

                score.add(new SaveNR(document, interview));
            }
            Collections.sort(score);

            int highRank = score.get(0).interview; // 면접 점수 1위 저장
            /*
            *  서류 점수로 정렬을 한 후 면접 점수의 최고 순위를 저장하여
            * 저장한 순위보다 더 좋은 순위가 있으면 서류 점수로 정렬이 되어 있으므로
            * highRank 값을 변경해 주어 현재 인덱스의 순위보다 면접 점수가 높아야 채용됨.
            */
            for (int i = 1; i < N; i++) {
                if (highRank > score.get(i).interview) {
                    count++;
                    highRank = score.get(i).interview;
                }
            }

            bw.write(count + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
/*

1
4
1 4
2 2
3 3
4 1
예상 답: 3 / 결과: 4
 */