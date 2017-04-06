package pl.oskarpolak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.oskarpolak.UserRepository;
import pl.oskarpolak.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by OskarPraca on 2017-03-04.
 */
@Controller
public class SQLController {

    @Autowired
    UserRepository userRepository;


    @RequestMapping(value = "/api/test", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        //Page<User> users = userRepository.findByIdGreaterThan(5, new PageRequest(0, 5, new Sort("username")));

        // SELECT * FROM user WHERE username = 'oski';
        // ResultSet (key:value, key:value);
        // User user = new User();
        // user.setUsername(resultSet.get("username"));
        // user.setPassword(resultSet.get("password"));

//        StringBuilder builder = new StringBuilder();
//        for(User user : users.getContent()) {
//          builder.append(user.toString() + "<br>");
//        }
//
//        builder.append("<br><br>");
//
//        builder.append("Wszystkie strony: " + users.getTotalPages() + "<br>");
//        builder.append("Wszystkie elementy: " + users.getTotalElements() + "<br>");
//        builder.append("Ma następną stronę?: " + users.hasNext() + "<br>");
//
//        users.nextPageable();
        User user = userRepository.findById(3);
        return user.toString();
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @RequestMapping("/api/mail")
    public ResponseEntity sendMail(){
        MimeMessage mail = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("marszalek.d@gmail.com");
            helper.setReplyTo("oskar@microsoft.com");
            helper.setFrom("oskar@microsoft.com");
            helper.setSubject("Witaj swiecie");
            helper.setText("HELLO!", true);

        } catch (MessagingException e) {
            e.printStackTrace();
        }



        javaMailSender.send(mail);
        return new ResponseEntity(HttpStatus.OK);
    }
}
