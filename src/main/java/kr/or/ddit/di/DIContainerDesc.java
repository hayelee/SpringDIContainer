package kr.or.ddit.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.sample.dao.SampleDAO;
import kr.or.ddit.sample.service.SampleService;

/**
 * Spring DI Container, IoC Container, Spring Container, Bean Container
 * 
 * 	Container ?? 객체(Bean)의 생명주기 관리자, 객체의 생성(소멸), 주입구조를 통한 의존관계 형성.
 * 
 * 	사용 단계
 * 	1. Spring bean configuration file :
 * 		bean 엘리먼트로 객체 등록
 * 	2. 등록된 bean 들간의 의존관 형성(주입 구조)
 * 		- 생성자 주입(constructor injection) - constructor-arg
 * 		- setter 주입(setter injection) - property
 *  3. 어플리케이션의 엔트리 포인트에서 컨테이너(ApplicationContext) 객체를 생성
 *  	- ClassPathXmlApplicationContext
 *  	- FileSystemXmlApplicationContext
 *  	- GenericXmlApplicationContext / 특별한 위치 지정하지 않은 경우 - resource loader 활용할 수 있는 prefix를 통해 자원의 위치 결정.
 * 	4. 필요 객체(bean) 주입 - getBean
 * 
 * 	컨테이너 빈 관리 정책 
 * 	1. 특별한 설정(scope)이 없는 한 등록된 모든 빈은 싱글턴으로 관리.
 * 		singleton : 빈 하나당 객체가 하나씩 생성 및 관리. 주입 횟수와 상관없이 하나의 객체가 공유.
 * 		prototype : 주입될때마다 새로운 객체가 생성 및 관리됨. 객체의 생성 시점이 지연됨.
 * 		request/session(web 용) : requestScope, sessionScope의 생명주기와 동일해짐.
 * 	2. 특별한 설정(lazy-init)이 없는 한 컨테이너가 초기화될 때 등록된 모든 객체를 일시에 생성함.
 *  3. 빈들의 객체 생성 순서(depends-on)를 제어할 수 있는 설정이 있음.
 *  4. 컨테이너는 빈의 생명주기를 관리할때 callback 구조를 활용함.
 *  	init-method : 객체가 생성되고, *필요한 주입이 완료된 후*, 마지막에 초기화 콜백 호출.
 * 		destroy-method : 컨테이너 종료시
 * 	5. 컨테이너에 등록된 빈에 컨테이너에 대한 참조 주소를 주입할 수 있음.
 * 
 */
public class DIContainerDesc {
	public static void main(String[] args) {
//		ApplicationContext context = 
		ConfigurableApplicationContext context = 
			new GenericXmlApplicationContext("classpath:kr/or/ddit/di/conf/DIContainer-Context.xml"); //알아서 kr을 classpath에서 찾는다
		context.registerShutdownHook(); // 종료가 될 시점이 되면 알아서 종료해라
//		SampleService service = (SampleService) context.getBean("service");
//		System.out.println(service.retrieveInfomation("PK_3"));
//		
//		SampleDAO dao1 = context.getBean("sampleDAOImpl_Oracle", SampleDAO.class); // 컨테이너가 제대로 동작하려면 개발자는 충분한 조건을 줘야함
//		System.out.printf("주입된 객체 : %s\n", dao1.getClass().getSimpleName());
//		System.out.println(dao1.hashCode());
		// 출력되는 해시코드가 같음 -> 계속 같은 객체가 주입되고 있다.싱글턴 활용되고 있다는 뜻(우리가 아는 싱글턴과 다름 싱글턴 디자인패턴의 대상은 class, spring 싱글턴의 디자인 패턴은 Bean)
	}
}
