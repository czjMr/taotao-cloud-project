/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.common.model;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户手机号和账号密码 身份权限认证类 登陆身份认证
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 19:17:12
 */
public class SecurityUser implements UserDetails, CredentialsContainer, Serializable, Cloneable {

	@Serial
	private static final long serialVersionUID = -3685249101751401211L;

	/**
	 * ROLE_PREFIX
	 */
	private static final String ROLE_PREFIX = "ROLE_";

	/**
	 * userId
	 */
	private Long userId;
	/**
	 * 账号
	 */
	private String account;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 电话号码
	 */
	private String phone;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * email
	 */
	private String email;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * avatar
	 */
	private String avatar;
	/**
	 * 状态 1-启用，2-禁用
	 */
	private Integer status;
	/**
	 * lockFlag
	 */
	private String lockFlag;
	/**
	 * delFlag
	 */
	private String delFlag;
	/**
	 * type 1.平台用户 2.商户用户(个人用户/企业用户)
	 */
	private Integer type;
	/**
	 * 商店id
	 */
	private Long storeId;

	/**
	 * 公司id
	 */
	private List<String> companyIds;
	/**
	 * 部门id
	 */
	private List<String> deptIds;
	/**
	 * 岗位id
	 */
	private List<String> jobIds;
	/**
	 * 权限列表
	 */
	private Set<String> permissions;
	/**
	 * 角色id
	 */
	private Set<String> roleIds;
	/**
	 * 角色code
	 */
	private Set<String> roleCodes;
	/**
	 * 租户标识
	 */
	private String tenant;
	/**
	 * 租户的超级管理员
	 */
	private boolean superAdmin;
	/**
	 * 租户的每一个公司的管理员
	 */
	private boolean admin;


	public SecurityUser() {

	}

	/**
	 * SecurityUser
	 *
	 * @param userId      用户Id
	 * @param username    用户名称
	 * @param password    密码
	 * @param permissions 权限
	 * @param roleCodes   权限code
	 * @since 2021-09-02 19:18:58
	 */
	public SecurityUser(Long userId,
						String username,
						String password,
						Set<String> permissions,
						Set<String> roleCodes) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.permissions = permissions;
		this.roleCodes = roleCodes;
	}

	/***
	 * 权限重写
	 */
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		if (!CollUtil.isEmpty(roleCodes)) {
			roleCodes.parallelStream()
				.forEach(role -> authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role)));
		}

		if (!CollUtil.isEmpty(permissions)) {
			permissions.parallelStream()
				.forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission)));
		}
		return authorities;
	}

	/**
	 * 账户是否未过期,过期无法验证
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 指定用户是否解锁,锁定的用户无法进行身份验证
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 是否可用 ,禁用的用户不能身份验证
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(String lockFlag) {
		this.lockFlag = lockFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public static SecurityUserBuilder builder() {
		return new SecurityUserBuilder();
	}

	public List<String> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<String> companyIds) {
		this.companyIds = companyIds;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public List<String> getJobIds() {
		return jobIds;
	}

	public void setJobIds(List<String> jobIds) {
		this.jobIds = jobIds;
	}

	public Set<String> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<String> roleIds) {
		this.roleIds = roleIds;
	}

	public Set<String> getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(Set<String> roleCodes) {
		this.roleCodes = roleCodes;
	}

	public boolean isSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public boolean isAdmin() {
		return admin;
	}
	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	@Override
	public SecurityUser clone() {
		try {
			return (SecurityUser) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	//获取是否长期有效的token 此函数可删
	public boolean getLongTerm() {
		return false;
	}

	public static final class SecurityUserBuilder {
		private String ROLE_PREFIX;
		private Long userId;
		private String account;
		private String username;
		private String nickname;
		private String password;
		private String phone;
		private String mobile;
		private String email;
		private Integer sex;
		private String birthday;
		private String avatar;
		private Integer status;
		private String lockFlag;
		private String delFlag;
		private Integer type;
		private Long storeId;
		private List<String> companyIds;
		private List<String> deptIds;
		private List<String> jobIds;
		private Set<String> permissions;
		private Set<String> roleIds;
		private Set<String> roleCodes;

		private SecurityUserBuilder() {
		}

		public SecurityUserBuilder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public SecurityUserBuilder withAccount(String account) {
			this.account = account;
			return this;
		}

		public SecurityUserBuilder withUsername(String username) {
			this.username = username;
			return this;
		}

		public SecurityUserBuilder withNickname(String nickname) {
			this.nickname = nickname;
			return this;
		}

		public SecurityUserBuilder withPassword(String password) {
			this.password = password;
			return this;
		}

		public SecurityUserBuilder withPhone(String phone) {
			this.phone = phone;
			return this;
		}

		public SecurityUserBuilder withMobile(String mobile) {
			this.mobile = mobile;
			return this;
		}

		public SecurityUserBuilder withEmail(String email) {
			this.email = email;
			return this;
		}

		public SecurityUserBuilder withSex(Integer sex) {
			this.sex = sex;
			return this;
		}

		public SecurityUserBuilder withBirthday(String birthday) {
			this.birthday = birthday;
			return this;
		}

		public SecurityUserBuilder withAvatar(String avatar) {
			this.avatar = avatar;
			return this;
		}

		public SecurityUserBuilder withStatus(Integer status) {
			this.status = status;
			return this;
		}

		public SecurityUserBuilder withLockFlag(String lockFlag) {
			this.lockFlag = lockFlag;
			return this;
		}

		public SecurityUserBuilder withDelFlag(String delFlag) {
			this.delFlag = delFlag;
			return this;
		}

		public SecurityUserBuilder withType(Integer type) {
			this.type = type;
			return this;
		}

		public SecurityUserBuilder withStoreId(Long storeId) {
			this.storeId = storeId;
			return this;
		}

		public SecurityUserBuilder withCompanyIds(List<String> companyIds) {
			this.companyIds = companyIds;
			return this;
		}

		public SecurityUserBuilder withDeptIds(List<String> deptIds) {
			this.deptIds = deptIds;
			return this;
		}

		public SecurityUserBuilder withJobIds(List<String> jobIds) {
			this.jobIds = jobIds;
			return this;
		}

		public SecurityUserBuilder withPermissions(Set<String> permissions) {
			this.permissions = permissions;
			return this;
		}

		public SecurityUserBuilder withRoleIds(Set<String> roleIds) {
			this.roleIds = roleIds;
			return this;
		}

		public SecurityUserBuilder withRoleCodes(Set<String> roleCodes) {
			this.roleCodes = roleCodes;
			return this;
		}

		public SecurityUser build() {
			SecurityUser securityUser = new SecurityUser();
			securityUser.setUserId(userId);
			securityUser.setAccount(account);
			securityUser.setUsername(username);
			securityUser.setNickname(nickname);
			securityUser.setPassword(password);
			securityUser.setPhone(phone);
			securityUser.setMobile(mobile);
			securityUser.setEmail(email);
			securityUser.setSex(sex);
			securityUser.setBirthday(birthday);
			securityUser.setAvatar(avatar);
			securityUser.setStatus(status);
			securityUser.setLockFlag(lockFlag);
			securityUser.setDelFlag(delFlag);
			securityUser.setType(type);
			securityUser.setStoreId(storeId);
			securityUser.setCompanyIds(companyIds);
			securityUser.setDeptIds(deptIds);
			securityUser.setJobIds(jobIds);
			securityUser.setPermissions(permissions);
			securityUser.setRoleIds(roleIds);
			securityUser.setRoleCodes(roleCodes);
			return securityUser;
		}
	}
}
