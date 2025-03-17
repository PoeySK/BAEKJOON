package Tree;

import java.io.*;
import java.util.*;

public class AntCave {
    static int N;
    static Node tree;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tree = new Node();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Node now = tree;
            for (int j = 0; j < K; j++) {
                String t = st.nextToken();
                if (!now.child.containsKey(t)) {
                    now.child.put(t, new Node());
                }
                now = now.child.get(t);
            }
        }
        br.close();

        /* 동작 */
        sb = new StringBuilder();
        run(tree, "");

        /* 출력 */
        bw.write(sb + "\n");
        bw.close();
    }

    private static void run(Node now, String dash) {
        if (now.child.isEmpty()) return;

        ArrayList<String> list = new ArrayList<>(now.child.keySet());
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            String food = list.get(i);
            sb.append(dash).append(food).append("\n");
            run(now.child.get(food), dash + "--");
        }
    }

    static class Node {
        Map<String, Node> child;

        Node() {
            this.child = new HashMap<>();
        }

        @Override
        public String toString() {
            return "child=" + child;
        }
    }
}
