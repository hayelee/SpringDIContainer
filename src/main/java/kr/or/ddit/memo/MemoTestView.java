package kr.or.ddit.memo;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import kr.or.ddit.memo.conf.MemoContextConfiguration;
import kr.or.ddit.memo.service.MemoService;

@Controller
public class MemoTestView {
	private MemoService service;
	@Required
	@Inject
	public void setService(MemoService service) {
		this.service = service;
	} //setter를 호출하지 않으면 그만이야 
	
	public void printMemoList() {
		service.retrieveMemoList().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
//		//container생성
//		ConfigurableApplicationContext context =
//				new GenericXmlApplicationContext("classpath:kr/or/ddit/memo/conf/auto/root-context.xml");
//		context.registerShutdownHook(); //닫아주기
//		
//		ConfigurableApplicationContext childContext =
//				new ClassPathXmlApplicationContext(
//					new String[] {"kr/or/ddit/memo/conf/auto/servlet-context.xml"}
//					,context
//				);
//		context.registerShutdownHook(); //닫아주기
//		childContext.registerShutdownHook();
		
		ConfigurableApplicationContext context = 
				new AnnotationConfigApplicationContext(MemoContextConfiguration.class);	
		context.registerShutdownHook();
		
		MemoTestView view = context.getBean(MemoTestView.class);
		view.printMemoList();
		//		memoservice service = new memoservice();
//		
	}
}
