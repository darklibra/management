package org.spring.management.member.validator;

public interface Validator {
    public void setTarget(Object o);
    public Boolean isInsert();
    public Boolean isUpdate();
    public Boolean isDelete();
    
    public String getErrorField();
    public String getErrorMessage();
}
