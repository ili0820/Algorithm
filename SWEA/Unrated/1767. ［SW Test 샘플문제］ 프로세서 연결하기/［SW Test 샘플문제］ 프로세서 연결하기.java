
import java.io.*;
import java.util.*;

public class Solution {
    static int T;
    static int N,total_cnt,total_len;
    static int[][] board;
    static ArrayList<Point> cores;
    static int[] dx= new int[] {0,0,-1,1};
    static int[] dy= new int[] {-1,1,0,0};
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            cores = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++)
                    board[i][j] = Integer.parseInt(st.nextToken());
            }
            //가장자리 제외 코어들 저장
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if(board[i][j]==1)cores.add(new Point(i,j));
                }
            }
            total_cnt=Integer.MIN_VALUE;
            total_len=Integer.MAX_VALUE;
            dfs(0,0,0);
            sb.append('#').append(tc).append(' ').append(total_len).append('\n');
        }
        System.out.print(sb);
    }
    public static void dfs(int depth,int cnt, int len){
        if(depth==cores.size()){
                // System.out.println(cnt);
            if(total_cnt<cnt){
                total_cnt=cnt;
                total_len=len;
            }
            else if(total_cnt==cnt){
                if(total_len>len)total_len=len;
            }
            return;
        }
        int x=cores.get(depth).x;
        int y=cores.get(depth).y;
        int nx,ny;
        for(int i=0;i<4;i++){
            int wires=0;
            boolean possible=false;
            nx=x;
            ny=y;
            while(true){
                nx+=dx[i];
                ny+=dy[i];
                if(!isin(nx,ny)){
                    possible=true;
                    break;
                }
                if(board[nx][ny]!=0)break;
                wires+=1;
            }
            if(possible){
                for(int j=1;j<=wires;j++){//와이어 설치
                    board[x+dx[i]*j][y+dy[i]*j]=1;
                }
                dfs(depth+1,cnt+1,len+wires);
                
                for(int j=1;j<=wires;j++){//와이어 철거
                    board[x+dx[i]*j][y+dy[i]*j]=0;
                }
            }
            else dfs(depth+1,cnt,len);
        }
    }
    public static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean isin(int x,int y){
        return 0<=x&&x<N && 0<=y&&y<N;
    }
}
