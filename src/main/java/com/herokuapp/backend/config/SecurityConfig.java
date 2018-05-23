package com.herokuapp.backend.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.herokuapp.backend.auth.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendRedirect("/denied"))
                .and().authorizeRequests()
                .antMatchers("/", "/denied").permitAll()
                .anyRequest().permitAll()
                //TODO change anyRequest to authentificated afte the tests are done
                .antMatchers(OPTIONS).permitAll().antMatchers(PUT).permitAll().and().cors()
                .and().csrf().disable().sessionManagement().sessionCreationPolicy(STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedMethod(OPTIONS);
        corsConfiguration.addAllowedMethod(PUT);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException{
        InputStream credentials = new ByteArrayInputStream(file.getBytes());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(credentials)).build();
        FirebaseApp.initializeApp(options);
        return FirebaseAuth.getInstance();
    }

    private AuthFilter authenticationTokenFilterBean() throws Exception{
        return new AuthFilter(authenticationManager());
    }

    //TODO - move to file
    String file = "{\n" +
            "  \"type\": \"service_account\",\n" +
            "  \"project_id\": \"taxihub-ef40c\",\n" +
            "  \"private_key_id\": \"a25f1458ca721f1433fb3a8b5d9dbfa55a239d20\",\n" +
            "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC1z6hqioTbGWd7\\nAhlOZjiFU26UVNtPvbH4hJotdD56ldPHyGrEqLTGtGP34zPzsSc1BYXMB7Bw8roh\\nzZ1QRnI8haWttbai9cvttZQbMSbouvACdNk+vcBtkoZKq8Aeou7wWiU3vlOuqIFX\\nnZIfF3BynJo5xBN9t5j4hwSHHIS84CF9QXYDChcEvJBU5/dztYorGkmA70y+qvYB\\n58v4ZBgznqsEv/mY0sJ1Bv6agQOsxAKj476m9Z+zcoE4foM0ZFa0kIBN1m5baxlw\\nG5bZzbIVByQNJqQ18SGG/hhym5ZmDN2uO/iEcdDeFiOGnoX1Vcyutc+xXAEVcmob\\nqOOBduaZAgMBAAECggEAAku9l8opLH7JC/2w4J4mq2ZW5eLJbzEZINHZ2ZDszg2m\\nIjvMFLDVsZGrFoEFEa15w4VkP7k+zOT/x+KpuyRcl8TknJ7dZXg+9kDoqSGT2R9X\\n3ba93I6MXMXbfMYSSRYWeCyx6MmLkyUibFRmI+Q0TGThB1w2wSES+oZynpspVkeP\\nykbQkKxJfP3ngAoYsMpuaZTw9GB6snws8Z/TBR4fw06EprsKSLGaaKG+fD7P93fq\\nm1oB3c6uJo7KHiE0EueJY+Zpk7ClELT2fZ9yhu8DpLk6r3FbHNn8XFcrmJNUisO5\\nQKjLI+SepS6jx2mim1IVUEers5Omk3waIYRm7FQfwQKBgQDzfj+KxSYhaZe8fdy2\\nkf5BZNWATorYEo56cbW3Qt0SFwlSrTwLedi68kMh+TicHWOkUjeZFNqwkCY79p6g\\nab9VhDRIUEGZDSqzJ1yKXWP0B6cJ1fStAXLv2XMg0jxXROZL/y089HPce+xOFvv8\\n9vz5Ev2py686xCkOs6FZ4/M4eQKBgQC/JlZ+VYXfMjWptepQxR3Ko6sjecQYMT3j\\nEl5FZUm1oPgJJxIxli7D0qETuHfY+SCsf2srr5Sk+okuUHFdYi8pAm79/ASG726O\\n4jH/Wa1v3ytWZ794DlZGnJjhlnHSZRiky664doU5m1Pw5imNuXyxkn7msMx1vt1d\\nEiCcsmfXIQKBgQDn70fjjWuGxCgu+cy1c1c3Py9HaolCzFo/M/UdvWkLBDpzMGRT\\nohrZ6E4eHGDaA/yX12DyRjURnh1tXLs4ncINWULb5ua1PR+BS2sQuWiuWGO6ML1B\\n1nF/GihaCShFEHI1zblvqIJ8cOZkc+xPx4ysTHSnukcNuTBKurxk/Yb+MQKBgAwM\\ne5k9BNI71Pwclmd+9VDHnkr/KugnenIsvzp6lvnW6quRBemQQORhC3w1vYA0u+WD\\nppq7V5BKpoj/0xWC0dww5hQmZZan0v04rtQcr/0EQTQSGBSWOsP6rPxTQltzuejx\\nWdMQzXcDHnhp2aawUXbT+CHMdSba4BM0yE94Q2lhAoGBAIzXxobam0aP1fRdMNOf\\nNuTj4/f2ctmpBvpBQQvwEXRdASZBQ31wQ8ISbk15jzVp/9+TlNUf2NWuD8+/OrKI\\nj2yTD5Y9pyEsdjiYYYQsxwHKKpn5FZmALD/pGVUHdL/iDtFRUHk8xr+bfVeANTih\\nF1YwQWbqLbavvRe1Ke/C/25G\\n-----END PRIVATE KEY-----\\n\",\n" +
            "  \"client_email\": \"firebase-adminsdk-vvuk6@taxihub-ef40c.iam.gserviceaccount.com\",\n" +
            "  \"client_id\": \"110932155257264991929\",\n" +
            "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
            "  \"token_uri\": \"https://accounts.google.com/o/oauth2/token\",\n" +
            "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
            "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-vvuk6%40taxihub-ef40c.iam.gserviceaccount.com\"\n" +
            "}";
}