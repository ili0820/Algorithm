
import java.io.*;
import java.util.*;
public class Main {
    static int N,M,D,answer;
    static int [][] board;
    static int [][] input;

    static StringBuilder sb=new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());

        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        D=Integer.parseInt(st.nextToken());
        
        board=new int[N][M];

        for(int i=0;i<N;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }


        combinations();
        System.out.print(answer);


        
    }


    public static boolean isin(int x,int y){
        return(0<=x && x< N && 0<=y && y<M);
    }
    public static int attack(int[] p){
        int[][] temp_board=new int[N][M];//원래 board deep copy
        for(int i=0;i<N;i++)
        temp_board[i]=board[i].clone();
        int[][] killed=new int[N][M];//죽인 적 기록하는 배열
        int result=0;//죽인 적의 수를 기록
        int[] archors=new int[3];
       
        int[] dx={0,-1,0};
        int[] dy={-1,0,1};

        
        //combination에서 궁수의 위치를 뽑아서 archors에 저장
        int idx=0;
        for(int i=0;i<M;i++)
                if(p[i]==1)archors[idx++]=i;
        // System.out.println(Arrays.toString(archors));
        //궁수를 아래에서 위로 올리면서 탐색
        int[] now;//큐에서 뽑은 값 저장 할 배열
        for(int i=N-1;i>-1;i--){
            List<int[]> this_turn=new ArrayList<>();
            for(int j :archors){
                Queue<int[]> q=new ArrayDeque<int[]>();
                q.offer(new int[] {i,j,1});//첫 시작은 distance = 1
                while(!q.isEmpty()){
                    now=q.poll();
                    int x=now[0];
                    int y=now[1];
                    int d=now[2];
                    if(temp_board[x][y]==1){
                        this_turn.add(new int[] {x,y});
                        if(killed[x][y]==0){
                            killed[x][y]=1;
                            result+=1;
                            
                        }
                        break;
                    }
                    if(d<D){
                        for(int k=0;k<3;k++){
                            int nx=x+dx[k];
                            int ny=y+dy[k];
                            if(isin(nx,ny))
                            q.offer(new int[] {nx,ny,d+1});
                        }
                    }

                }

            }
            for(int[] l:this_turn){
                temp_board[l[0]][l[1]]=0;
            }
        
        }
        // System.out.println(result);
        return result;
    }
    
    public static void combinations(){
        int[] p = new int[M];//M개의 자리에 궁수를 배치 가능.
        int cnt=0;
        while(++cnt<=3)p[M-cnt]=1;
        
        do{
            //p배열을 이용한 조합 확인
            answer=Math.max(answer,attack(p));

        }while(np(p));

    }

    private static boolean np(int[] p){
        int N=p.length;

        int i= N-1;
        while(i>0 && p[i-1]>=p[i])--i;
        if(i==0) return false;
        int j=N-1;
        while(p[i-1]>=p[j]) --j;
        swap(p,i-1,j);
        int k= N-1;
        while(i<k){
            swap(p,i++,k--);
        }

        return true;
    }
    private static void swap(int[] p, int a, int b){
        int temp=p[a];
        p[a]=p[b];
        p[b]=temp;
    }


}
