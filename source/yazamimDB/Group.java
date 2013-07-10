package yazamimDB;

import java.sql.Date;
import java.sql.Time;

import yazamimDB.helpers.Weekdays;

public class Group {

	private int groupNum;
	private String groupName;
	private int activityYear;
	private BankDetails bankDetails;
	private Weekdays meetingDay;
	private Time meetingTime;
	private School school;
	private Step step;
	private GroupType type;
	private Program program;

	public Group(int groupNum, String groupName, int activityYear,
			Weekdays meetingDay, Time meetingTime, School school, Step step,
			GroupType type, Program program) {
		this.groupNum = groupNum;
		this.groupName = groupName;
		this.activityYear = activityYear;
		this.meetingDay = meetingDay;
		this.meetingTime = meetingTime;
		this.school = school;
		this.step = step;
		this.type = type;
		this.program = program;
	}

	public Group(int groupNum, String groupName, int activityYear,
			int meetingDay, Time meetingTime, School school, Step step,
			GroupType type, Program program) {
		this(groupNum, groupName, activityYear, Weekdays.fromInt(meetingDay),
				meetingTime, school, step, type, program);
	}

	public Group(int groupNum, String groupName, int activityYear,
			Date openAccountDate, String branchNum, String branchName,
			String accountNum, int meetingDay, Time meetingTime, School school,
			Step step, GroupType type, Program program) {
		this(groupNum, groupName, activityYear, meetingDay, meetingTime,
				school, step, type, program);
		this.bankDetails = new BankDetails(openAccountDate, branchNum,
				branchName, accountNum);
	}

	public Group(int groupNum, String groupName, int activityYear,
			BankDetails bankDetails, int meetingDay, Time meetingTime,
			School school, Step step, GroupType type, Program program) {
		this(groupNum, groupName, activityYear, meetingDay, meetingTime,
				school, step, type, program);
		this.bankDetails = bankDetails;
	}

	public Group(int groupNum, String groupName) {
		this.groupNum = groupNum;
		this.groupName = groupName;
	}
	
	public Group(School school, int groupNum, String groupName, int activityYear, Program program)
	{
		this.school = school;
		this.groupNum = groupNum;
		this.groupName = groupName;
		this.activityYear = activityYear;
		this.program = program;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getActivityYear() {
		return activityYear;
	}

	public void setActivityYear(int activityYear) {
		this.activityYear = activityYear;
	}

	public BankDetails getBankDetails() {
		return bankDetails;
	}

	public void setBankDetails(BankDetails bankDetails) {
		this.bankDetails = bankDetails;
	}

	public Weekdays getMeetingDay() {
		return meetingDay;
	}

	public void setMeetingDay(Weekdays meetingDay) {
		this.meetingDay = meetingDay;
	}

	public void setMeetingDay(int meetingDay) {
		this.setMeetingDay(Weekdays.fromInt(meetingDay));
	}

	public Time getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Time meetingTime) {
		this.meetingTime = meetingTime;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public GroupType getGroupType() {
		return type;
	}

	public void setGroupType(GroupType type) {
		this.type = type;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@Override
	public String toString() {
		return "Group [groupNum=" + groupNum + ", groupName=" + groupName
				+ ", activityYear=" + activityYear + ", bankDetails="
				+ bankDetails + ", meetingDay=" + meetingDay + ", meetingTime="
				+ meetingTime + ", school=" + school + ", step=" + step
				+ ", type=" + type + ", program=" + program + "]";
	}

}
