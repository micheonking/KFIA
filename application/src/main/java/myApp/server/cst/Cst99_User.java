package myApp.server.cst;

import java.util.ArrayList;
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
import myApp.client.vi.pln.model.Pln03_ResrchModel;
import myApp.server.apr.Apr04_ApprStep;
import myApp.server.utils.db.UpdateDataModel;

public class Cst99_User { 
	
	private	String mapperName = "cst99_user"; 

	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = this.mapperName + ".selectById"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("userId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void selectByAccountList(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		request.putLongParam("userId", request.getLongParam("userId") );
		System.out.println("userId   param: " + request.getLongParam("userId") ); 
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByAccountList", request.getLongParam("userId"));
		System.out.println("listSize "+ list.size());
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByAccountName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		request.putLongParam("userId", request.getLongParam("userId") );
		request.putStringParam("accountName", request.getStringParam("accountName") );

		System.out.println("userId   param: " + request.getLongParam("userId") ); 
		System.out.println("accountName  param: " + request.getStringParam("accountName") ); 

		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByAccountName", request.getParam());
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByUserId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		System.out.println("userId   param: " + request.getLongParam("userId") ); 

		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByUserId", request.getLongParam("userId"));
		result.setRetrieveResult(1, "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		System.out.println("0000000000");

		String message = "회원가입 처리중 오류발생.고객지원실에 문의바랍니다.";
		result.setMessage(message);
		
		System.out.println("1111111111111");

		//User정보 생성
		Cst01_UserModel userModel = (Cst01_UserModel)request.getModelParam("userModel");
		sqlSession.update(mapperName + ".insert", userModel);

		System.out.println("2222222222");

		//계좌정보 생성
		List<GridDataModel> list = request.getList();
		List<GridDataModel> updateList = new ArrayList<GridDataModel>();
		System.out.println("333333333333");

		//펀드코드 셋팅
		for(GridDataModel data : list) {
			System.out.println("4444444444444");

			Cst02_AccountModel accModel = (Cst02_AccountModel)data ; 

//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("mgCode", accModel.getMgCode()); 
//			param.put("accNo" , accModel.getAccountNo()); 
//
//			String fundCode = sqlSession.selectOne("cst03_icam_acc.getFundCode", param);
//			if(fundCode == null) {
//				result.setMessage("계좌정보 가져오기 실패. 증권사와 계좌번호를 확인하여 주십시오.");
//				result.setStatus(-1); 
//				return; 
//			} else {
//				System.out.println("fundCode ====>" + fundCode);
//				accModel.setFundCode(fundCode);
//			}
			updateList.add((GridDataModel)accModel); 
		}
		System.out.println("555555555555555555");

		//계좌정보 Insert
		UpdateDataModel<Cst02_AccountModel> updateModel = new UpdateDataModel<Cst02_AccountModel>();
		updateModel.updateModel(sqlSession, updateList, "cst02_account", result);
		System.out.println("66666666666666666");

//		userModel.setMrdRole("kjkk");
//		userModel.setRefreshTime("k123jkk");
		System.out.println("Cst99_User입니다.======!!!!!" + userModel.getUserId());
		System.out.println("Cst99_User입니다.======!!!!!" + userModel.getEmail());
		System.out.println("Cst99_User입니다.======!!!!!" + userModel.getUserName());
		
//		sqlSession.update(mapperName + ".insert", userModel);
		
//		UpdateDataModel<Cst01_UserModel> updateModel = new UpdateDataModel<Cst01_UserModel>(); 
//		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Cst01_UserModel> updateModel = new UpdateDataModel<Cst01_UserModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
	
}
