
import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[] dp;
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        
        dp= new int[1000001];
        dp[2]=1;
        dp[3]=1;
        for(int i =4 ; i<=N;i++){
            int[] temp;
            if(i%3==0 && i%2==0){
                temp=new int[] {dp[i-1]+1,dp[i/2]+1,dp[i/3]+1};
            }else if(i%3==0){
                temp=new int[] {dp[i-1]+1,dp[i/3]+1};
            }else if(i%2==0){
                temp=new int[] {dp[i-1]+1,dp[i/2]+1};
            }else{
                temp=new int[] {dp[i-1]+1};
            }
            Arrays.sort(temp);
            dp[i]=temp[0];
        }
        
        System.out.print(dp[N]);

    }
}
