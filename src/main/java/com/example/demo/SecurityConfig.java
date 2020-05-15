package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 静的リソースへのアクセスにはセキュリティを適用しない
		web.ignoring().antMatchers("/webjars/**", "/css/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//　直リンクの禁止
		// ログイン不要ページへの設定
		http.authorizeRequests().antMatchers("/webjars/**").permitAll()
								.antMatchers("/css/**").permitAll()
								.antMatchers("/login").permitAll()
								.antMatchers("/signup").permitAll()
								// それ以外のページは直リンク禁止
								.anyRequest().authenticated();
		// CSRF対策を一時的に無効に設定
		http.csrf().disable();
	}
}