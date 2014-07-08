package org.spring.management.member.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.spring.management.member.model.User.MEMBER_TYPE; 

public class UserTest {
    private User user;
    
    @Before
    public void setting() {
        user = new User();    
    }
    
    @Test
    public void member_type_create_test() {
        user.setMemberType(MEMBER_TYPE.ADMIN);
        test_member_type_admin();
    }
    
    @Test
    public void member_type_setter_test() {
        user.setMemberType(MEMBER_TYPE.valueOf(MEMBER_TYPE.ADMIN.getValue()));
        test_member_type_admin(); 
    }
    
    private void test_member_type_admin() {
        assertThat(user.getMemberType(), is(MEMBER_TYPE.ADMIN));
        assertThat(user.getMemberType().getValue(), is(MEMBER_TYPE.ADMIN.getValue()));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void expect_IllegalArgumentException_when_wrong_member_type_from_string() {
        user.setMemberType(MEMBER_TYPE.valueOf("WRONG_MEMBER_TYPE"));
    }
    
    @Test
    public void order_test_member_type_of_user() {
        MEMBER_TYPE[] typesOfUserMember = MEMBER_TYPE.values();
        MEMBER_TYPE[] expectMemberTypeOrder = {MEMBER_TYPE.ADMIN, MEMBER_TYPE.GENERAL_MEMBER, MEMBER_TYPE.SPECIAL_MEMBER};
        
        assertThat(typesOfUserMember.length, is(expectMemberTypeOrder.length));    
        
        for (int position = 0;position < typesOfUserMember.length; position++) {
            assertThat(typesOfUserMember[position], is(expectMemberTypeOrder[position]));    
        }      
    }
}
