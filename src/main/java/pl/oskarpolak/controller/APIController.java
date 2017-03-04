package pl.oskarpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.oskarpolak.UserRepository;
import pl.oskarpolak.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

//        User user = new User();
//        user.setPassword("mojehaslo");
//        user.setUsername("oski");
//        user.setRole("ADMIN");

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody(required = false) User user) {
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        User userDatebase = userRepository.findByUsername(user.getUsername());
        if (userDatebase != null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        user.setPassword(new ShaPasswordEncoder().encodePassword(user.getPassword(), null));
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/{username}",
            method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity deleteUser(@PathVariable("username") String id) {

        User user = userRepository.findByUsername(id);
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userRepository.delete(user.getId());

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(value = "/api/user", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody(required =  false) User user){
        User userLocal = userRepository.findByUsername(user.getUsername());
        if(userLocal == null) {
           new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        userLocal.setUsername(user.getUsername());
        //userLocal.setPassword(user.getPassword());
        userLocal.setRole(user.getRole());
        userRepository.save(userLocal);
        return new ResponseEntity(HttpStatus.OK);
    }


    // to poniżej ignore
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/api/sendmail")
    public ResponseEntity sendMail(){
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("oskarbank@gmail.com");
            helper.setReplyTo("oskarbank@gmail.com");
            helper.setFrom("oskarbank@gmail.com");
            helper.setSubject("Witaj swiecie");
            helper.setText("HELLO!", true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mail);
        return new ResponseEntity(HttpStatus.OK);
    }

}
