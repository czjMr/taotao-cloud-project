package com.taotao.cloud.sensitive.sensitive.sensitive.model.sensitive.system;


import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.strategy.SensitiveStrategyCardId;
import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.strategy.SensitiveStrategyChineseName;
import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.strategy.SensitiveStrategyEmail;
import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.strategy.SensitiveStrategyPassword;
import com.taotao.cloud.sensitive.sensitive.sensitive.annotation.strategy.SensitiveStrategyPhone;

/**
 * 系统内置注解
 */
public class SystemBuiltInAt {

    @SensitiveStrategyPhone
    private String phone;

    @SensitiveStrategyPassword
    private String password;

    @SensitiveStrategyChineseName
    private String name;

    @SensitiveStrategyEmail
    private String email;

    @SensitiveStrategyCardId
    private String cardId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    @Override
    public String toString() {
        return "SystemBuiltInAt{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
