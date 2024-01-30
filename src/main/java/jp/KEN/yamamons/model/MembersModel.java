package jp.KEN.yamamons.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import jp.KEN.yamamons.Group.Group1;

public class MembersModel implements Serializable {

	@NotEmpty(message="名前入力は必須です")
	private String name;

	@NotEmpty(message="emailは必須です")
	@Email(groups=Group1.class, message="メールアドレスではありません")
	private String mail;

	@NotEmpty(message="電話番号は必須です")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message="携帯番号ではありません",groups=Group1.class)
	private String phoneNumber;

	private String credit;

	private String planNo;

	@NotEmpty(message="パスワードは必須です")
	@Length(min=4, max=10,message="4文字以上10文字以下で入力してください",groups=Group1.class)

	private String password;

	@NotEmpty(message="住所は必須です")
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
