package jp.KEN.yamamons.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginModel implements Serializable {
	@NotEmpty(message="ログイン用メールアドレスは必須入力です")
	private String loginMail;
	@NotEmpty(message="パスワードは必須入力です")
	private String password;

	private String customerName;


	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLoginMail() {
		return loginMail;
	}
	public void setLoginMail(String loginMail) {
		this.loginMail = loginMail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
