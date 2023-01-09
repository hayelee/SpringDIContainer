package kr.or.ddit.sample.service;

import java.io.File;
import java.util.Calendar;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.dao.SampleDAOFactory;
import kr.or.ddit.sample.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.sample.dao.SampleDAOImpl_Postgre;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleServiceImpl implements SampleService, ApplicationContextAware {
	private ConfigurableApplicationContext context;
	private Resource log4j2xml;
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException { // bean에 ApplicationContextAware가 있으면 알아서 호출해줘
		this.context = (ConfigurableApplicationContext) arg0;
		log.info("ApplicationContextAware 구조로 컨테이너 주입됨.");
	}
	
	public void init() {
		log.info("{} 객체 초기화", getClass().getSimpleName());
		log4j2xml = context.getResource("classpath:log4j2.xml");
		log.info("{} 리소스 로딩 완료", log4j2xml);	
		
	}
	
	public void destroy() { // 이름은 아무거나 해도 됨~
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}

	// case1 : 의존객체를 new 키워드로 직접 생성.(결합력 최상) 문제점?
//	private SampleDAO dao = new SampleDAOImpl_Oracle();
//	private SampleDAO dao = new SampleDAOImpl_Postgre();
	
	// case2 : Factory Object[Method] Pattern, 결합력이 factory로 넘어감
//	private SampleDAO dao = SampleDAOFactory.getSampleDAO(); // _Oracle와 _Postgre의 결합력이 발생하지 않음.
	
	// case3 : Strategy Pattern, 전략 주입자가 반드시 필요함. sampleView가 service를 사용하려면 DAO를 받아야함, 전략 주입자가 모든 결합력을 떠안게됨
	private SampleDAO dao; // 의존해야 하는 전략객체를 내가 생성하지 않고, 상황에 따라 주입받아 사용.
	
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
		log.info("{} 객체 생성 및 전략 객체({}) 주입", getClass().getSimpleName(), dao.getClass().getSimpleName());
	}
	
	// case4 : DI Container
	public SampleServiceImpl() {
		super();
		log.info("기본 생성자로 {} 객체 생성", getClass().getSimpleName());
	}
	
	public void setDao(SampleDAO dao) {
		this.dao = dao;
		log.info("{} 객체 생성 후 setter를 통해 {}주입받음"
					, getClass().getSimpleName()
					, dao.getClass().getSimpleName());
	}
	
	@Override
	public StringBuffer retrieveInfomation(String pk) {
		String data = dao.selectRowData(pk);
		StringBuffer infomation = new StringBuffer();
		infomation.append(
			String.format("%tc에 조회된 데이터를 가공함 - %s", Calendar.getInstance(), data)
		);
		
		return infomation;
	}
}
