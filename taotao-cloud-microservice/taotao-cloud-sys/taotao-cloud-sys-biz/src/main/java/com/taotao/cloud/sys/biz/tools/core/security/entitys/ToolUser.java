package com.taotao.cloud.sys.biz.tools.core.security.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.validation.constraints.NotBlank;

public class ToolUser {
    @NotBlank
    private String username;
    @JsonIgnore
    private String password;

    public ToolUser() {
    }

    public ToolUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
