package ra.academy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ra.academy.dao.StudentDao;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentDao studentDao;

    @RequestMapping
    public String list(Model model){
        model.addAttribute("list",studentDao.findAll());
        return "admin/student/student";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        studentDao.delete(id);
        return "redirect:/student";
    }

}
