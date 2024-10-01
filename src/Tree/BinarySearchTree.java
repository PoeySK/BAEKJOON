package Tree;

import java.io.*;

public class BinarySearchTree {
    static Node tree;
    static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        /* 입력 */
        int root = Integer.parseInt(br.readLine());
        tree = new Node(root);
        String input;
        while ((input = br.readLine()) != null) {
            int N = Integer.parseInt(input);
            tree.insert(N);
        }
        br.close();

        /* 작동 */
        run(tree);

        /* 출력 */
        bw.close();
    }

    private static void run(Node node) throws IOException { // 후위
        if (node.left != null) {
            run(node.left);
        }
        if (node.right != null) {
            run(node.right);
        }

        bw.write(node.key + "\n");
    }

    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }

        void insert(int N) {
            if (this.key > N) { // 왼쪽 자식
                if (this.left == null) {
                    this.left = new Node(N);
                } else {
                    this.left.insert(N);
                }
            } else { // 오른쪽 자식
                if (this.right == null) {
                    this.right = new Node(N);
                } else {
                    this.right.insert(N);
                }
            }
        }
    }
}
