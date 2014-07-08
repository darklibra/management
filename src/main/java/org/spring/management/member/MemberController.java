package org.spring.management.member;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.spring.management.member.model.User;
import org.spring.management.member.model.UserPaging;
import org.spring.management.member.model.UserSearch;
import org.spring.management.member.service.UserService;
import org.spring.management.member.validator.Validator;
import org.spring.management.member.validator.impl.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class MemberController {
    @Resource(name="userService")
    UserService userService;
    @Resource(name="userValidator")
    Validator validator;
    
    @RequestMapping(value={"/", "/list"})
    public String list(UserSearch search, UserPaging pagingInfo, Model model) {
        List<User> users = userService.listAndGetPaging(search, pagingInfo);
        model.addAttribute("memberTypes", User.MEMBER_TYPE.values());
        model.addAttribute("users", users);
        model.addAttribute("search", search);
        model.addAttribute("pagingInfo", pagingInfo);
        
        return "member/list";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.GET)
    public String add_form(Model model, UserSearch search) {
        model.addAttribute("user", new User());
        model.addAttribute("memberTypes", User.MEMBER_TYPE.values());
        model.addAttribute("search", search);
        return "member/add_form";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String add(@ModelAttribute User user, UserSearch search, Model model) {
        this.validator.setTarget(user);
        model.addAttribute("search", search);
        if (this.validator.isInsert()) {
            // TODO : 현재(2014.07.06) birthDate가 정상적을 연결되지 않고 있음.
            if (user.getBirthDate() == null) {
                user.setBirthDay(new Date(2014,7,6));
            }
            
            userService.insert(user);
            model.addAttribute("url", "/list");
            model.addAttribute("message", "입력에 성공하였습니다.");
            
            return "member/alter_message_and_redirect_url";
        } else {
            model.addAttribute(this.validator.getErrorField(), this.validator.getErrorMessage());
            model.addAttribute("memberTypes", User.MEMBER_TYPE.values());
            model.addAttribute("search", search);
            return "member/add_form";
        }
    }
    
    @RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
    public String modify_form(@PathVariable int id, Model model, @RequestParam(value="searchFunc", required=false) String searchFunc) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("memberTypes", User.MEMBER_TYPE.values());
        model.addAttribute("searchFunc", searchFunc);
        
        return "member/modify_form";
    }

    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public String modify(@ModelAttribute User user, Model model, String searchFunc) {
        if (user.getBirthDate() == null) {
            user.setBirthDay(new Date());
        }

        this.validator.setTarget(user);
        
        if (this.validator.isUpdate()) {
            userService.update(user);
            model.addAttribute("message", "수정에 성공하였습니다.");
            model.addAttribute("searchFunc", searchFunc);
            
            return "member/alter_message_and_close_window";
        } else {
            model.addAttribute(this.validator.getErrorField(), this.validator.getErrorMessage());
            model.addAttribute("memberTypes", User.MEMBER_TYPE.values());
            return "/modify/"+user.getId();
        }
    }
    
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String delete(@ModelAttribute User user, Model model, String searchFunc) {
        userService.delete(user);
        model.addAttribute("message", "삭제에 성공하였습니다.");
        model.addAttribute("searchFunc", searchFunc);
        
        return "member/alter_message_and_close_window";
    } 
}
