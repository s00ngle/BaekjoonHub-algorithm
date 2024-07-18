
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int set = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int num = 0;
            switch (command) {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    set |= 1 << (num - 1);
                    break;
                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    set &= ~(1 << (num - 1));
                    break;
                case "check":
                    num = Integer.parseInt(st.nextToken());
                    sb.append((set & 1 << (num - 1)) > 0 ? 1 : 0).append("\n");
                    break;
                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    set ^= 1 << (num - 1);
                    break;
                case "all":
                    set |= ~0;
                    break;
                case "empty":
                    set &= 0;
                    break;
            }
        }
        System.out.println(sb.toString());
    }
}
