package myApp.server.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gwt.dev.util.collect.HashMap;

import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.sys.model.Sys01_CompanyModel;
import myApp.server.utils.db.UpdateDataModel;

public class Sys13_YearMonth {

	private String mapperName = "sys13_year_month"; 
	
	public void selectById(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
//		Long calendarId = request.getLongParam("calendarId"); 
		List<GridDataModel> list = sqlSession.selectList(mapperName + ".selectById",  0L);
		result.setRetrieveResult(list.size(), "select ok", list);
	}

	public void update(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Sys01_CompanyModel> updateModel = new UpdateDataModel<Sys01_CompanyModel>(); 
		updateModel.updateModel(sqlSession, request.getList(), mapperName, result);
	}

	public void delete(SqlSession sqlSession, ServiceRequest request, ServiceResult result) {
		UpdateDataModel<Sys01_CompanyModel> updateModel = new UpdateDataModel<Sys01_CompanyModel>(); 
		updateModel.deleteModel(sqlSession, request.getList(), mapperName, result);
	}
}
