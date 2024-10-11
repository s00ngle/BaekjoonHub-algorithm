import java.io.*;
import java.util.*;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
 
    static int N;
    static int W;
    static int H;
 
    static int answer;
 
    public static void main(String[] args) throws IOException {
 
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
 
            int[][] board = new int[H][W];
 
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
//            board = gravity(board);
//
//            for (int[] row : board) {
//                for (int num : row) {
//                    System.out.print(num + " ");
//                }
//                System.out.println();
//            }
            answer = Integer.MAX_VALUE;
 
            DFS(0, board);
 
            sb.append("#").append(t).append(" ").append(answer).append('\n');
        }
        System.out.println(sb);
    }
 
    static void DFS(int depth, int[][] board) {
        if (depth == N) {
            answer = Math.min(answer, cntBoard(board));
            return;
        }
 
        for (int i = 0; i < W; i++) {
            int[][] temp = BFS(board, i);
            DFS(depth + 1, temp);
        }
    }
 
    static int[][] BFS(int[][] board, int col) {
 
        int[][] temp = new int[H][W];
 
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                temp[i][j] = board[i][j];
            }
        }
        int row = getRow(temp, col);
        if (row == -1) return temp;
 
        Queue<int[]> q = new LinkedList<>();
 
        q.offer(new int[]{row, col});
 
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];
            int power = temp[curr[0]][curr[1]];
 
            temp[curr[0]][curr[1]] = 0;
 
            // 4 방향 (power - 1) 거리 만큼 pq에 offer
            // board 인덱스 벗어나지 않게 확인
 
            // 값이 0이라면 pq에 넣지 않음
            // 만약 1번 벽돌이라면 그냥 pq에 넣지 않고 바로 0으로 만들기
            // 2 이상만 넣기
 
            int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] dir : dirs) {
                int nr = r;
                int nc = c;
                for (int i = 1; i < power; i++) {
                    nr += dir[0];
                    nc += dir[1];
 
                    if (nr < 0 || nc < 0 || nr >= H || nc >= W) {
                        continue;
                    }
 
                    if (temp[nr][nc] > 1) {
                        q.offer(new int[]{nr, nc});
                    }
                    else {
                        temp[nr][nc] = 0;
                    }
                }
            }
        }
 
        // 빈 공간이 생겼으면 밑으로 내리기
        temp = gravity(temp);
 
        return temp;
    }
 
    private static int cntBoard(int[][] board) {
        int cnt = 0;
 
        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                if (board[row][col] > 0) {
                    cnt++;
                }
            }
        }
 
        return cnt;
    }
 
    private static int[][] gravity(int[][] board) {
 
        int[][] temp = new int[H][W];
 
        for (int col = 0; col < W; col++) {
            Queue<Integer> q = new LinkedList<>();
            for (int row = 0; row < H; row++) {
                if (board[row][col] > 0) {
                    q.add(board[row][col]);
                }
            }
            for (int row = H - q.size(); row < H; row++) {
                temp[row][col] = q.poll();
            }
        }
 
        return temp;
    }
 
    static int getRow(int[][] board, int col) {
        for (int row = 0; row < H; row++) {
            if (board[row][col] != 0) {
                return row;
            }
        }
        return -1;
    }
}