package yazamimDB.helpers;

public enum InstructorTypes {
	BUSINESS('b', "עסקי"), STUDNET('s', "סטודנט"), TEACHER('t', "מורה");

	private final char value;
	private final String name;

	private InstructorTypes(char value, String name) {
		this.value = value;
		this.name = name;
	}

	public char getValue() {
		return value;
	}

	public static InstructorTypes fromChar(char value) {
		switch (value) {
		case 'b':
			return InstructorTypes.BUSINESS;
		case 's':
			return InstructorTypes.STUDNET;
		case 't':
			return InstructorTypes.TEACHER;
		default:
			throw new IllegalArgumentException(
					"Possible values are 'b', 's' and 't'");
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}
