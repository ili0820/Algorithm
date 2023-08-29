
import java.io.*;
import java.util.*;
public class  Main {
    static int N;
    static int[][] costs;
    static int[][] dp;
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        
        costs=new int[N][3];
        
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++)
            costs[i][j]=Integer.parseInt(st.nextToken());
        }

        dp=new int[N][3];
        //dp 는 i번째 집을 j 번째 색을 칠하는 경우
        //costs는 i번째 집을 j 번째 색으로 칠하는경우
        dp[0][0]=costs[0][0];
        dp[0][1]=costs[0][1];
        dp[0][2]=costs[0][2];
        for(int i=1;i<N;i++){
            dp[i][0]=Math.min(dp[i-1][1],dp[i-1][2])+costs[i][0];
            dp[i][1]=Math.min(dp[i-1][0],dp[i-1][2])+costs[i][1];
            dp[i][2]=Math.min(dp[i-1][1],dp[i-1][0])+costs[i][2];
        }
        int min_value=Integer.MAX_VALUE;
        for(int i=0;i<3;i++){
            min_value=Math.min(min_value,dp[N-1][i]);
        }
        System.out.print(min_value);
    }
}
