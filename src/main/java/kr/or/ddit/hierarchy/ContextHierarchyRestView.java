package kr.or.ddit.hierarchy;

import java.util.Map;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.sample.service.SampleService;

public class ContextHierarchyRestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parent = 
				new ClassPathXmlApplicationContext("kr/or/ddit/sample/conf/Parent-Context.xml");
		ConfigurableApplicationContext child = 
				new ClassPathXmlApplicationContext(
					new String[] {"kr/or/ddit/sample/conf/Sample-context.xml"}
					, parent
				);
		child.registerShutdownHook(); // 종료조건
		parent.registerShutdownHook();
		
		SampleService service = child.getBean(SampleService.class);
		System.out.println(service.retrieveInfomation("PK_2")); //parent에 있는 걸 child에서 주입받을 수 있다.
		
		Map<String, String> oracleDB = (Map) child.getBean("oracleDB");
		System.out.println(oracleDB); // 자식을 통해서 부모에 접근 가능
		
		SampleService service2 = parent.getBean(SampleService.class); // 부모를 통해서 자식에 접근 가능? 안된다~
	}
}
