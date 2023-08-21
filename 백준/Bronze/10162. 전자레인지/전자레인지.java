import java.io.*;
import java.util.*;
public class Main {
    static int A=5*60; //5분 저장
    static int B=1*60; //1분 저장
    static int C=10; //10초 저장
    
    static int[] answer={0,0,0};//정답 
    static int T;//T초
    static StringBuilder sb=new StringBuilder();//출력 저장할 스트링빌더
    static StringTokenizer st;//입력을 위한 스트링 토크나이저
    public static void main(String [] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        T=Integer.parseInt(st.nextToken());
        //최소 단위인 10 보다 크거나 같을 때 동안 반복
        while(T>=10){
            if(T>=A){//5분보다 크면 일단 5분 버튼 누르기
                int cnt=T/A;
                T-=(A*cnt);//요리시간에서 5분 뺴기
                answer[0]+=cnt;//A버튼 누른 횟수 +cnt
            }
            else if(T>=B){//이제 1분보다 크면 1분버튼 누르기.(5분 버튼 못 누를때)
                int cnt=T/B;;
                T-=(B*cnt);//요리시간 1분 빼기
                answer[1]+=cnt;//B버튼 누른 횟수 +1
            }
            else if(T>=C){//남은 시간 10초 이상일때(C버튼 누를수있을때)
                int cnt=T/C;;
                T-=(C*cnt);//요리시간 10초 뺴기
                answer[2]+=cnt;//C버튼 누른 횟수 +1
            }

        }
        if(T==0){//딱 떨어지면 맞추기 가능
            for(int a :answer)sb.append(a).append(" ");
            
        }else {//그외 맞추기 불가!
            sb.append(-1);
        }
        System.out.print(sb);
    }   
}
