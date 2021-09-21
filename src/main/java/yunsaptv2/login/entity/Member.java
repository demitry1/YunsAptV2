package yunsaptv2.login.entity;

import java.util.Date;

public class Member {
	private String id;
	private String password;
	private String name;
	private Date regdate;
	private String email;

	public Member() {

	}

	public Member(String id, String password, String name, Date regdate, String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.regdate = regdate;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", regdate=" + regdate + ", email="
				+ email + "]";
	}

}
