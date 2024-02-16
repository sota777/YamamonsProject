package jp.KEN.yamamons.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import jp.KEN.yamamons.Group.Group1;

public class MembersModel implements Serializable {

	@NotEmpty(message="※Not Empty Name")
	private String name;

	@NotEmpty(message="※Not Empty Email")
	@Email(groups=Group1.class, message="Not Email")
	private String mail;

	@NotEmpty(message="※Not Empty Phonenumber")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message="※Not Phonenumber",groups=Group1.class)
	private String phoneNumber;

	@NotNull(message="※Not Empty Credit")
	private String credit;

	private String planNo;

	@NotEmpty(message="※Not Empty Password")
	@Length(min=4, max=10,message="※Between 4 And 10 Alphanumeric Characters",groups=Group1.class)

	private String password;

	@NotEmpty(message="※Not Empty Address")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}





}
