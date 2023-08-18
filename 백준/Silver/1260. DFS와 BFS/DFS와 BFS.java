
import java.io.*;
import java.util.*;

public class Main {
    static int N,M,V;
    static List<List<Integer>> graph;
    // static int[] graph;
    static StringBuilder sb=new StringBuilder();
    static StringTokenizer st;
    
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        V=Integer.parseInt(st.nextToken());
        //그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }
        //그래프 입력
        for(int i=0;i<M;i++){
            st=new StringTokenizer(br.readLine());
            int node1=Integer.parseInt(st.nextToken());
            int node2=Integer.parseInt(st.nextToken());
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
            // graph[i]=Integer.parseInt(st.nextToken());
        }
        
        for (int i = 1; i <N+1; i++) { 
	    Collections.sort(graph.get(i)); // 방문 순서를 위해 오름차순 정렬 
        }

        dfs(V,new boolean[N+1]);
        sb.append("\n");
        bfs(V);
        System.out.print(sb);

    }
    public static void dfs(int now,boolean[] visited){
        visited[now]=true;
        List<Integer> arr=graph.get(now);
        sb.append(now).append(" ");

        for(int i=0;i<arr.size();++i){
                if(!visited[arr.get(i)]){
                    dfs(arr.get(i),visited);
                }
            }
    }
    
    public static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();
        boolean visited[] = new boolean[N+1];

        q.offer(start);
        visited[start]=true;

        while(!q.isEmpty()){
            int current = q.poll();
            sb.append(current).append(" ");
            List<Integer> arr=graph.get(current);
            for(int i=0;i<arr.size();++i){
                if(!visited[arr.get(i)]){
                    q.offer(arr.get(i));
                    visited[arr.get(i)]=true;
                }
            }
        }
    }
}


