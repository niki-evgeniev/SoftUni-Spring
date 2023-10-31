package com.example.coffeeshop.web;

import com.example.coffeeshop.service.LoggedUser;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import com.example.coffeeshop.view.OrderViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(LoggedUser loggedUser, OrderService orderService, UserService userService) {
        this.loggedUser = loggedUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {

        if (!loggedUser.isLogged()) {
            return "index";
        }
        List<OrderViewModel> orders = orderService.findAllOrderByPriceDesc();
        model.addAttribute("orders", orders);
        model.addAttribute("totalTime",orders.stream()
                .map(orderViewModel -> orderViewModel.getCategory().getNeededTime())
                .reduce(Integer::sum)
                .orElse(0));

        model.addAttribute("users", userService.findAllUserAndCountOrdersDesc());

        return "home";
    }


}
