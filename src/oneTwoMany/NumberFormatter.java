package oneTwoMany;

public class NumberFormatter {
	
	public static String formatNumber(double number) {
		
		if(number >= 1_000_000_000) {
			return String.format("%.1fB", number / 1_000_000_000);
		}
		
		else if(number >= 1000000) {
			return String.format("%.1fM", number / 1000000.0);
		}
		
		else if(number >= 1000) {
			return String.format("%.1fK", number / 1000.0);
			
		}
		else {
			return String.valueOf(number);
		}
	}
}
