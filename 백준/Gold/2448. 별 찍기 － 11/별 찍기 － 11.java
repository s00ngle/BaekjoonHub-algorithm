
import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static char[][] star;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        star = new char[N][2 * N - 1];

        for (int i = 0; i < N; i++) {
            Arrays.fill(star[i], ' ');
        }

        draw(0, N - 1, N);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N - 1; j++) {
                sb.append(star[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void draw(int x, int y, int height) {
        if (height == 3) {
            star[x][y] = '*';
            star[x + 1][y - 1] = star[x + 1][y + 1] = '*';
            for (int i = -2; i <= 2; i++) {
                star[x + 2][y + i] = '*';
            }
        }
        else {
            draw(x, y, height / 2);
            draw(x + height / 2, y - height / 2, height / 2);
            draw(x + height / 2, y + height / 2, height / 2);
        }
    }
}


// .....*
// ....*.*
// ...*****
// ..*.....*
// .*.*...*.*
// *****.*****