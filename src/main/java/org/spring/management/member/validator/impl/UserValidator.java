package org.spring.management.member.validator.impl;

import org.spring.management.member.exception.UserValidException;
import org.spring.management.member.model.User;
import org.spring.management.member.validator.Validator;
import org.springframework.stereotype.Service;

@Service("userValidator")
public class UserValidator implements Validator {
    public final String ERR_FIELD_ID = "errId";
    public final String ERR_FIELD_NAME = "errName";
    public final String ERR_FIELD_BIRTH_DATE = "errBirthDate";
    public final String ERR_FIELD_MEMBER_TYPE = "errMemberType";
    
    private final int MAX_SIZE_NAME = 20;
    
    User user;
    String errField;
    String errMessage;
    
    @Override
    public void setTarget(Object obj) {
        if (!(obj instanceof User)) return;
        this.user = (User)obj;
    }

    @Override
    public Boolean isInsert() {
        try {
            validateName();
            validateMemberType();
        } catch (UserValidException e) {
            this.errField = e.getField();
            this.errMessage = e.getMessage();
            return false;
        }
        
        return true;
    }

    @Override
    public Boolean isUpdate() {
        try {
            validateName();
            validateMemberType();
        } catch (UserValidException e) {
            this.errField = e.getField();
            this.errMessage = e.getMessage();
            return false;
        }
        
        return true;
    }

    @Override
    public Boolean isDelete() {
        return true;
    }
    
    private void validateName() {
        isNull(ERR_FIELD_NAME, this.user.getName());
        isEmpty(ERR_FIELD_NAME, this.user.getName());
        checkStringLength(ERR_FIELD_NAME, MAX_SIZE_NAME, this.user.getName());
    }
    
    private void validateMemberType() {
        isNull(ERR_FIELD_MEMBER_TYPE, this.user.getMemberType());
        isEmpty(ERR_FIELD_MEMBER_TYPE, this.user.getMemberType().getValue());
    }

    @Override
    public String getErrorField() {
        return this.errField;
    }

    @Override
    public String getErrorMessage() {
        return this.errMessage;
    }
    
    private void isNull(String field, Object data) {
        if (data==null)
            throw new UserValidException(field, "잘못된 입력입니다.");
    }
    
    private void isEmpty(String field, String data) {
        if (data.isEmpty())
            throw new UserValidException(field, "잘못된 입력입니다.");
    }
    
    private void checkStringLength(String field, int limitLength, String data) {
        int dataLength = data.length();
        if (dataLength < 1 || dataLength > limitLength)
            throw new UserValidException(field, "입력한 글자 수가 잘못되었습니다.(MIN:1, MAX:"+limitLength+")");
    }
}
