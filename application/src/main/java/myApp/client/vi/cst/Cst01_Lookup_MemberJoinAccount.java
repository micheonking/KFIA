package myApp.client.vi.cst;

import java.util.List;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.grid.GridBuilder;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.service.GridDeleteData;
import myApp.client.service.GridInsertRow;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.cst.model.Cst02_AccountModelProperties;

public class Cst01_Lookup_MemberJoinAccount extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceServiceCall {

    Cst02_AccountModelProperties properties = GWT.create(Cst02_AccountModelProperties.class);
    Cst02_AccountModel accModel;

    private Grid<Cst02_AccountModel> accGrid = this.buildGrid();
	private String actionName;

    public Grid<Cst02_AccountModel> getGrid() {
    	return accGrid;
    }
    
	public Cst01_Lookup_MemberJoinAccount() {

		this.setBorders(false);

		LabelToolItem accLabel = new LabelToolItem("계좌정보 : ");
		Label accDesc = new HTML("<font size='2'>※ 계좌번호는 <span style='color:red'><b>'-' 없이 번호만</b></span> 입력하여 주십시오.</font>");

		HBoxLayoutContainer hlc = new HBoxLayoutContainer();
		hlc.add(accLabel, new BoxLayoutData(new Margins(5,0,0,18)));
		hlc.add(accDesc, new BoxLayoutData(new Margins(0,0,0,3)));
		this.add(hlc, new VerticalLayoutData(550,25, new Margins(0, 0, 0, 0)));
		this.add(this.accGrid, new VerticalLayoutData(1,1, new Margins(8, 5, 0, 5)));
	}

	private Grid<Cst02_AccountModel> buildGrid() {
		
		GridBuilder<Cst02_AccountModel> gridBuilder = new GridBuilder<Cst02_AccountModel>(properties.keyId());
		gridBuilder.setChecked(SelectionMode.MULTI);

//		final MgComboBoxField mgComboBox = new MgComboBoxField();
		final CommonComboBoxField mgComboBox = new CommonComboBoxField("cst.Cst02_Account.selectMgCombo");
		mgComboBox.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				accGrid.getSelectionModel().getSelectedItem().setMgCode(mgComboBox.getCode());
				accGrid.getView().refresh(true);
			}
		});
		gridBuilder.addText(properties.mgName()		, 120, "증권사"	, mgComboBox);
		gridBuilder.addText(properties.accountNo()	, 150, "계좌번호"	, new TextField());
		
		ActionCell<String> accCheck = new ActionCell<String>("계좌확인", new ActionCell.Delegate<String>() {
			@Override
			public void execute(String object) {
				getAccInfo();
			}
		});
		gridBuilder.addCell(properties.actionCell()	,  75, ""	, accCheck);
		gridBuilder.addText(properties.accountName(), 130, "계좌명");

		return gridBuilder.getGrid();
	}

	@Override
	public void retrieve(){
	}

	@Override
	public void update(){
	}

	@Override
	public void insertRow(){
	}

	public void insertRow(Long userId){
		
		Cst02_AccountModel insertModel = new Cst02_AccountModel();
		insertModel.setUserId(userId);
		
		GridInsertRow<Cst02_AccountModel> service = new GridInsertRow<Cst02_AccountModel>();
		service.insertRow(accGrid, insertModel);
	}

	@Override
	public void deleteRow(){
		
		List<Cst02_AccountModel> delList = accGrid.getSelectionModel().getSelectedItems();

		GridDeleteData<Cst02_AccountModel> service = new GridDeleteData<Cst02_AccountModel>();
		service.delete(accGrid.getStore(), delList, "cst.Cst02_Account.delete");
	}

	private void getAccInfo() {

		this.actionName = "getAccInfo";

		accGrid.getStore().commitChanges();
		Cst02_AccountModel accModel = accGrid.getSelectionModel().getSelectedItem();

		if(accModel.getMgCode() == null) {
			new SimpleMessage("확인", "증권사를 선택하여 주십시오.");
			return;
		}

		if((accModel.getAccountNo() == null)||("".equals(accModel.getAccountNo().replaceAll(" " , "")))) {
			new SimpleMessage("확인", "계좌번호를 입력하여 주십시오.");
			return;
		}

		ServiceRequest request = new ServiceRequest("cst.Cst02_Account.getAccInfo");
		request.addParam("accModel", accModel);

		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}

	public boolean checkPreUpdate() {
		accGrid.getStore().commitChanges();
		if (accGrid.getStore().size() == 0) {
			new SimpleMessage("확인", "계좌정보를 입력하여 주십시오.");
			return false;
		}
		for(Cst02_AccountModel accModel : accGrid.getStore().getAll()) {
			if(accModel.getMgCode() == null) {
				new SimpleMessage("확인", "증권사를 선택하여 주십시오.");
				return false;
			}
			if((accModel.getAccountNo() == null)||("".equals(accModel.getAccountNo().replaceAll(" " , "")))) {
				new SimpleMessage("확인", "계좌번호를 입력하여 주십시오.");
				return false;
			}
			if(accModel.getFundCode() == null) {
				new SimpleMessage("확인", "계좌확인을 하여 주십시오.");
				return false;
			}
		}
		return true;
	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if ("getAccInfo".equals(this.actionName)) {
			String accName  = null;
			String fundCode = null;
			if (result.getStatus() > -1) {
				Cst02_AccountModel accModel = (Cst02_AccountModel)result.getResult(0);
				accName  = accModel.getAccountName();
				fundCode = accModel.getFundCode();
			} else {
				new SimpleMessage("오류", result.getMessage());
			}
			accGrid.getSelectionModel().getSelectedItem().setAccountName(accName);
			accGrid.getSelectionModel().getSelectedItem().setFundCode(fundCode);
			accGrid.getView().refresh(true);
		}
	}

}