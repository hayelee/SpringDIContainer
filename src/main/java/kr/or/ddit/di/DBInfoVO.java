package kr.or.ddit.di;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@Slf4j
public class DBInfoVO {
	private String driverClassName;
	private String url;
	private String user;
	private String password;
	
	private int initialSize;
	private int maxTotal;
	private int maxIdle;
	private int maxWait;
	
	public void init() {
		log.info("{} 객체 초기화 완료", getClass().getSimpleName());
	}
	
	public void destroy() {
		log.info("{} 객체 소멸", getClass().getSimpleName());
	}
}
