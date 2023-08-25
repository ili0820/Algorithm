
import java.io.*;
import java.util.*;
public class Main {
    static int V,E;
    static int K;
    static ArrayList<ArrayList<int[]>> graph;
    static int[] distance;

    static StringBuilder sb= new StringBuilder();
    static StringTokenizer st;
    public static void main (String[] args) throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        K=Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        
		graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
			graph.add(new ArrayList<>());
		}
        
        distance= new int [V];
        for(int i=0;i<V;i++)distance[i]=Integer.MAX_VALUE;
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())-1).add(new int[] {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())});
        }
        dijkstra(K-1);
        for(int i=0;i<V;i++){
            if(distance[i]==Integer.MAX_VALUE)sb.append("INF").append('\n');
            else sb.append(distance[i]).append('\n');
        }
        System.out.print(sb);

    }
    public static void dijkstra(int start){
        PriorityQueue<int[]> q= new PriorityQueue<>( (a,b) -> Integer.compare(a[0],b[0]));
        q.add(new int[] {0,start});
        distance[start]=0;
        while(!q.isEmpty()){
            int[] temp=q.poll();
            int dist=temp[0];
            int now=temp[1];
            if(distance[now]<dist)continue;
            for(int[] i:graph.get(now)){
                int cost=dist+i[1];
                if(cost<distance[i[0]]){
                    distance[i[0]]=cost;
                    q.add(new int[] {cost,i[0]});
                }
            }
        }


    }
}
