package com.busreservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.busreservation.filter.JwtAuthFilter;
import com.busreservation.utility.Constants.UserRole;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter authFilter;

	@Bean
	// authentication
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
		        .cors(cors -> cors.disable())
		    
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/api/user/login", "/api/user/register",
								 "/api/journey/book/fetch/seatDetails","/api/journey/fetch/all","/api/journey/search","/api/journey/class/all",
								 "/api/busStop/fetch/all", "/api/user/admin/register","/api/journey/book/fetch").permitAll()
						
						// this APIs are only accessible by ADMIN
						.requestMatchers("/api/bus/add","/api/bus/fetch/all", "/api/busStop/add", "/api/journey/book/fetch/all", "/api/journey/add"
								,"/api/journey/status/all","/api/journey/update/status","/api/user/fetch/role","/api/user/update/status")
						.hasAuthority(UserRole.ROLE_ADMIN.value())
						
						
						// this APIs are only accessible by CUSTOMER
						.requestMatchers("/api/journey/book/add","/api/journey/book/fetch/user",
								"/api/journey/book/ticket/cancel","/api/user/add/wallet/money","/api/user/passenger/wallet/fetch")
						.hasAuthority(UserRole.ROLE_PASSENGER.value())
						
						// this APIs are only accessible by BANK & ADMIN
						.requestMatchers("/api/journey/book/download/ticket")
						.hasAnyAuthority(UserRole.ROLE_ADMIN.value(), UserRole.ROLE_PASSENGER.value())
						
						.anyRequest()
						.authenticated())
		        
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
