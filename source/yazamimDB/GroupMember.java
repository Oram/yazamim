package yazamimDB;

public class GroupMember {

	private String memberId;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private boolean _destroy; // using KO naming convention to track deletions 

	public GroupMember(String Id, String name, String phone, String email) {
		this.memberId = Id;
		this.memberName = name;
		this.memberPhone = phone;
		this.memberEmail = email;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public boolean is_destroy() {
		return _destroy;
	}

	public void set_destroy(boolean _destroy) {
		this._destroy = _destroy;
	}

	@Override
	public String toString() {
		return "GroupMember [memberId=" + memberId + ", name=" + memberName
				+ ", phone=" + memberPhone + ", email=" + memberEmail + "]";
	}
}
