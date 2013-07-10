package yazamimDB;

public class Step {

	private int stepNum;
	private String stepName;

	public Step(int stepNum, String stepName) {
		this.stepNum = stepNum;
		this.stepName = stepName;
	}

	public int getStepNum() {
		return stepNum;
	}

	public void setStepNum(int stepNum) {
		this.stepNum = stepNum;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	@Override
	public String toString() {
		return "Step [stepNum=" + stepNum + ", stepName=" + stepName + "]";
	}
}
