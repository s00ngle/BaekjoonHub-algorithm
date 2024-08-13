import java.io.*;
import java.util.StringTokenizer;

public class Solution
{

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1 ; t <= 10 ; t++) {
            StringTokenizer st;

            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[100][100];
            int start = 0;

            for (int i = 0 ; i < 100 ; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0 ; j < 100 ; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0 ; i < 100 ; i++) {
                if (arr[99][i] == 2) {
                    start = i;
                }
            }

            int col = start;
            for (int row = 99 ; row >= 0 ; row--) {
                if (col > 0 && arr[row][col - 1] == 1) {
                    while (col > 0 && arr[row][col - 1] == 1) {
                        col--;
                    }
                    continue;
                }
                if (col < 99 && arr[row][col + 1] == 1) {
                    while (col < 99 && arr[row][col + 1] == 1) {
                        col++;
                    }
                }
            }

            System.out.println("#" + n + " " + col);
        }
    }
}