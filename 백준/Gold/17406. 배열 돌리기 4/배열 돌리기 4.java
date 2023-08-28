
import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;
// 실패 
public class Main {

    static int n,m,r;
    static int map[][];
    static int command[][];
    static int min;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        r = Integer.parseInt(s[2]);
        command = new int[r][3];
        map = new int[n][m];

        min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(s[j]);
            }
        }
        for (int i = 0; i < r; i++) {
            s = br.readLine().split(" ");
            int rr = Integer.parseInt(s[0])-1;
            int cc = Integer.parseInt(s[1])-1;
            int ss = Integer.parseInt(s[2]);

            command[i][0] = rr;
            command[i][1] = cc;
            command[i][2] = ss;

        }
        Perm(0,new int[r], new boolean[r]);

        System.out.println(min);
    }

    private static void Perm(int cnt, int[] arr, boolean[] visited) {
        if(cnt == r){
            Round(arr);
            return;
        }
        for (int i = 0; i < r; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            arr[cnt] = i;
            Perm(cnt+1,arr,visited);
            visited[i] = false;
        }
    }

    private static void Round(int[] arr) {
        int [][] tmp = MapCopy();
        for (int round = 0; round < r ; round++) {

            int r = command[arr[round]][0];
            int c = command[arr[round]][1];
            int S = command[arr[round]][2];
            for(int s=1; s<=S; s++) {

                int tempRRL = tmp[r-s][c-s]; // 오른쪽 아래
                int tempRRH =tmp[r-s][c+s]; // 오른쪽 위
                int tempLLL = tmp[r+s][c-s]; //왼쪽 아래
                
                
                for (int j = c-s; j <c+s ; j++) { //아래
                    tmp[r+s][j] = tmp[r+s][j+1];
                } //안겹

                for (int j = r-s; j <r+s; j++) { //왼쪽
                    tmp[j][c-s] = tmp[j+1][c-s];
                }

                tmp[r+s-1][c-s] = tempLLL; //겹치는 부분 처리


                for (int j = c+s; j > c-s; j--) { //위
                    tmp[r-s][j] = tmp[r-s][j-1];
                }
                
                tmp[r-s][c-s+1] = tempRRL;
                for (int j = r+s; j >r-s; j--) { //오른쪽
                    tmp[j][c+s] = tmp[j-1][c+s];
                }
                tmp[r-s+1][c+s] = tempRRH; //겹치는 부분 처리


            }
        }

//        Print(tmp);
        Count(tmp);
    }

    private static void Count(int[][] tmp) {
        for (int i = 0; i < tmp.length; i++) {
            int sum = Arrays.stream(tmp[i]).sum();
            if(sum < min) min = Math.min(sum,min);
        }

    }

    public static int[][] MapCopy() {
        int[][] tmp = new int[n][m];

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                tmp[i][j] = map[i][j];
            }
        }

        return tmp;
    }
//    private static void Print(int [][] data) {
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                sb.append(data[i][j]).append(" ");
//            }
//            sb.append("\n");
//        }
//        System.out.println(sb);
//    }
}
