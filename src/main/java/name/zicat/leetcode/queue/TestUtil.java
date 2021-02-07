package name.zicat.leetcode.queue;

public class TestUtil {

	public static int numWays(int n, int k) {
		if (n <= 2) {
			return k*k;
		} else {
			int[] m = new int[n];
			m[0] = k;
			m[1] = k*k;
			for (int i = 2; i < n; i++) {
				m[i] = m[i-1] * (k-1) + m[i-2] * (k-1);
			}
			return m[n-1];
		}
	}


	public static void main(String[] args) {
		System.out.println("args = " + numWays(6,2));
	}
}
