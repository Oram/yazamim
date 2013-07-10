package yazamimTools;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class HebrewYearConverter {

	private static final String DIG = "אבגדהוזחט";
	private static final String TEN = "יכלמנסעפצ";
	private static final String HUN = "קרשת";
	private static final int HEBREW_YEAR_OFFSET = 3760;

	public static String getHebrewYear(int year) {
		year %= 1000; // ignore thousands
		String hebrewYear = "";
		int hun = 4, tens = 9;
		while (year > 0) {
			if (year >= hun * 100) {
				hebrewYear += HUN.charAt(hun - 1);
				year -= hun * 100;
				continue;
			}
			if (hun > 1) {
				--hun;
				continue;
			}
			if (year >= tens * 10) {
				hebrewYear += TEN.charAt(tens - 1);
				year -= tens * 10;
			} else if (tens > 1) {
				tens--;
				continue;
			}
			if (year > 0) {
				hebrewYear += DIG.charAt(year - 1);
				year = 0;
			}
		}
		return addGershayim(hebrewYear);
	}

	private static String addGershayim(String s) {
		return s.substring(0, s.length() - 1) + "\""
				+ s.substring(s.length() - 1, s.length());
	}

	public static int getCurrentHebrewYear() {
		Calendar now = new GregorianCalendar();
		int currentMonth = now.get(Calendar.MONTH);
		int currentHebrewYear = now.get(Calendar.YEAR) + HEBREW_YEAR_OFFSET;
		if (currentMonth < Calendar.AUGUST)
			return currentHebrewYear;
		return currentHebrewYear + 1;
	}
}
