package clinica.security;


import clinica.security.jwt.AuthEntryPointJwt;
import clinica.security.jwt.AuthTokenFilter;
import clinica.security.service.UsuarioDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig{
    @Autowired
    UsuarioDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


        @Bean
        public DaoAuthenticationProvider authenticationProvider(){
            DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(usuarioService);
            authProvider.setPasswordEncoder(bCryptPasswordEncoder);
            return authProvider;
        }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } }


    @Bean
    public SecurityFilterChain filterChain( HttpSecurity  httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .authenticationProvider(authenticationProvider())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/odontologos").hasRole("ROL_ADMIN")
                .requestMatchers("/api/domicilios").hasRole("ROL_ADMIN")
                .requestMatchers("/api/pacientes").hasRole("ROL_ADMIN")
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/turnos").permitAll()
                .requestMatchers("/api/test/**").permitAll()

                .anyRequest().authenticated();

        httpSecurity.authenticationProvider(authenticationProvider());

        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
    @Autowired
    private UsuarioDetailsServiceImpl usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/get_turno.html","/post_turno.html","/index.html").permitAll()
                .antMatchers("/get_paciente.html", "/get_odontologo.html","/post_odontologo.html", "/post_paciente.html").hasRole("ROL_ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();
    }

}