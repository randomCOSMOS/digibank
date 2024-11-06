package com.appproject.digibank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.appproject.digibank.model.User;
import com.appproject.digibank.model.LoginInfo;
import com.appproject.digibank.model.Transaction;
import com.appproject.digibank.service.BlockchainService;
import com.appproject.digibank.service.UserService;

@Controller
public class bankController {

    Boolean status = false;
    String currentUser = null;

    @Autowired
    UserService userService;

    @Autowired
    BlockchainService blockchainService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("status", status);
        model.addAttribute("currentUser", currentUser);
        return("index");
    }   

    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("status", status);
        return("about");
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginInfo", new LoginInfo());
        return("login");
    }  
    
    @PostMapping("/login")
    public ModelAndView checkCredentials(@ModelAttribute LoginInfo loginInfo, Model model){
        List<User> user = userService.getUserByEmail(loginInfo.getEmail());
        model.addAttribute("loginInfo", new LoginInfo());

        if(user.size() > 0 && user.get(0).getPassword().equals(loginInfo.getPassword())){
            System.out.println("Pass!");
            status = true;
            currentUser = loginInfo.getEmail();
            return new ModelAndView("redirect:/");
        } else {
            System.out.println("Reject!");
            return new ModelAndView("redirect:/login");
        }
    }
    
    @GetMapping("/signin")
    public String signin(Model  model){
        model.addAttribute("userForm", new User());
        return("signin");
    }
    
    @PostMapping("/signin")
    public ModelAndView registerUser(@ModelAttribute User user, Model model){
        userService.saveUser(user);
        currentUser = user.getEmail();
        model.addAttribute("userForm", new User());
        status = true;
        return new ModelAndView("redirect:/");
    }
    
    @GetMapping("/signout")
    public ModelAndView signOut(){
        status = false;
        currentUser = null;
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/transaction")
    public String transaction(Model model){
        List<Transaction> transactions = blockchainService.getTransactionByEmail(currentUser); 
        model.addAttribute("transactionForm", new Transaction());
        model.addAttribute("transactions", transactions);
        model.addAttribute("status", status);
        return("transaction");
    }

    @PostMapping("/transaction")
    public ModelAndView mine(@ModelAttribute Transaction transaction , Model model){
        transaction.setEmail(currentUser);
        blockchainService.mineBlocks(transaction);
        model.addAttribute("message", "Well done bruv");
        return new ModelAndView("redirect:/transaction");
    }
    
}


