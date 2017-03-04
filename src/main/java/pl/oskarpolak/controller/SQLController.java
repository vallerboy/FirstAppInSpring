package pl.oskarpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.oskarpolak.UserRepository;
import pl.oskarpolak.model.User;

/**
 * Created by OskarPraca on 2017-03-04.
 */
@Controller
public class SQLController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/api/getUser", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        User user = userRepository.findByUsername("oski");

        // SELECT * FROM user WHERE username = 'oski';
        // ResultSet (key:value, key:value);
        // User user = new User();
        // user.setUsername(resultSet.get("username"));
        // user.setPassword(resultSet.get("password"));

        return user.toString();
    }

}
