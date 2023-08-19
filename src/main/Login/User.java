// @Document(collection = "users")
// public class User {
// @Id
// private String id;
// private String username;
// private String password;
// private List<String> roles;

// }
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//             .authorizeRequests()
//             .antMatchers("/login", "/login/oauth2/code/tmdb").permitAll()
//             .anyRequest().authenticated()
//             .and()
//             .oauth2Login()
//             .loginPage("/login") // La pagina di login personalizzata dove reindirizzare gli utenti a TMDb
//             .defaultSuccessUrl("/success") // Pagina di successo dopo l'autenticazione
//             .and()
//             .logout().logoutSuccessUrl("/logout");
//     }
// }
