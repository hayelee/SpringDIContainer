package kr.or.ddit.memo.dao;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.MemoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class FileSystemMemoDAOimpl implements MemoDAO, ApplicationContextAware{
	@Inject
	private ApplicationContext context;
	
	
	@PostConstruct // 가장 마지막에 실행
	public void init() { //context가 주입 되어있는 상태
		dataBase = context.getResource("file:d:/memo.dat"); //	private File dataBase = new File("d:/memo.dat"); 이 역할 대신함 // resource api 사용
		log.info("리소스 로딩 : {}", dataBase);
		try(
			InputStream fis = dataBase.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
		){
			memoTable = (Map<Integer, MemoVO>) ois.readObject();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			this.memoTable = new HashMap<>();
		}
	} 
	
	private Resource dataBase;
	private Map<Integer, MemoVO> memoTable; // 직렬화 역직렬화 대상


	@Override
	public List<MemoVO> selectMemoList() {
		return new ArrayList<>(memoTable.values());
	}

	@Override
	public int insertMemo(MemoVO memo) {
		int maxCode = memoTable.keySet().stream()
									.mapToInt(key->key.intValue())
									.max()
									.orElse(0);
		
		memo.setCode(maxCode+1);
		memoTable.put(memo.getCode(), memo);
		serializeMemoTable();
		return 1;
	}
	
	private void serializeMemoTable() {
		try(
			FileOutputStream fos = new FileOutputStream(dataBase.getFile());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
		){
			oos.writeObject(memoTable);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMemo(MemoVO memo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMemo(int code) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}
}
