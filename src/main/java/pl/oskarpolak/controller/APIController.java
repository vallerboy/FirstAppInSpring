package pl.oskarpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.oskarpolak.UserRepository;
import pl.oskarpolak.model.User;

/**
 * Created by OskarPraca on 2017-03-03.
 */

@Controller
public class APIController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/api/user/{username}",
            method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getUser(@PathVariable("username") String id) {

          User user = userRepository.findByUsername(id);
          if(user == null) {
              return new ResponseEntity(HttpStatus.BAD_REQUEST);
          }

//        User user = new User();
//        user.setPassword("mojehaslo");
//        user.setUsername("oski");
//        user.setRole("ADMIN");

        return new ResponseEntity(user, HttpStatus.OK);
    }


}
