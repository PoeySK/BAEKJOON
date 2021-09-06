package Tree;
import java.io.*;
import java.util.*;

class Node {
    char data;
    Node left;
    Node right;

    Node(char data) {
        this.data = data;
    }
}

class Tree {
    Node root;

    public void add(char data, char left, char right) {
        if (root == null) {
            if(data != '.'){
                root = new Node(data);
            }
            if (left != '.') {
                root.left = new Node(left);
            }
            if (right != '.') {
                root.right = new Node(right);
            }
        } else {
            search(root, data, left, right);
        }

    }

    public void search(Node root, char data, char left, char right) {
        if (root == null) {
            return;
        } else if (root.data == data) {
            if (left != '.') {
                root.left = new Node(left);
            }
            if (right != '.') {
                root.right = new Node(right);
            }
        } else {
            search(root.left, data, left, right);
            search(root.right, data, left, right);
        }
    }

    public void preorder(Node root) { // 전위 순회
        System.out.print(root.data);
        if (root.left != null) {
            preorder(root.left);
        }
        if (root.right != null) {
            preorder(root.right);
        }
    }

    public void inorder(Node root) { // 중위 순회
        if (root.left != null) {
            inorder(root.left);
        }
        System.out.print(root.data);
        if (root.right != null) {
            inorder(root.right);
        }
    }

    public void postorder(Node root) { // 후위 순회
        if (root.left != null) {
            postorder(root.left);
        }
        if (root.right != null) {
            postorder(root.right);
        }
        System.out.print(root.data);
    }

    public void levelorder(Node root){
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        String result = "";

        while(!queue.isEmpty()){
            Node current = queue.remove();

            result += " " + current.data;

            if(current.left != null){
                queue.add(current.left);
            }
            if(current.right != null){
                queue.add(current.right);
            }
        }
        System.out.print(result.substring(1));

    }
}

public class TreeCircuit {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        Tree tree = new Tree();

        for (int i = 0; i < n; i++) {
            char[] data;
            data = br.readLine().replaceAll(" ", "").toCharArray();
            tree.add(data[0], data[1], data[2]);
        }

        tree.preorder(tree.root);
        System.out.println();

        tree.inorder(tree.root);
        System.out.println();

        tree.postorder(tree.root);
        System.out.println();

        tree.levelorder(tree.root);

    }
}