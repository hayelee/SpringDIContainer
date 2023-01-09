package kr.or.ddit.sample.dao;

public class SampleDAOFactory {
	public static SampleDAO getSampleDAO() {
		return new SampleDAOImpl_Postgre(); //Business Logic Layer를 건드릴 필요가 없어짐
	}
}
