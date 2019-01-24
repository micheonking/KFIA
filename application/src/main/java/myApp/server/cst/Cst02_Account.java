package myApp.server.cst;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.sys.model.Sys00_CommonComboBoxModel;
import myApp.server.utils.db.UpdateDataModel;

public class Cst02_Account { 
	
	private	String mapperName = "cst02_account"; 

	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		String sqlId = this.mapperName + ".selectById"; 
		List<GridDataModel> list = sqlSession.selectList(sqlId, request.getLongParam("userId")) ;
		result.setRetrieveResult(1, sqlId, list);
	}

	public void getAccInfo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		Cst02_AccountModel accModel = (Cst02_AccountModel)request.getModelParam("accModel");

		System.out.println("mgCode    : " + accModel.getMgCode());
		System.out.println("accountNo : " + accModel.getAccountNo());
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("mgCode"   , accModel.getMgCode());
		param.put("accountNo", accModel.getAccountNo().replaceAll(" ", ""));

		List<GridDataModel> list = sqlSession.selectList("cst02_account.getAccInfo", param);
		System.out.println("size : " + list.size());
		if (list.size() == 0) {
			result.setMessage("계좌정보 조회 실패. 증권사 및 계좌번호를 확인하여 주십시오.");
			result.setStatus(-1);
		} else {
			result.setRetrieveResult(list.size(), "select ok", list);
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
		String date = sqlSession.selectOne("cst02_account.getSettlementDate", request.getParam());
		if(date == null) {
			result.setMessage("결산정산내역이 없습니다.(2)");
			result.setStatus(-1);
		} else {
			result.setMessage(date);
			result.setStatus(1);
		}
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
