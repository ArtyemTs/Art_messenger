package art.artmessenger.congig;

import art.artmessenger.domain.User;
import art.artmessenger.repo.UserDetailsRepo;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/").permitAll()  // позволяет ходить в root без авторизации
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }


    // Созранение авторизованного пользователя в БД
    // Фабричный метод:
    public PrincipalExtractor principalExtractor(UserDetailsRepo userDetailsRepo){

        return map -> {
            return new User();  //Возвращает заглушку в виде нового пользователя
        };
    }

}
