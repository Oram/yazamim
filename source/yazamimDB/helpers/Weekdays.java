package yazamimDB.helpers;

public enum Weekdays {
	SUNDAY(1, "ראשון"),
	MONDAY(2, "שני"),
	TUESDAY(3,"שלישי"),
	WEDNESDAY(4 ,"רביעי"),
	THURSDAY(5 ,"חמישי"),
	FRIDAY(6 ,"שישי"),
	SATURDAY(7 ,"שבת");
			
	private final int value;
	private final String name;

	private Weekdays(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public static Weekdays fromInt(int value) {
		switch (value) {
		case 0:
			return null;
		case 1:
			return Weekdays.SUNDAY;
		case 2:
			return Weekdays.MONDAY;
		case 3:
			return Weekdays.TUESDAY;
		case 4:
			return Weekdays.WEDNESDAY;
		case 5:
			return Weekdays.THURSDAY;
		case 6:
			return Weekdays.FRIDAY;
		case 7:
			return Weekdays.SATURDAY;
		default:
			throw new IllegalArgumentException("Possible values are "
					+ "between 1 for Sunday and 7 for Saturday or 0 for null");
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
