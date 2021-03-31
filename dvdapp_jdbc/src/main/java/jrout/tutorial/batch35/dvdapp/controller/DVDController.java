package jrout.tutorial.batch35.dvdapp.controller;


import jrout.tutorial.batch35.dvdapp.dao.UserDAO;
import jrout.tutorial.batch35.dvdapp.domain.UserInformation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.sql.*;
import java.util.List;


@Controller
public class DVDController {



        //@RequestMapping("/showForm")
        @RequestMapping("/")
        public String showForm()
        {
            return "welcome";
        }

        @RequestMapping(value="/processForm",params="insert",method=RequestMethod.POST)
        public String insert(Model model)
        {
            System.out.println("Insert block called");
            UserInformation userInformation = new UserInformation();
            model.addAttribute("userInfo",userInformation);
            return "index_insert";
            //return "welcome2";

        }
        @RequestMapping(value="/processForm",params="update",method=RequestMethod.POST)
        public String update(Model model)
        {
            System.out.println("Update block called");
            UserInformation userInformation = new UserInformation();
            model.addAttribute("userInfo",userInformation);
            return "index_update";
        }

    @RequestMapping(value="/processForm",params="by_ID",method=RequestMethod.POST)
    public String selectID(Model model)
    {
        System.out.println("Update block called");
        UserInformation userInformation = new UserInformation();
        model.addAttribute("userInfo",userInformation);
        return "index_id";
    }


    @RequestMapping(value="/processForm",params="by_name",method=RequestMethod.POST)
    public String selectName(Model model)
    {
        System.out.println("Update block called");
        UserInformation userInformation = new UserInformation();
        model.addAttribute("userInfo",userInformation);
        return "index_fname";
    }



    @PostMapping("/formsubmit")
    public String handleForm(@ModelAttribute("userInfo") UserInformation userInformation, Model model) throws SQLException {
        System.out.println(userInformation);
        System.out.println(userInformation.getEmail());
        return "final";
    }


    @RequestMapping(value="/do-stuff")
    public String doStuffMethod() {
        System.out.println("Success");
        return "final";
    }

   /* @GetMapping("/")
    public String landingPage(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
       //return "selection";
        return "index"; // index.html
    }

    */

    @GetMapping("/selection")
    public String landingPage_select(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
        //return "selection";
        return "selection"; // index.html
    }


    @GetMapping("/fetchid")
    public String landingPage_id(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
        //return "selection";
        return "index_id"; // index.html
    }

    @GetMapping("/fetchname")
    public String landingPage_name(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
        //return "selection";
        return "index_fname"; // index.html
    }

    @GetMapping("/insert")
    public String landingPage_insert(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
        //return "selection";
        return "index_insert"; // index.html
    }

    @GetMapping("/update")
    public String landingPage_update(Model model){
        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("default@gmail.com");
        model.addAttribute("userInfo",userInformation);
        System.out.println("Landing Page...");
        return "index_update";
    }

    @PostMapping("/formfetch")
    public String userDAO(UserInformation userInformation,Model model) throws SQLException {
        UserDAO userdao = new UserDAO();
        userInformation = userdao.fetchCustomer(userInformation.getCustomer_id());
        model.addAttribute("userInfo",userInformation);
        return "final";
    }

    @PostMapping("/fetchCustomers")
    public String customerDAO(UserInformation userInformation,Model model) throws SQLException {
        UserDAO userdao = new UserDAO();
        List<UserInformation> userList =  userdao.fetchCustomersByFirstName(userInformation.getFirst_name());
        model.addAttribute("userInfo",userList);
        return "finalList";
    }

    @PostMapping("/insertCustomer")
    public String insertDAO(@ModelAttribute("userInfo") UserInformation userInformation, Model model) throws SQLException {
        UserDAO userdao = new UserDAO();
       // UserInformation userInformation1 = new UserInformation();
        model.addAttribute("userInfo",userInformation);
        userdao.insertCustomer(userInformation);
        System.out.println("inserted");

        return "final";
    }

    @PostMapping("/deleteCustomer")
    public String deleteDAO(@ModelAttribute("userInfo") UserInformation userInformation, Model model) throws SQLException {
        UserDAO userdao = new UserDAO();
        // UserInformation userInformation1 = new UserInformation();
        model.addAttribute("userInfo",userInformation);
        userdao.deleteCustomer(userInformation.getCustomer_id());
        System.out.println("deleted" + userInformation.getCustomer_id());

        return "final";
    }


    @PostMapping("/updateCustomer")
    public String updateDAO(@ModelAttribute("userInfo") UserInformation userInformation, Model model) throws SQLException {
        UserDAO userdao = new UserDAO();
        // UserInformation userInformation1 = new UserInformation();
        model.addAttribute("userInfo",userInformation);
        System.out.println("model" + model);
        userdao.updateCustomer(userInformation, userInformation.getCustomer_id());
        System.out.println("updated" + userInformation.getCustomer_id());

        return "final";
    }
}

