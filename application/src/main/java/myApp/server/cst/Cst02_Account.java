package myApp.server.cst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.server.utils.db.UpdateDataModel;

public class Cst02_Account { 
	
	private	String mapperName = "cst02_account"; 

	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = this.mapperName + ".selectById"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("accountId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void selectByUserId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = this.mapperName + ".selectByUserId"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("userId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void getAccInfo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

//		Cst02_AccountModel accModel = (Cst02_AccountModel)request.getModelParam("accModel");
		System.out.println("mgCode    : " + request.getStringParam("mgCode"));
		System.out.println("accountNo : " + request.getStringParam("accNo"));
		System.out.println("eMail     : " + request.getStringParam("eMail"));

		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("mgCode"   , accModel.getMgCode());
//		param.put("accountNo", accModel.getAccountNo().replaceAll(" ", ""));
		param.put("mgCode"   , request.getStringParam("mgCode"));
		param.put("accountNo", request.getStringParam("accNo"));

		Cst02_AccountModel accModel = sqlSession.selectOne("cst02_account.getAccInfo", param);
		if (accModel == null) {
			result.setMessage("계좌정보 조회 실패.<br>증권사 및 계좌번호를 확인하여 주십시오.");
			result.setStatus(-1);
		} else {
			String eMail = request.getStringParam("eMail");
			if(eMail.equals(accModel.geteMail())) {
				result.setModel(1, "select ok", accModel);
			} else {
				result.setMessage("계좌정보와 E-Mail정보가 다릅니다. 계좌개설시 등록된 메일주소를 확인하여 주십시오.");
				result.setStatus(-1);
			}
		}
	}

	public void selectFundCodeList(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst02_account.selectFundCodeList"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("userId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void selectMgCombo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst02_account.selectMgCombo"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void selectYearCombo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst02_account.selectYearCombo"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getParam()) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void selectSettlementDateCombo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst02_account.selectSettlementDateCombo"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getParam()) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void getSettlementDate(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		System.out.println("fundCode : " + request.getStringParam("fundCode"));
		System.out.println("ymd : " + request.getStringParam("ymd"));

		String date = sqlSession.selectOne("cst02_account.getSettlementDate", request.getParam());
		if(date == null) {
			result.setMessage("결산정산 내역이 없습니다.(2)");
			result.setStatus(-1);
		} else {
			result.setMessage(date);
			result.setStatus(1);
		}
	}

	public void selectCloseDateCombo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = "cst02_account.selectCloseDateCombo"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getParam()) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void getCloseDate(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String date = sqlSession.selectOne("cst02_account.getCloseDate", request.getParam());
		if(date == null) {
			result.setMessage("전부해지정산 내역이 없습니다.(2)");
			result.setStatus(-1);
		} else {
			System.out.println(date);
			result.setMessage(date);
			result.setStatus(1);
		}
	}

	public void updateOne(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String message = "계좌정보 등록중 오류발생.고객지원실에 문의바랍니다.";
		result.setMessage(message);
		
		Cst02_AccountModel accModel = (Cst02_AccountModel)request.getModelParam("accModel");
		sqlSession.update("cst02_account.insert", accModel);
		System.out.println("Account 생성 완료");
	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Cst02_AccountModel> updateModel = new UpdateDataModel<Cst02_AccountModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Cst02_AccountModel> updateModel = new UpdateDataModel<Cst02_AccountModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
	
}
