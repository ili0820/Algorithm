
import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int [] arr=new int [9];
    static int [] p= new int[9];
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0;i<9;i++){
            st=new StringTokenizer(br.readLine());
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int cnt=0;
        while(++cnt<=7)p[9-cnt]=1;

        do{
            int sum=0;
            for(int i=0;i<9;i++){
                if(p[i]==0)continue;
                sum+=arr[i];
            }
            if (sum==100){
                for(int i=0;i<9;i++){
                    if(p[i]==0)continue;
                    sb.append(arr[i]).append("\n");
                }
                break;
            }

        }while(np(p));
        System.out.print(sb);
    }
    
    
    private static boolean np(int[] p){
        int N=p.length;

        int i=N-1;
        while(i>0&&p[i-1]>=p[i])--i;
        if(i==0)return false;

        int j=N-1;
        while(p[i-1]>=p[j]) --j;

        swap(p,i-1,j);

        int k=N-1;
        while(i<k)
            swap(p,i++,k--);
    
        return true;
        
    }

    private static void swap(int[] p, int x,int y){
        int temp=p[x];
        p[x]=p[y];
        p[y]=temp;
    }

    
    
}
