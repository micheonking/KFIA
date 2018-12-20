package myApp.server.cst;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;

public class IcamAcc {
	
	String	mapperName = "cst03_icam_acc";
	
	public void selectByMgCombo (SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByMgCombo");  
		result.setRetrieveResult(1, "select OK",  list);
	}

	public void checkEmail (SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		String message = " ID(Email) 중복확인중 오류발생. 고객지원실에 문의바랍니다.";
		result.setMessage(message);

		request.putStringParam("email", request.getStringParam("email"));

		System.out.println("fundName param: " + request.getStringParam("fundName"));

		Long count = sqlSession.selectOne("sys02_user.checkEmail", request.getParam());
		System.out.println("count is " + count); 

		if(count == 0) {
			result.setMessage("사용가능한 ID(Email)입니다.");
		} else {
			result.setMessage("이미 등록된 ID(Email)입니다.");
		}
		result.setStatus(1); 
	}
}
