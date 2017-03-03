package pl.oskarpolak;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import pl.oskarpolak.auth.UserAuth;

/**
 * Created by OskarPraca on 2017-02-28.
 */

@Configuration
@EnableWebSecurity
public class SecureConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserAuth userAuth;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http


                .authorizeRequests()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                    .antMatcher("/api/**").csrf().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        ShaPasswordEncoder encoder  = new ShaPasswordEncoder();
        String passwordHashed = encoder.encodePassword("jakiesHasloDoZakodowania", null);

        auth.userDetailsService(userAuth).passwordEncoder(encoder);


      //  auth.inMemoryAuthentication().withUser("oskar").password("oskar1").roles("USER");
    }
}
