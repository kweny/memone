/*
 * Copyright (C) 2018 Apenk.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apenk.memone.application.config;

import org.apenk.memone.service.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security configurer
 *
 * @author Kweny
 * @since 0.0.1
 */
@Configuration("securityConfigurer")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MemoneSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/login";
    private static final String ADMIN_URL = "/admin/**";
    private static final String[] ADMIN_ROLES = {"ADMIN", "EDITOR", "AUTHOR", "CONTRIBUTOR"};

    @Override
    public UserDetailsService userDetailsService() {
        return getApplicationContext().getBean("authenticationService", AuthenticationService.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(LOGIN_URL).permitAll()
            .and().authorizeRequests()
                .antMatchers(ADMIN_URL).hasAnyRole(ADMIN_ROLES)
                .anyRequest().permitAll();
    }

    @Bean("passwordEncoder")
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}