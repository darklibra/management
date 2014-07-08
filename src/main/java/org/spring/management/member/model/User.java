package org.spring.management.member.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class User {
    public enum MEMBER_TYPE{
        ALL(""), ADMIN("ADMIN"), GENERAL_MEMBER("GENERAL_MEMBER"), SPECIAL_MEMBER("SPECIAL_MEMBER");
        private String value = "";
        private MEMBER_TYPE(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    private int id;
    private String name;
    private Date birthDate;
    private MEMBER_TYPE memberType;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDay(Date birthDate) {
        this.birthDate = birthDate;
    }
    public MEMBER_TYPE getMemberType() {
        return memberType;
    }
    public void setMemberType(MEMBER_TYPE memberType) {
        this.memberType = memberType;
    }
}
