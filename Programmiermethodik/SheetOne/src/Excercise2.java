// this class is used for calculating a possible discount for a shopping cart
public class Excercise2 {

	// main method
	public static void main(final String[] args) {

		// list of product prices in shopping cart as a Java array

		// shopping cart 1
		// double[] originalShoppingCart = new double[] { 40.95, 10.80, 2.35,
		// 22.50, 13.45, 5.95, 33, 41.10, 7.80, 5 };

		// shopping cart 2
		double[] originalShoppingCart = new double[] { 40.95, 10.80, 2.35, 22.50, 13.45, 5.95, 33, 41.10, 7.80, 5, 1.25,
				10.40, 6.10, 14.40, 55.55 };

		// shopping cart 3
		// double[] originalShoppingCart = new double[] { 40.95, 10.80, 2.35,
		// 22.50, 13.45, 5.95, 33, 41.10, 7.80, 5, 1.25,
		// 10.40, 6.10, 14.40, 55.55, 4.35, 2.02, 45.67, 35.34, 345.46, 34.30,
		// 66.30, 4.23, 1.23, 5.77, 85.89 };

		// // shopping cart 4
		// int[] originalShoppingCart = new int[] { 40, 10, 2, 22, 13, 5, 33,
		// 41, 7, 5 };

		int numberOfItems = originalShoppingCart.length;
		double originalPrice = 0;
		for (int i = 0; i < numberOfItems; i++) {
			originalPrice += originalShoppingCart[i];
		}
		// format to 2 decimal places
		originalPrice = Math.round(originalPrice * 100) / 100.0d;

		double discountedShoppingCart = calculateDiscount(originalPrice, numberOfItems);

		// ******OUTPUT on console:******
		// NOTE: NO output should happen before!!

		// TODO: output price of original cart and price of discounted cart with
		// only 2 decimal places after comma

		// FIX for rounding problem (EKW 2)
		double roundedDiscountedShoppingCart = Math.round(discountedShoppingCart * 100) / 100.0d;

		// Prints original cart and discounted cart
		System.out.println("Price of the original cart: " + originalPrice);
		System.out.println("Price of the discounted cart: " + roundedDiscountedShoppingCart);
		System.out.println("Number of items in cart: " + numberOfItems);

	} // end main

	// this method calculates the discount for a shopping cart based on the
	// number of items in the cart and the discount amount
	private static double calculateDiscount(final double originalPrice, final int numberOfItems) {
		double discountAmount = getDiscountAmount(originalPrice);
		double discountedPrice = 0;

		// TODO: use a switch statement to distinguish the price calculation
		// based on the given number of items and the calculated discountAmount
		// calculation is done like follows:
		// for exactly 5 items in the cart: discountedPrice = originalPrice -
		// ((originalPrice * discountAmount) / 100);
		// for exactly 10 items in the cart: discountedPrice = originalPrice -
		// ((originalPrice * discountAmount) / 100) - 10;
		// ... in steps of 5 up to 30 items with respectively a discount of the
		// accordingly amount of items (see example above)
		// the default case returns the originalPrice

		/*
		 * Just a very generic switch statement with 6 cases (+default). A
		 * switch statement makes little sense to me in this case, because every
		 * value but the ones defined in the cases will result in no discount
		 * whatsoever. We should definitely use if (numberOfItems >= 30) else
		 * if...
		 */
		switch (numberOfItems) {
		case 5:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100);
			break;
		case 10:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100) - 10;
			break;
		case 15:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100) - 15;
			break;
		case 20:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100) - 20;
			break;
		case 25:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100) - 25;
			break;
		case 30:
			discountedPrice = originalPrice - ((originalPrice * discountAmount) / 100) - 30;
			break;
		default:
			discountedPrice = originalPrice;
			break;
		}

		return discountedPrice;
	}

	// this method defines the discount amount depending on original price of
	// the cart
	private static double getDiscountAmount(final double originalPrice) {
		if (originalPrice < 50) {
			return 0;
		}
		if (originalPrice >= 50 && originalPrice < 60) {
			return 5;
		}

		if (originalPrice >= 60 && originalPrice < 70) {
			return 10;
		}

		if (originalPrice >= 70 && originalPrice < 100) {
			return 15;
		}

		if (originalPrice >= 100) {
			return 20;
		}
		return 0;
	}
} // end class