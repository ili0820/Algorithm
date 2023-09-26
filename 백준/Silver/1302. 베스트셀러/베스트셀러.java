import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			
			if (map.containsKey(str)) {
				map.put(str, map.get(str)+1);
			} else {
				map.put(str,  1);
			}
			
		}
		
		List<String> keys = new ArrayList<>(map.keySet());
		Collections.sort(keys, (o1, o2) -> {
			if (map.get(o2)==map.get(o1)) {
				return o1.compareTo(o2);
			}
			return map.get(o2)-map.get(o1);
		}
		);
		
		System.out.println(keys.get(0));
	}
}