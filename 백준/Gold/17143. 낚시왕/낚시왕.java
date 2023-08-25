
import java.io.*;
import java.util.*;

public class Main {
    static int R, C, M, answer;
    static int[][][] board;// 속력,이동방향,크기 저장하는 3차원 배열
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        answer = 0;
        board = new int[R][C][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            board[r - 1][c - 1] = new int[] { s, d, z };

        }
        // printarr(board);
        for (int i = 0; i < C; i++) {
            fishing_king(i);
            board = shark_move();
            // printarr(board);
        }
        System.out.print(answer);
    }

    public static void fishing_king(int x) {
        for (int i = 0; i < R; i++) {
            if (board[i][x][2] != 0) {
                answer += board[i][x][2];
                board[i][x] = new int[] { 0, 0, 0 };
                break;
            }
        }
    }

    public static int[][][] shark_move() {
        int[][][] next_board = new int[R][C][3];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j][2] != 0) {// 사이즈가 0이 아니면.
                    int s = board[i][j][0];
                    int d = board[i][j][1];
                    int z = board[i][j][2];
                    int[] temp = next_loc(i, j, s, d);
                    int nx = temp[0];
                    int ny = temp[1];
                    int nd = temp[2];
                    // for (int k = 0; k < s; k++) {
                    // if (!isin(nx+dx[d], ny+dy[d])) {// 밖으로나가면
                    // // 방향전환
                    // if (d % 2 == 0)// 위나 오른쪽이면
                    // d += 1;
                    // else
                    // d -= 1;
                    // }
                    // nx += dx[d];
                    // ny += dy[d];
                    // }

                    if (next_board[nx][ny][2] < z) {// 이미 이동한 상어가 있다면 z비교
                        next_board[nx][ny] = new int[] { s, nd, z };
                    }
                }
            }
        }

        return next_board;
    }

    public static boolean isin(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    public static int[] next_loc(int i, int j, int s, int d) {
        if (d < 2) {// d up or down
            int cycle = R * 2 - 2;
            if (d == 0)
                s += 2 * (R - 1) - i;
            else
                s += i;
            s %= cycle;
            if (s >= R) {
                return new int[] { 2 * R - 2 - s, j, 0 };
            } else {
                return new int[] { s, j, 1 };
            }
        } else {
            int cycle = C * 2 - 2;
            if (d == 3)
                s += 2 * (C - 1) - j;
            else
                s += j;
            s %= cycle;
            if (s >= C) {
                return new int[] { i, 2 * C - 2 - s, 3 };
            } else {
                return new int[] { i, s, 2 };
            }
        }
    }

    public static void printarr(int[][][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j][2] != 0)
                    System.out.print(1 + " ");
                else
                    System.out.print(0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
