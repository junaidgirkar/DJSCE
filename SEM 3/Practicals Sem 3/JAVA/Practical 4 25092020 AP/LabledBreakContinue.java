public class LabledBreakContinue {
	public static void main(String[] args) {
		int breaklimit = 9;
		outer: for (int i = 0; ; i++) {
			for (int j = 0; j < 10; j++) {
				if (j > i) {
					System.out.println();
					continue outer;
				}
				System.out.print(" " + (i * j));
			}
			if(i==breaklimit){
				break outer;
			}
		}
		System.out.println();
	}
}
