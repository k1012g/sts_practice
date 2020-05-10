package com.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// AOPクラスにはAspectアノテーションをつける
// 同時にDIコンテナで管理するためにComponentをつける
@Aspect
@Component
public class LogAspect {
//	Beforeアノテーションの後に特定のメソッドを指定
//	execution(<戻り値><パッケージ名>.<クラス名>.<メソッド名>(<引数>) 
	@Before("execution(* *..*.*Controller.*(..))")
	public void startLod(JoinPoint jp) {
		System.out.println("メソッド開始 : " + jp.getSignature());
	}
	
	@After("execution(* *..*.*Controller.*(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了 : " + jp.getSignature());
	}
	
//	@Around(bean(* Controller))
//	@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
//	@Around("@within(org.springframework.stereotype.Controller)")

	@Around("execution(* *..*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp)throws Throwable {
		System.out.println("メソッド開始 : " + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			System.out.println("メソッド終了 : " + jp.getSignature());
			return result;
		}catch(Exception e) {
			System.out.println("メソッド異常終了 : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
	
//	UserDaoクラスのログを出力
	@Around("execution(* *..*.*UserDao*.*(..))")
	public Object daoLog(ProceedingJoinPoint jp)throws Throwable {
		System.out.println("メソッド開始 : " + jp.getSignature());
		
		try {
			Object result = jp.proceed();
			System.out.println("メソッド終了 : " + jp.getSignature());
			return result;
		}catch(Exception e) {
			System.out.println("メソッド異常終了 : " + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}
	
}