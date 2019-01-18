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
import myApp.client.vi.cst.model.Cst03_IcamAccountsModel;

public class MgComboBoxField extends StringComboBox implements InterfaceServiceCall {

	private Map<String, Cst03_IcamAccountsModel> mgCodeList = new HashMap<String, Cst03_IcamAccountsModel>();
	
	public MgComboBoxField(){
		ServiceRequest request = new ServiceRequest("cst.Cst03_Icam_Accounts.selectByMgCombo");
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
		this.setTriggerAction(TriggerAction.ALL);
  	}  	
	
	public String getCode(){
		
		mgCodeList.get("");
		Cst03_IcamAccountsModel code = mgCodeList.get(this.getCurrentValue());
  		if(code != null){
  			return code.getMgCode(); 
  		}
  		else {
  			return null; 
  		}
  	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			Info.display("error", result.getMessage());
			return ; 
		}
		for (GridDataModel model: result.getResult()) {
			Cst03_IcamAccountsModel mgCode = (Cst03_IcamAccountsModel)model ;
			mgCodeList.put(mgCode.getMcCodeName(), mgCode);
			this.add(mgCode.getMcCodeName());
		}
	}
}


