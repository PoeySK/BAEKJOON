package BruteForcing;

import java.io.*;
import java.util.*;

public class StartAndLink {
    static int N;
    static int[] selectTeam, selectAbility;
    static ArrayList<ArrayList<Integer>> team, startTeam, linkTeam;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        N = Integer.parseInt(br.readLine());
        int[][] ability = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(input[j]);
            }
        }
        br.close();

        /* 작동 */
        selectTeam = new int[N / 2];
        selectAbility = new int[2];
        team = new ArrayList<>();
        combinationTeam(0, 0); // 가능한 경우 찾기
        int result = run(ability);

        /* 출력 */
        bw.write(result + "\n");
        bw.flush();
        bw.close();
    }

    private static int run(int[][] ability) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < team.size() / 2; i++) {
            min = Math.min(min, compare(ability, i));
        }

        return min;
    }

    // team 나누기 (n) Combination (2/n)
    private static void combinationTeam(int idx, int k) {
        if (k == selectTeam.length) {
            team.add(new ArrayList<>());
            for (int i = 0; i < k; i++) {
                team.get(team.size() - 1).add(selectTeam[i]);
            }

            return;
        }
        if (idx == N) {
            return;
        }
        selectTeam[k] = idx;
        combinationTeam(idx + 1, k + 1);
        combinationTeam(idx + 1, k);
    }

    private static int compare(int[][] ability, int idx) {
        startTeam = new ArrayList<>(); // 새로운 team이 구성될 때 마다 초기화
        combinationStartTeam(team.get(idx), 0, 0); // start 팀에 넣을 i와 j 추출
        
        linkTeam = new ArrayList<>(); // 새로운 team이 구성될 때 마다 초기화
        combinationLinkTeam(team.get(team.size() - 1 - idx), 0, 0); // link 팀에 넣을 i와 j 추출

        int start = 0;
        int link = 0;
        for (int i = 0; i < startTeam.size(); i++) {
            int si = startTeam.get(i).get(0);
            int sj = startTeam.get(i).get(1);
            int li = linkTeam.get(i).get(0);
            int lj = linkTeam.get(i).get(1);

            start += ability[si][sj] + ability[sj][si];
            link += ability[li][lj] + ability[lj][li];

        }
        int diff = Math.abs(start - link);

        return diff;
    }

    // 나눈 팀에서 스타트팀의 능력 구하기, (2/n) Combination 2
    private static void combinationStartTeam(ArrayList<Integer> selectIdx, int idx, int k) {
        if (k == 2) {
            startTeam.add(new ArrayList<>());
            startTeam.get(startTeam.size() - 1).add(selectAbility[0]);
            startTeam.get(startTeam.size() - 1).add(selectAbility[1]);
            return;
        }
        if (idx == N/2) {
            return;
        }

        selectAbility[k] = selectIdx.get(idx);
        combinationStartTeam(selectIdx, idx + 1, k + 1);
        combinationStartTeam(selectIdx, idx + 1, k);

    }

    // 나눈 팀에서 링크팀의 능력 구하기
    private static void combinationLinkTeam(ArrayList<Integer> selectIdx, int idx, int k) {
        if (k == 2) {
            linkTeam.add(new ArrayList<>());
            linkTeam.get(linkTeam.size() - 1).add(selectAbility[0]);
            linkTeam.get(linkTeam.size() - 1).add(selectAbility[1]);
            return;
        }
        if (idx == N/2) {
            return;
        }

        selectAbility[k] = selectIdx.get(idx);
        combinationLinkTeam(selectIdx, idx + 1, k + 1);
        combinationLinkTeam(selectIdx, idx + 1, k);

    }
}
