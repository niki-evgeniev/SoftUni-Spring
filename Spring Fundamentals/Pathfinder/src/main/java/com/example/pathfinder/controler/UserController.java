package com.example.pathfinder.controler;


import com.example.pathfinder.models.DTO.UserLoginDTO;
import com.example.pathfinder.models.DTO.UserRegisterDTO;
import com.example.pathfinder.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView login(UserLoginDTO userLoginDTO)  {
        boolean isLogged = userService.login(userLoginDTO);

        if (isLogged){
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("login");
    }


    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterDTO userRegisterDTO) {


        userService.register(userRegisterDTO);

        return new ModelAndView("redirect:login");
    }


    @GetMapping("/logout")
    public ModelAndView logout(){
        userService.logout();
        return new ModelAndView("redirect:/");

    }
}
