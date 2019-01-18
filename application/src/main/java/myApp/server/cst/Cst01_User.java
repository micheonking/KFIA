package myApp.server.cst;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.apr.model.Apr01_ApprModel;
import myApp.client.vi.apr.model.Apr03_RelateFundModel;
import myApp.client.vi.apr.model.Apr04_ApprStepModel;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.dcr.model.Dcr01_ClassTreeModel;
import myApp.client.vi.opr.model.Opr01_CreateModel;
import myApp.client.vi.pln.model.Pln02_PlanModel;
import myApp.client.vi.sys.model.Sys05_UserRoleModel;
import myApp.server.apr.Apr04_ApprStep;
import myApp.server.utils.db.UpdateDataModel;

public class Cst01_User { 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst01_user.selectById"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("userId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void checkEmail (SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		String message = " ID(Email) 중복확인 중 오류발생. 고객지원실에 문의바랍니다.";
		result.setMessage(message);

		System.out.println("email ====> " + request.getStringParam("email")); 

		Long count = sqlSession.selectOne("cst01_user.checkEmail", request.getParam());
		if(count == 0) {
			result.setMessage("사용가능한 ID(Email)입니다.");
			result.setStatus(1);
		} else {
			result.setMessage("사용불가합니다. 이미 등록된 ID(Email)입니다.");
			result.setStatus(-1);
		}
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		String message = "회원가입 처리중 오류발생.고객지원실에 문의바랍니다.";
		result.setMessage(message);
		
		//User정보 생성
		Cst01_UserModel userModel = (Cst01_UserModel)request.getModelParam("userModel");
		sqlSession.update("cst01_user.insert", userModel);
		System.out.println("User정보 생성 완료");
		
		//계좌정보 생성
		UpdateDataModel<Cst02_AccountModel> updateModel = new UpdateDataModel<Cst02_AccountModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), "cst02_account", result);
		System.out.println("계좌정보 생성 완료");
		
		//사용자별 Role정보 생성
		Sys05_UserRoleModel userRoleModel = new Sys05_UserRoleModel();
		userRoleModel.setUserRoleId(sqlSession.selectOne("dbConfig.getSeq"));
		userRoleModel.setUserId(userModel.getUserId());
		userRoleModel.setRoleId((long)1000000);	//웹 사용자
		userRoleModel.setNote("한채투 고객지원사이트 사용자");
		sqlSession.update("sys05_user_role.insertUpdate", userRoleModel);
		System.out.println("사용자별 Role정보 생성 완료");
	}
}
