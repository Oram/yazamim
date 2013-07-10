package yazamimDB;

public class Program {

	private int programNum;
	private String programName;

	public Program(int programNum, String programName) {
		this.programNum = programNum;
		this.programName = programName;
	}

	public int getProgramNum() {
		return programNum;
	}

	public void setProgramNum(int programNum) {
		this.programNum = programNum;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	@Override
	public String toString() {
		return "Program [programNum=" + programNum + ", programName="
				+ programName + "]";
	}

}
