package ra.academy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.model.Student;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @RequestMapping({"/",""})
    public String dashboard(){
        return "admin/index";
    }
    @RequestMapping("/catalog")
    public String catalog(){
        return "admin/category";
    }
    @RequestMapping("/product")
    public String product(){
        return "admin/product";
    }
    @RequestMapping("/user")
    public String user(){
        return "admin/user";
    }
    @RequestMapping("/demo")
    public String demo(Model model){
        model.addAttribute("name","Nguyễn Văn A");
        model.addAttribute("age",19);
        model.addAttribute("sex",true);
        model.addAttribute("html","<div><ul><li><a href=\"/\">Hello</a></li></ul></div>");
        model.addAttribute("today", new Date());
        model.addAttribute("student",new Student(1L,"Nguyễn Văn A",24,true));
        return "demo";
    }

    @RequestMapping("/find/{id}")
    public String target(Model model, @PathVariable long id){
        model.addAttribute("id",id);
        List<String> list = Arrays.asList("Hùng","Nam","Khánh");
        model.addAttribute("list",list);
        return "target";
    }

}
