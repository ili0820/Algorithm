
import java.io.*;
import java.util.*;
public class Main {
    static int N,d,k,c,answer;
    static int[] arr;
    static LinkedHashSet <Integer> records = new LinkedHashSet <>();
    static StringTokenizer st;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        d=Integer.parseInt(st.nextToken());
        k=Integer.parseInt(st.nextToken());
        c=Integer.parseInt(st.nextToken());
        
        int[] arr=new int[N+k-1];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        }
        //init
        for (int i = 0; i < k-1; i++) {
			arr[N++]=arr[i];
		}

		int[] ate=new int[d+1];
		answer=1; // 쿠폰 먹었다고 치자
		ate[c]+=1;
        //처음 k 개 먹기
        int start=0;
		for (int i = start; i < k; i++) {
			if(ate[arr[i]]==0) {
				answer++;
			}
			ate[arr[i]]+=1;
		}
        start=0;

		
		int result=answer;
		for (int i = k; i < arr.length; i++) {
			ate[arr[start]]-=1;
			if(ate[arr[start]]==0) {
				result-=1;
			}
			if(ate[arr[i]]==0) result+=1;
			ate[arr[i]]+=1;
			answer=Math.max(answer, result);
			start++;
		}
        System.out.println(answer);
    }
}

