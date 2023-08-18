
import java.io.*;
import java.util.*;

public class Main {
    static int R,C,answer;
    static StringBuilder sb=new StringBuilder();
    static StringTokenizer st;
    static int flag;
    static int[][] board;
    static int[] dx={0,0,-1,1};
    static int[] dy={1,-1,0,0};
    public static void main(String [] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        String input;
        board= new int[R][C];
        for(int i=0;i<R;i++){
            input=br.readLine();
            for(int j=0;j<C;j++)board[i][j]=input.charAt(j)-'A';
        }

        dfs(0,0,flag| 1<<board[0][0],1);
        System.out.println(answer);
        

    }

    
    public static void dfs(int x, int y,int flag,int cnt){
        answer=Math.max(answer,cnt);
        for(int i=0;i<4;i++){
            int nx=x+dx[i];
            int ny=y+dy[i];
            if(isin(nx,ny) && (flag & 1<<board[nx][ny])==0){
                dfs(nx,ny,(flag| 1<<board[nx][ny]), cnt+1);

            }
        }

    }
 

    public static boolean isin(int x,int y){
        return (0<=x&&x<R && 0<=y && y<C);}

}

