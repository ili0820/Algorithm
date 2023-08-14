import java.io.*;//BufferedReader를 쓰기 위한 라이브러리 import
import java.util.*;//ArrayList,StringTokenizer를 위한 라이브러리 import 
public class Main {
	static int k,N;
	static String h;
	static int[] fs;
	static String [] folds;
	static String [][] paper;
	static 	String [] arr1={"2","3","0","1"};
	static	String [] arr2={"1","0","3","2"};
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		//k입력

		st=new StringTokenizer(br.readLine());
		k=Integer.parseInt(st.nextToken());

		//접는법 초기화& 입력
		N=(int)Math.pow(2,k);
		fs=new int[k];
		for(int i=0;i<fs.length;i++)
			fs[i]=(int)Math.pow(2,k-i);
		
		paper=new String[N][N];
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
			paper[i][j]="0";
		
		folds=new String[2*k];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<folds.length;i++)
			folds[i]=st.nextToken();
		//h 입력
		st=new StringTokenizer(br.readLine());
		h=st.nextToken();

		solve(0,0,0,N-1,0,N-1);
		for (int i=0;i<N;i++){
			for(int j=0;j<N;j++)
				sb.append(paper[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.print(sb);
	}
	private static String transform(String dir,String numb){

		if (dir.equals("D") | dir.equals("U")){
			return arr1[Integer.parseInt(numb)];
		}
		if (dir.equals("R") | dir.equals("L")){
			return arr2[Integer.parseInt(numb)];
		}
		return "";

	}

	private static void solve(int fi,int fj,int si, int ei, int sj,int ej){

		if (fi==k && fj==k){
			paper[si][sj]=h;
			return;
		}

		String f=folds[fi+fj];
		switch(f){
			case "D":
				solve(fi+1,fj,(si+ei)/2+1,ei,sj,ej);
				
				for(int i=0;i<fs[fi];i++){
					for(int j=sj;j<ej+1;j++)
					paper[si+i][j]=transform(f,paper[ei-i][j]);
				}
				break;
		}
		switch(f){
			case "U":
				solve(fi+1,fj,si,(si+ei)/2,sj,ej);
				for(int i=0;i<fs[fi];i++){
					for(int j=sj;j<ej+1;j++)
					paper[ei-i][j]=transform(f,paper[si+i][j]);
				}
				break;
		}
		switch(f){
			case "R":
				solve(fi,fj+1,si,ei,(sj+ej)/2+1,ej);
				for(int i=si;i<ei+1;i++){
					for(int j=0;j<fs[fj];j++)
					paper[i][sj+j]=transform(f,paper[i][ej-j]);
				}
				break;
		}
		switch(f){
			case "L":
				solve(fi,fj+1,si,ei,sj,(sj+ej)/2);
				for(int i=si;i<ei+1;i++){
					for(int j=0;j<fs[fj];j++)
					paper[i][ej-j]=transform(f,paper[i][sj+j]);
				}
				break;
		}

	}


}


