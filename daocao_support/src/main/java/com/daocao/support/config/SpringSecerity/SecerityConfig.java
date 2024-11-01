package com.daocao.support.config.SpringSecerity;

import com.daocao.support.filter.JwtAuthticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecerityConfig {
    /**
     * 引入userdetailsService
     *
     */
    private final SysUserDetailsService userDetailsService;
    private final JwtAuthticationFilter jwtAuthticationFilter;
    public SecerityConfig(SysUserDetailsService userDetailsService, JwtAuthticationFilter jwtAuthticationFilter, JwtAuthticationFilter jwtAuthticationFilter1) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthticationFilter = jwtAuthticationFilter1;

    }
    //创建AuthenticationManager
    @Bean
    public AuthenticationManager sysUserAuthenticationManager(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());

        provider.setUserDetailsService(userDetailsService);

        return new ProviderManager(provider);
    }


    /**
     * 配置过滤器链
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        禁用csrf
        http.csrf(csrf -> csrf.disable());
//        配置拦截策略
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/**").permitAll().anyRequest().authenticated());
//                .permitAll().authenticated());
//        开启form认证
        http.formLogin(Customizer.withDefaults());
        //    配置跨域
        http.cors(cors -> cors.configurationSource(configurationSource()));
//        配置过滤器
        http.addFilterBefore(jwtAuthticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    //    配置密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //    配置跨域 配置configurationSource
    public CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//        创建CorsConfigurationSource对象
        UrlBasedCorsConfigurationSource  urlBasedCorsConfigurationSource=new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
