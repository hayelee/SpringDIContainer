package kr.or.ddit.memo.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.memo.MemoTestView;
import kr.or.ddit.memo.dao.FileSystemMemoDAOimpl;
import kr.or.ddit.memo.dao.MemoDAO;
import kr.or.ddit.memo.service.MemoService;

@ComponentScan("kr.or.ddit.memo") //4개의 어노테이션이 동작할 수 있는 조건이 만들어짐
@Lazy //모든 bean들이 Lazy한 상태가 됨
public class MemoContextConfiguration {
//	@Bean //dao하나를 bean으로 등록한 것
//	@Scope("prototype") //매번 injection될때마다 새로운 객체 생성, 지연시킨다는 의미 포함
//	public MemoDAO memoDAO() {
//		return new FileSystemMemoDAOimpl();
//	}
//	
//	@Bean //bean이라는 의존관계를 아래 코드를 통해 실행, 필요한 게 있으면 container를 통해서 받아
//	public MemoService generateService(MemoDAO dao) {
//		return new MemoService(dao);
//	}
//	
//	@Bean("testView") //bean의 아이디는 기본적으로 메소드 이름! 맘에 들지 않으면 singlevalue annotation으로 설정해줘도 됨
//	public MemoTestView testView(MemoService service) {
//		MemoTestView view = new MemoTestView();
//		view.setService(service);
//		return view;
//	}
}
