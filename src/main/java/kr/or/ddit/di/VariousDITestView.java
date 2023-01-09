package kr.or.ddit.di;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {
	public static void main(String[] args) {
		//container객체 생성, 필요없으면 자동으로 소멸되게
		ConfigurableApplicationContext context = 
				new ClassPathXmlApplicationContext("kr/or/ddit/di/conf/VariousDI-Context.xml");
		context.registerShutdownHook();
		
		//유일한 bean을 현재 메인 메소드에 받아 bean을 출력되게
		VariousDIVO vo1 = context.getBean("vo1", VariousDIVO.class);
		VariousDIVO vo2 = context.getBean("vo2", VariousDIVO.class);
		log.info("주입된 객체 : {}", vo1);
		log.info("주입된 객체 : {}", vo2);

	}
}
