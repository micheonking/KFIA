package myApp.client.vi.cst;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
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
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.grid.GridBuilder;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.service.GridDeleteData;
import myApp.client.service.GridInsertRow;
import myApp.client.service.GridRetrieveData;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.cst.model.Cst02_AccountModelProperties;

public class Cst01_Lookup_MemberJoinAccount_bak extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceServiceCall {

    Cst02_AccountModelProperties properties = GWT.create(Cst02_AccountModelProperties.class);
    Cst02_AccountModel accModel;

    private Grid<Cst02_AccountModel> accGrid = this.buildGrid();
	private String actionName;

    public Grid<Cst02_AccountModel> getGrid() {
    	return accGrid;
    }
    
	public Cst01_Lookup_MemberJoinAccount_bak() {

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

		final CommonComboBoxField mgComboBox = new CommonComboBoxField("cst.Cst02_Account.selectMgCombo");
		mgComboBox.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				accGrid.getSelectionModel().getSelectedItem().setMgCode(mgComboBox.getCode());
//				accGrid.getView().refresh(true);

				accGrid.getStore().commitChanges();
				String accNo = accGrid.getSelectionModel().getSelectedItem().getAccountNo();
				if (accNo != null) {
					getAccInfo(mgComboBox.getCode(), accNo);
				}
			}
		});
		gridBuilder.addText(properties.mgName()	, 130, "증권사"	, mgComboBox);
		
		TextField accNo = new TextField();
		accNo.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				if((event.getValue() == null)||("".replaceAll(" ", "").equals(event.getValue()))) {
					accGrid.getSelectionModel().getSelectedItem().setAccountName(null);
					accGrid.getSelectionModel().getSelectedItem().setFundCode(null);
					accGrid.getView().refresh(true);
				} else {
					String mgCode = accGrid.getSelectionModel().getSelectedItem().getMgCode();
					if (mgCode != null) {
						getAccInfo(mgCode, event.getValue());
					}
				}
			}
		});
		gridBuilder.addText(properties.accountNo()	, 170, "계좌번호"	, accNo);
		
//		ActionCell<String> accCheck = new ActionCell<String>("계좌확인", new ActionCell.Delegate<String>() {
//			@Override
//			public void execute(String object) {
//				getAccInfo();
//			}
//		});
//		gridBuilder.addCell(properties.actionCell()	,  75, ""	, accCheck);
		gridBuilder.addText(properties.accountName(), 150, "계좌명");
		gridBuilder.addLong(properties.userId(), 150, "userid");

		return gridBuilder.getGrid();
	}

	@Override
	public void retrieve(){
	}

	public void retrieve(long userId){
		GridRetrieveData<Cst02_AccountModel> service = new GridRetrieveData<Cst02_AccountModel>(accGrid.getStore());
		service.addParam("userId", userId);
		service.retrieve("cst.Cst02_Account.selectByUserId");
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

	private void getAccInfo(String mgCode, String accNo) {
		this.actionName = "getAccInfo";

//		accGrid.getStore().commitChanges();
//		Cst02_AccountModel accModel = accGrid.getSelectionModel().getSelectedItem();

//		if(accModel.getMgCode() == null) {
//			new SimpleMessage("확인", "증권사를 선택하여 주십시오.");
//			return;
//		}

//		if((accModel.getAccountNo() == null)||("".equals(accModel.getAccountNo().replaceAll(" " , "")))) {
//			new SimpleMessage("확인", "계좌번호를 입력하여 주십시오.");
//			return;
//		}

		ServiceRequest request = new ServiceRequest("cst.Cst02_Account.getAccInfo");
//		request.addParam("accModel", accModel);
		request.addParam("mgCode",  mgCode);
		request.addParam("accNo",  accNo);

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
				new SimpleMessage("확인", "계좌확인이 되지 않았습니다.<br>증권사 및 계좌번호를 확인하여 주십시오.", 400);
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
				new SimpleMessage("오류", result.getMessage(), 400);
			}
			accGrid.getSelectionModel().getSelectedItem().setAccountName(accName);
			accGrid.getSelectionModel().getSelectedItem().setFundCode(fundCode);
			accGrid.getView().refresh(true);
		}
	}

}