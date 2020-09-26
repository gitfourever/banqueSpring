package ang.neggaw.config;

import ang.neggaw.dao.ClientOnlineRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // @Autowired
    // DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    private final UserDetailsService userDetailsService;
    private final ClientOnlineRepository clientOnlineRepository;

    public WebSecurityConfig(ClientOnlineRepository clientOnlineRepository,
                             @Qualifier("usersDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.clientOnlineRepository = clientOnlineRepository;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // authentication with users saved in memory
        /*
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}1234").roles("ADMIN", "USER").and()
                .withUser("user").password("{noop}1234").roles("USER").and()
                .withUser("user01").password("{noop}1234").roles("USER");
         */

        // Authentication with DB users
        /*
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select email, password, enabled from user_banks where email = ?")
            .authoritiesByUsernameQuery("select id, email from clients where email = ?")
            .passwordEncoder(new BCryptPasswordEncoder());

         */

        // Authentication with DB users and JWT
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        /*
        http
            .authorizeRequests()
                .antMatchers("/seConnecter.html", "/css/**", "/js/**", "/img/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/seConnecter.html")
                .defaultSuccessUrl("/banque.html", true)
                .failureUrl("/seConnecter.html?error=true")
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
            .and()
                .csrf()
                .disable();
         */

        // Authentication with JWT
        http
            .csrf().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/home", "/login").permitAll()
                //.hasRole("ACTIF")
                .anyRequest()
                .authenticated()
            .and()
            .formLogin()
                //.loginPage("/login")
                //.successForwardUrl("/index")
                //.permitAll()
            .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(), clientOnlineRepository))
                .addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
