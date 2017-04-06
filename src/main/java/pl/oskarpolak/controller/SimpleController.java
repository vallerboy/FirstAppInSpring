package pl.oskarpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.UserRepository;
import pl.oskarpolak.model.Email;
import pl.oskarpolak.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by OskarPraca on 2017-02-18.
 */

@Controller
public class SimpleController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("name", "Oskar");
        model.addAttribute("isBanned", false);

        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(SimpleGrantedAuthority authority : simpleGrantedAuthorities){
            System.out.println("Masz autoryzacje: " + authority);
        }

        return "start";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String send(@RequestParam(value = "text", required = true) String text,
                       @RequestParam(value = "email", required = true) String email, Model model){
        model.addAttribute("email", email);
        return "result";
    }

    @RequestMapping(value = "/dodajuser/{nazwa}", method = RequestMethod.GET)
    @ResponseBody
    public String dodajUser(@PathVariable("nazwa") String name){
        return "Witaj, " + name + " !";
    }

    @RequestMapping(value = "/dodajuser/{nazwa}", method = RequestMethod.POST)
    @ResponseBody
    public String dodajUserPost(@PathVariable("nazwa") String name){
        return "Witaj, " + name + " !";
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


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping(value = "/testsql", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
       // User user = userRepository.findByUsername("siema");
        return " " ;
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        model.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping("/admin")
    public String admin(){
        return "start";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerPost(@ModelAttribute User user, Model model){
        //User userLocal = userRepository.findByUsername(user.getUsername());
//        if(userLocal == null){
//            user.setPassword(new ShaPasswordEncoder().encodePassword(user.getPassword(), null));
//            userRepository.save(user);
//            model.addAttribute("registred", true);
//        }
        model.addAttribute("user", new User());
        return "register";
    }





}
