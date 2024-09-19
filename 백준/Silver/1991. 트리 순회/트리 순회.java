import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;

    static int N;

    static class Node {
        char left;
        char right;
        public Node(char left, char right) {
            this.left = left;
            this.right = right;
        }
    }

    static HashMap<Character, Node> tree = new HashMap<>();

    static void preorder(char x) {
        Node now = tree.get(x);
        sb.append(x);

        if (now.left != '.') {
            preorder(now.left);
        }
        if (now.right != '.') {
            preorder(now.right);
        }
    }

    static void inorder(char x) {
        Node now = tree.get(x);

        if (now.left != '.') {
            inorder(now.left);
        }

        sb.append(x);

        if (now.right != '.') {
            inorder(now.right);
        }
    }

    static void postorder(char x) {
        Node now = tree.get(x);

        if (now.left != '.') {
            postorder(now.left);
        }

        if (now.right != '.') {
            postorder(now.right);
        }

        sb.append(x);
    }

    public static void main(String[] args) throws Exception {

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char root = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree.put(root, new Node(left, right));
        }

        sb = new StringBuilder();
        preorder('A');
        System.out.println(sb);

        sb = new StringBuilder();
        inorder('A');
        System.out.println(sb);

        sb = new StringBuilder();
        postorder('A');
        System.out.println(sb);
    }
}
