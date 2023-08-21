import java.io.*;
import java.util.*;
public class Main {

    static int N,score,time;
    // static Stack<Character[]> tasks = new ArrayListM<();
    static Deque<Integer[]> tasks = new ArrayDeque<Integer[]>();

    static StringBuilder sb=new StringBuilder();//출력 저장할 스트링빌더
    static StringTokenizer st;//입력을 위한 스트링 토크나이저
    public static void main(String [] args) throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());

        //입력받기
        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());//한줄씩 읽어서 공백기준 나누기.
            if (st.countTokens()==3){//새로운 태스크가 들어왔다면
                //임시로 temp 라는 배열에 저장
                Integer [] temp=new Integer[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
                temp[2]-=1;//받자마자 일하니까 -1분
                if (temp[2]==0)score+=temp[1];//일이 다끝난다면 점수추가
                else tasks.push(temp);//안끝난다면 스택에 넣기
            }
            else{
                if(tasks.isEmpty())continue;//스택이 비어있으면 그냥 넘어가기
                Integer[] temp =tasks.pop();//지금 하던 일 pop
                temp[2]-=1;//일의 남은시간 -1분
                if (temp[2]==0)score+=temp[1];//일이 다끝난다면 점수 추가
                else tasks.push(temp);//안끝난다면 스택에 넣기
            }
        }
        System.out.print(score);

    }
}   
