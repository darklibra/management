package org.spring.management.member.model;

import static org.spring.management.member.model.User.MEMBER_TYPE;

public class UserSearch {
    private String searchName = "";
    private MEMBER_TYPE searchMemberType = User.MEMBER_TYPE.ALL;
    
    public String getSearchName() {
        return searchName;
    }
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }
    public MEMBER_TYPE getSearchMemberType() {
        return searchMemberType;
    }
    public void setSearchMemberType(MEMBER_TYPE searchMemberType) {
        this.searchMemberType = searchMemberType;
    }
    
}
