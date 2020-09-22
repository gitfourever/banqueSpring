package ang.neggaw.config;

import ang.neggaw.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import sun.security.util.Password;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Autowired
    DataSource dataSource;

    @Qualifier("usersDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    //@Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);

        /*
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER").and()
                .withUser("user").password("{noop}1234").roles("USER").and()
                .withUser("user01").password("{noop}1234").roles("USER");
         */

        /*
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select email, password, enabled from user_banks where email = ?")
            .authoritiesByUsernameQuery("select id, email from clients where email = ?")
            .passwordEncoder(new BCryptPasswordEncoder());

         */


        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());



    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        http
                .authorizeRequests()
                    .antMatchers("/login.html", "/css/**", "/js/**", "/img/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login.html")
                    .defaultSuccessUrl("/banque.html", true)
                    .failureUrl("/login.html?error=true")
                    //.usernameParameter("username")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .csrf()
                    .disable();
    }
}
