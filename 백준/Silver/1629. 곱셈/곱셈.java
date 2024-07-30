import java.util.*;
import java.io.*;

public class Main {

    static long A, B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(pow(A, B));
    }

    static long pow(long a, long b) {
        if (b == 0) return 1;
        if (b == 1) return a % C;
        long val = pow(a, b / 2);
        if (b % 2 == 0) return val * val % C;
        else return val * val % C * a % C;
    }
}
