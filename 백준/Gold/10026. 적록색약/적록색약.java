
import java.io.*;
import java.util.*; 
public class Main {
    public static int N;
    public static Character[][] board;
    public static boolean[][] visited;
    public static Queue<int[]> q=new LinkedList<>();
    public static StringTokenizer st;
    public static int[] dx = new int[] {-1,1,0,0};
    public static int[] dy = new int[] {0,0,-1,1};

    public static int[] arr;
    public static int[] answer=new int[2];
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st= new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        board = new Character[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String input=br.readLine();
            for(int j=0;j<N;j++)
            board[i][j]=input.charAt(j);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j]){bfs(i,j);
                answer[0]+=1;}
            }
        }


        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(board[i][j].equals('R'))board[i][j]='G';
            }
        }
        
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!visited[i][j]){bfs(i,j);
                answer[1]+=1;}
            }
        }

        System.out.print(answer[0] + " "+answer[1]);


        

        
        
    }  
    
    public static void bfs(int x, int y){
        char target=board[x][y];
        q.add(new int[] {x,y});
        visited[x][y]=true;
        while(!q.isEmpty()){
            int[] now = q.poll();
            x=now[0];
            y=now[1];
            for (int i=0;i<4;i++){
                int nx=dx[i]+x;
                int ny=dy[i]+y;
                if(isin(nx,ny) && !visited[nx][ny] && board[nx][ny].equals(target)){
                    visited[nx][ny]=true;
                    q.add(new int[] {nx,ny});
                }
            }

        }
    }


        public static boolean isin(int x, int y){
        return 0<=x && x<N && 0<=y && y<N;
    }
}
