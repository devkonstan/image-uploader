package com.example.imageuploader;

import com.example.imageuploader.model.AppUser;
import com.example.imageuploader.repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private AppUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, AppUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser(
//               new User("Jan",passwordEncoder().encode("Jan123"), Collections.singleton(new SimpleGrantedAuthority("user"))));
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //endpoints
                .antMatchers("/test1").hasRole("USER")
                .antMatchers("/test2").hasRole("ADMIN")
                .antMatchers("/upload-image").hasRole("ADMIN")
                .antMatchers("/gallery").hasRole("USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable(); // protect from connecting by external host
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() { // ROLE prefixes are necessary
        AppUser appUser = new AppUser("Jan", passwordEncoder().encode("Nowak"), "ROLE_USER");
        AppUser appAdmin = new AppUser("Admin", passwordEncoder().encode("1234"), "ROLE_ADMIN");
        appUserRepo.save(appAdmin);
        appUserRepo.save(appUser);
    }

}
