package pl.coderslab.charity.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "admin/login";
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register")
    public String registerPost(User user){
        userService.save(user);
        return "redirect:/login";
    }

//PROFILE EDIT
    @GetMapping("/profile/{userId}/edit")
    public String userEditGet(@PathVariable Long userId, Model model){
        model.addAttribute("user", userService.findById(userId));
        return "profile/edit";
    }

    @PostMapping("/profile/{userId}/edit")
    public String userEditPost(@PathVariable Long userId, User user){
        User userFromDB = userService.findById(userId);
        userFromDB.setFirstName(user.getFirstName());
        userFromDB.setLastName(user.getLastName());
        userFromDB.setEmail(user.getEmail());
        userService.edit(userFromDB);
    
        Set<GrantedAuthority> authorities = userFromDB.getRoles().stream().map(r-> new SimpleGrantedAuthority(r.getRoleType().toString())).collect(Collectors.toSet());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userFromDB.getEmail(), userFromDB.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/profile/"+userId+"/edit";
    }
}
