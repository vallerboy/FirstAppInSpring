package pl.oskarpolak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.oskarpolak.model.Email;

import javax.validation.Valid;

/**
 * Created by OskarPraca on 2017-02-18.
 */

@Controller
public class SimpleController {

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Oskar");
        model.addAttribute("isBanned", false);
        return "start";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestParam(value = "text", required = true) String text,
                       @RequestParam(value = "email", required = true) String email, Model model){
        model.addAttribute("email", email);
        return "result";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendGet(Model model){
        model.addAttribute("email", "TO NIE TA METODA, NIE MOŻESZ TAK TU WCHODZIC!");
        return "result";
    }

    @RequestMapping(value = "/newform", method = RequestMethod.GET)
    public String newForm(Model model){
        model.addAttribute("emailClass", new Email());
        return "newform";
    }

    @RequestMapping(value = "/newform", method = RequestMethod.POST)
    public String newFormGet (@ModelAttribute("emailClass") @Valid Email emailClass, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "newform";
        }
        return "resultEmail";
    }

}
