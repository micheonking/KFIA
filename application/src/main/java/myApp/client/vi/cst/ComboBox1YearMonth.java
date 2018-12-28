package myApp.client.vi.cst;

import java.util.HashMap;
import java.util.Map;

import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.widget.core.client.form.StringComboBox;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.vi.sys.model.Sys13_YearMonthModel;

public class ComboBox1YearMonth extends StringComboBox implements InterfaceServiceCall {

	private Map<String, Sys13_YearMonthModel> list = new HashMap<String, Sys13_YearMonthModel>();
	
	public Map<String, Sys13_YearMonthModel> getCodeList(){
		return this.list; 
	}
	
	public ComboBox1YearMonth(String evalTypeCode){
		this.setTriggerAction(TriggerAction.ALL);
		this.retrieve(evalTypeCode);
  	}  	

	public Sys13_YearMonthModel getSeletedStartModel(String startName){
		Sys13_YearMonthModel yearMonthModel = list.get(startName);
  		if(yearMonthModel != null){
  			return yearMonthModel;   
  		}
  		else {
  			return null; 
  		}
  	}
	
	public String getCurrentFundCode(){

		Info.display("this text", this.getText());

		Sys13_YearMonthModel currentModel = list.get(this.getText());
		
  		if(currentModel != null){
//  			Info.display("ZZZZZZ", "KKK");
  			return currentModel.getYearMonth();   
  		}
  		else {
  			return "null"; 
  		}
  	}
	
	public void retrieve(String evalTypeCode) {
		ServiceRequest request = new ServiceRequest("sys.Sys13_YearMonth.selectById");
//		request.putLongParam("userId", LoginUser.getUserId());
//		request.putStringParam("evalTypeCode", evalTypeCode); // 콤보에서는 모두 조회한다. 평가 기간이 없으면 조회되지 않는다. 
//		request.putStringParam("baseYear", "%");

		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {
		boolean isFirst = true;  
		this.getStore().clear();		
		
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}
		for (GridDataModel data : result.getResult()) {
			Sys13_YearMonthModel yearMonthModel = (Sys13_YearMonthModel)data ;
			String comboText = yearMonthModel.getYearMonthKor();
			this.add(comboText);
			list.put(comboText, yearMonthModel);
			
			// 초기값 설정. 
			if(isFirst) {
				this.setText(comboText);
				isFirst = false; 
			}
		}
	}
}