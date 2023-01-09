package kr.or.ddit.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) {
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/DIContainer-Context.xml"); //container를 resourceloader로 쓰기 위해 사용
		Resource resource1 = context.getResource("file:D:/contents/images/cat1.jpg"); //file resource 자원을 찾고 검색하고 하는 작업이 간편해짐
		Resource resource2 = context.getResource("classpath:log4j2.xml"); //classpath resource
		Resource resource3 = context.getResource("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"); //web resource
		
		System.out.printf("file system resource : %s\n", resource1.getClass().getSimpleName());
		System.out.printf("class path resource : %s\n", resource2.getClass().getSimpleName());
		System.out.printf("web resource : %s\n", resource3.getClass().getSimpleName());
		
	}
}
