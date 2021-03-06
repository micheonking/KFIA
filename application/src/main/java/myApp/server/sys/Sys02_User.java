package myApp.server.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.barokey.barokey;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.LoginUser;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.emp.model.Emp00_InfoModel;
import myApp.client.vi.sys.model.Sys00_CommonComboBoxModel;
import myApp.client.vi.sys.model.Sys01_CompanyModel;
import myApp.client.vi.sys.model.Sys02_UserModel;
import myApp.server.utils.db.UpdateDataModel;

public class Sys02_User {
	
	private String mapperName = "sys02_user";
	
	public void getLoginInfo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		Long companyId = request.getLongParam("companyId");
		String loginId = request.getStringParam("loginId").replaceAll(" ", "");
//		String passwd = request.getStringParam("passwd");
		String otpNumber = request.getStringParam("otpNumber");
		
		if ((otpNumber == null)||("".equals(otpNumber.replaceAll(" ", "")))) {
			result.fail(-1, "OTP인증번호를 입력하여 주십시오.");
			return;
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId",  companyId);
		param.put("loginId", loginId);
		
		System.out.println("companyId : " + companyId);
		System.out.println("loginId   : " + loginId);
		
		Cst01_UserModel cstUserModel = sqlSession.selectOne("cst01_user.selectByLoginId", param) ;
		if(cstUserModel == null) {
			result.fail(-1, "미등록된 ID(E-Mail)입니다. 회원가입 후 로그인 하여 주십시오.");
			return;
		} else {
			String emailAddr	= cstUserModel.getEmail(); 
			String mobileTelno	= cstUserModel.getPhoneNo().replace("-", "");
			String cycleTime	= "60";

			System.out.println("emailAddr   => [" + emailAddr + "]");
			System.out.println("mobileTelno => [" + mobileTelno + "]");
			System.out.println("otpNumber   => [" + otpNumber + "]");
			System.out.println("UserId   => [" + cstUserModel.getUserId() + "]");

			if (barokey.verifyKEYL(emailAddr, mobileTelno, cycleTime, otpNumber)) {
				Map<String, Object> fundParam = new HashMap<String, Object>();
				fundParam.put("userId", cstUserModel.getUserId());
				List<Sys00_CommonComboBoxModel> fundList = sqlSession.selectList("cst02_account.selectFundCodeList", fundParam);
				if (fundList.size() == 0) {
					System.out.println("계좌정보 미등록!!!");
					result.setModel(40, "no account!", cstUserModel);
				} else {
					cstUserModel.setFundCode(fundList.get(0).getCode());
					cstUserModel.setFundComboBoxName(fundList.get(0).getName());
					result.setModel(30, "find employee user!", cstUserModel);
				}
			} else {
				result.fail(-1, "OTP인증번호가 틀립니다.");
			}
		}
		return;


		// 사원부터 찾는다. 
//		Emp00_InfoModel empInfo = sqlSession.selectOne("emp00_info.selectByLoginId", loginId) ;
//		if(empInfo != null) {
//			String emailAddr	= empInfo.getEmailAddress(); 
//			String mobileTelno	= empInfo.getMobileTelNo().replace("-", "");
//			String cycleTime	= "60";	//	empInfo.getCycleTime();
//			
//			System.out.println("emailAddr   => [" + emailAddr + "]");
//			System.out.println("mobileTelno => [" + mobileTelno + "]");
//			System.out.println("cycleTime   => [" + cycleTime + "]");
//			System.out.println("otpNumber   => [" + otpNumber + "]");
//			
//			Sys01_CompanyModel companyModel = sqlSession.selectOne("sys01_company.selectById", empInfo.getCompanyId());
//			if (otpNumber.equals(companyModel.getTelNo02())) {
//				result.setModel(10, "pass user!", empInfo);
//			} else {
////				if (barootp.verifyTOTPL(emailAddr, mobileTelno, cycleTime, otpNumber)) {
//				if (barokey.verifyKEYL(emailAddr, mobileTelno, cycleTime, otpNumber)) {
////					// find emp info check password or OTP number => return 10
//					result.setModel(10, "find employee user!", empInfo);
//				} else {
//					result.fail(-1, "OTP인증번호가 틀립니다.2");
//				}
//			}
//			return ;
//		}

		// 없으면 user table에서 찾는다., user로 등록된 사람은 관리자이다. 
//		Sys02_UserModel userInfo = sqlSession.selectOne(mapperName + ".selectByLoginId", loginId) ;
//		if(userInfo != null) {
//			// find user info => return 20
//			result.setModel(20, "login OK", userInfo);
//			return ; 
//		}

		// not found employee or user 
//		result.fail(-1, "등록된 사용자 정보가 아닙니다. 입력정보를 확인하여 주십시요!");
//		return ; 
	}

	public void getLoginAdminInfo(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		Long   companyId = request.getLongParam("companyId");
		String otpNumber = request.getStringParam("otpNumber");
		
		if ((otpNumber == null)||("".equals(otpNumber))) {
			result.fail(-1, "OTP인증번호를 입력하여 주십시오.");
			return;
		}
		
		Sys01_CompanyModel companyModel = sqlSession.selectOne("sys01_company.selectById", companyId);
		if (otpNumber.equals(companyModel.getTelNo02())) {
			result.setModel(10, "pass user!", null);
		} else {
			result.fail(-1, "OTP인증번호가 틀립니다.3");
		}
		return ;
	}
	
	public void selectByAll(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByAll");
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByUserName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {

		String korName = request.getStringParam("userName");
		
		if(korName == null){
			korName = "";
		}
		korName = "%" + korName + "%"; 
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", LoginUser.getCompanyId());
		param.put("korName", korName); 
		
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByUserName", param);
		result.setRetrieveResult(1, "select ok", list);
	}

	public void selectByCompanyId(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		Long clientId = request.getLongParam("companyId"); 
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByCompanyId", clientId);
		result.setRetrieveResult(1, "select ok", list);
	}
	
	public void selectByName(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		
		Map<String, Object> param = new HashMap<String, Object>(); 
		
		String userName = request.getStringParam("userName") ; 
		if(userName != null){
			userName = "%" + userName + "%"; 
		}
		else {
			userName = "%"; 
		}
		
		param.put("companyId", request.getLongParam("companyId"));
		param.put("korName", userName);
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectByName", param);
		result.setRetrieveResult(1, "select ok", list);
	}
	
//	public void updateAdminUser(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
//		
//		Long companyId = Long.parseLong("2000001"); 
//		
//		UpdateDataModel<Sys02_UserModel> updateModel = new UpdateDataModel<Sys02_UserModel>();
//		List<GridDataModel> updateList = new ArrayList<GridDataModel>(); 
//		
//		for(GridDataModel model : request.getList()){
//			Sys02_UserModel userModel = (Sys02_UserModel)model; 
//			if(userModel.getCompanyId() == null){
//				userModel.setCompanyId(companyId);
//			}
//			updateList.add(userModel);
//		}
//		updateModel.updateModel(sqlSession, updateList, mapperName, result);
//	}
	
	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Sys02_UserModel> updateModel = new UpdateDataModel<Sys02_UserModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Sys02_UserModel> updateModel = new UpdateDataModel<Sys02_UserModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
