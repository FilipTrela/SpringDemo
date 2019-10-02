package pl.sda.gda25.spring.students.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo/")
public class indexController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index() {
        return "strona-hello";
    }

    @GetMapping("/hello")
    public String showHello() {
        return "strona-hello";
    }

    @GetMapping("/helloTo")
    public String showHelloTo(Model model,
                              @RequestParam(name = "imie") String parametrImie) {
        model.addAttribute("atrybutImie",parametrImie);

        return "strona-hello-to";
    }
    @GetMapping("/helloMyBaby/{babyName}")
    public String showBabyName(Model model,
                               @PathVariable(name = "babyName") String varible){
        model.addAttribute("atrybutImie",varible);
        return "strona-hello-to";
    }
}
