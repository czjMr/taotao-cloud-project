package com.taotao.cloud.sensitive.sensitive.sensitive.model.sensitive;


import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyCardId;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyChineseName;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyEmail;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyPassword;
import com.taotao.cloud.sensitive.sensitive.sensitive.core.api.strategory.StrategyPhone;

public class User {

	@Sensitive(strategy = StrategyChineseName.class)
	private String username;

	@Sensitive(strategy = StrategyCardId.class)
	private String idCard;

	@Sensitive(strategy = StrategyPassword.class)
	private String password;

	@Sensitive(strategy = StrategyEmail.class)
	private String email;

	@Sensitive(strategy = StrategyPhone.class)
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User{" +
			"username='" + username + '\'' +
			", idCard='" + idCard + '\'' +
			", password='" + password + '\'' +
			", email='" + email + '\'' +
			", phone='" + phone + '\'' +
			'}';
	}
}
