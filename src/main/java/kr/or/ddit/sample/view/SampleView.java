package kr.or.ddit.sample.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.sample.dao.SampleDAOImpl_Postgre;
import kr.or.ddit.sample.service.SampleService;
import kr.or.ddit.sample.service.SampleServiceImpl;

@Component
public class SampleView {
	public static void main(String[] args) {
//		SampleDAO dao = new SampleDAOImpl_Postgre();
//		SampleService service = new SampleServiceImpl(dao);
		ApplicationContext context =
				new ClassPathXmlApplicationContext(
						"kr/or/ddit/sample/conf/Parent-Context.xml"
						, "kr/or/ddit/sample/conf/auto/SampleAutoDI-Context.xml"
				); //이때 딱 한번 new가 나옴 길을 잃으면 안돼~ container 안의 객체를 받아서 쓰기 위해 온 거임
		SampleService service = context.getBean(SampleService.class); 
		StringBuffer model = service.retrieveInfomation("PK_2");
		System.out.println(model);
	}
}
