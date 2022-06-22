package com.spring.aws.web;

import com.spring.aws.config.auth.LoginUser;
import com.spring.aws.config.auth.dto.SessionUser;
import com.spring.aws.domain.user.User;
import com.spring.aws.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("user", user);
        }
        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "postsSave";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("post", postsService.findById(id));
        return "postsUpdate";
    }
}
