public class Checkstyle {
	private static final int TRUE_AGE = 8000000;

	public static void main(final String[] args) {
		int age = 17;
		int min_age = 18;
		if (age != min_age) {
			age++;
		}

		System.out.println(
				"Hello World! I'm crazy and I'm " + age + " years old. No, sorry, you are that old! Or even older?");
	}

}
