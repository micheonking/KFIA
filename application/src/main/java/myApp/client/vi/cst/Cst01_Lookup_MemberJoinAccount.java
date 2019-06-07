package myApp.client.vi.cst;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.LongField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.resource.ResourceIcon;
import myApp.client.service.DBUtil;
import myApp.client.service.InterfaceCallback;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.hom.StartPage;

public class Cst01_Lookup_MemberJoinAccount extends Window implements Editor<Cst02_AccountModel>, InterfaceServiceCall {

	interface EditDriver extends SimpleBeanEditorDriver<Cst02_AccountModel, Cst01_Lookup_MemberJoinAccount> {
	}
	EditDriver editDriver = GWT.create(EditDriver.class);

	Cst01_UserModel userModel = new Cst01_UserModel();
	Cst02_AccountModel accModel = new Cst02_AccountModel();
	private String actionName;
	private String procType;
	private InterfaceCallbackResult callback;

	CommonComboBoxField mgName;
	TextField mgCode = new TextField();
	TextField accountNo = new TextField();
	TextField accountName = new TextField();
	TextField fundCode = new TextField();
	LongField userId = new LongField();
	
	public void open (Cst01_UserModel userModel, Cst02_AccountModel accModel, String procType, InterfaceCallbackResult callback) {
		
		this.callback = callback;
		this.userModel = userModel;
		this.accModel = accModel;
		this.procType = procType;
		
		editDriver.initialize(this);
		this.setHeaderVisible(false);
		this.setModal(true);
		this.setBorders(false);
		this.setResize(false);
		this.setSize("600", "250");
		
		mgName = new CommonComboBoxField("cst.Cst02_Account.selectMgCombo",null,"선택하여 주십시오.", new InterfaceCallback() {
			@Override
			public void execute() {
				init();
			}
		});

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(this.getForm(), new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
		this.add(vlc);
		
		TextButton saveButton = new TextButton("저장");
		saveButton.setWidth(50);
		saveButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				save();
			}
		});
		
		TextButton closeButton = new TextButton("닫기");
		closeButton.setWidth(50);
		closeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				hide();
			}
		});
		
		this.getButtonBar().add(saveButton);
		this.getButtonBar().add(closeButton);
		this.getButtonBar().setHeight(75);
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.show();
	}

	private void init() {
		if (this.accModel == null) {
			this.accModel = new Cst02_AccountModel();
			DBUtil dbUtil = new DBUtil();
			dbUtil.setSeq(accModel, new InterfaceCallback() {
				@Override
				public void execute() {
					accModel.setUserId(userModel.getUserId());
					editDriver.edit(accModel);
				}
			});
		} else {
			editDriver.edit(accModel);
			mgName.setText(accModel.getMgName());
		}
	}

	private IsWidget getForm() {

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		vlc.add(StartPage.getTextContents("계좌등록 및 정보수정"), new VerticalLayoutData(-1, -1, new Margins(0, 0, 0, 0)));
		Image lineBar = new Image(ResourceIcon.INSTANCE.verticalTitle());
		vlc.add(lineBar, new VerticalLayoutData(0.95, -1, new Margins(0, 0, 0, 25)));

		//------------------------------------------------------------
		//	증권사
		//------------------------------------------------------------
		FieldLabel mgCodeField = new FieldLabel(mgName, "증권사");
		mgCodeField.setWidth(250);
		mgCodeField.setLabelWidth(80);
		mgName.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				mgCode.setValue(mgName.getCode());
			}
		});
		vlc.add(mgCodeField, new VerticalLayoutData(-1, -1, new Margins(5,0,0,20)));
//		vlc.add(mgCode);
		//------------------------------------------------------------
		//	계좌번호 / 계좌명
		//------------------------------------------------------------
		FieldLabel accountCdField = new FieldLabel(accountNo, "계좌번호");
		accountCdField.setWidth(250);
		accountCdField.setLabelWidth(80);
		accountNo.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				accountName.setText(null);
				fundCode.setText(null);
			}
		});

		TextButton accChkButton = new TextButton("계좌확인");
		accChkButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getAccInfo();
			}
		});

		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.add(accountCdField, new BoxLayoutData(new Margins(0, 2, 0, 0)));
		hblc.add(accChkButton, new BoxLayoutData(new Margins(2, 2, 0, 0)));

		accountName.setEnabled(false);
		accountName.setWidth(200);
		hblc.add(accountName, new BoxLayoutData(new Margins(2, 0, 0, 0)));
		vlc.add(hblc, new VerticalLayoutData(-1, -1, new Margins(5,0,0,20)));
		
		HBoxLayoutContainer tmpblc = new HBoxLayoutContainer();
		tmpblc.add(new FieldLabel(fundCode, "펀드코드"));
		tmpblc.add(new FieldLabel(userId, "USERID"));
//		vlc.add(tmpblc, new VerticalLayoutData(-1, -1, new Margins(5,0,0,20)));

		FormPanel form = new FormPanel();
		form.setWidget(vlc);
		return form;
	}

	protected void save() {
		this.actionName = "save";
		this.accModel = editDriver.flush();
		if(checkPreUpdate()) {
			ServiceRequest request = new ServiceRequest("cst.Cst02_Account.updateOne");
			request.putModelParam("accModel", this.accModel);
			ServiceCall service = new ServiceCall();
			service.execute(request, this);
		}
	}
	
//	private Grid<Cst02_AccountModel> buildGrid() {
//		
//		GridBuilder<Cst02_AccountModel> gridBuilder = new GridBuilder<Cst02_AccountModel>(properties.keyId());
//		gridBuilder.setChecked(SelectionMode.MULTI);
//
//		final CommonComboBoxField mgComboBox = new CommonComboBoxField("cst.Cst02_Account.selectMgCombo");
//		mgComboBox.addCollapseHandler(new CollapseHandler() {
//			@Override
//			public void onCollapse(CollapseEvent event) {
//				accGrid.getSelectionModel().getSelectedItem().setMgCode(mgComboBox.getCode());
////				accGrid.getView().refresh(true);
//
//				accGrid.getStore().commitChanges();
//				String accNo = accGrid.getSelectionModel().getSelectedItem().getAccountNo();
//				if (accNo != null) {
//					getAccInfo(mgComboBox.getCode(), accNo);
//				}
//			}
//		});
//		gridBuilder.addText(properties.mgName()	, 130, "증권사"	, mgComboBox);
//		
//		TextField accNo = new TextField();
//		accNo.addValueChangeHandler(new ValueChangeHandler<String>() {
//			@Override
//			public void onValueChange(ValueChangeEvent<String> event) {
//				if((event.getValue() == null)||("".replaceAll(" ", "").equals(event.getValue()))) {
//					accGrid.getSelectionModel().getSelectedItem().setAccountName(null);
//					accGrid.getSelectionModel().getSelectedItem().setFundCode(null);
//					accGrid.getView().refresh(true);
//				} else {
//					String mgCode = accGrid.getSelectionModel().getSelectedItem().getMgCode();
//					if (mgCode != null) {
//						getAccInfo(mgCode, event.getValue());
//					}
//				}
//			}
//		});
//		gridBuilder.addText(properties.accountNo()	, 170, "계좌번호"	, accNo);
//		
////		ActionCell<String> accCheck = new ActionCell<String>("계좌확인", new ActionCell.Delegate<String>() {
////			@Override
////			public void execute(String object) {
////				getAccInfo();
////			}
////		});
////		gridBuilder.addCell(properties.actionCell()	,  75, ""	, accCheck);
//		gridBuilder.addText(properties.accountName(), 150, "계좌명");
//		gridBuilder.addLong(properties.userId(), 150, "userid");
//
//		return gridBuilder.getGrid();
//	}

	private void getAccInfo() {
		this.actionName = "getAccInfo";
		this.accModel  = editDriver.flush();

		if(accModel.getMgCode() == null ) {
			new SimpleMessage("확인", "증권사를 선택하여 주십시오.", 350);
			return;
		}
		if((accModel.getAccountNo() == null)||("".equals(accModel.getAccountNo().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "계좌번호를 입력하여 주십시오.", 350);
			return;
		}
		ServiceRequest request = new ServiceRequest("cst.Cst02_Account.getAccInfo");
		request.addParam("mgCode", accModel.getMgCode());
		request.addParam("accNo" , accModel.getAccountNo());
		request.addParam("eMail" , userModel.getEmail());
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
	}

	public boolean checkPreUpdate() {
		this.accModel = editDriver.flush();
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
			this.accountName.setText(accName);
			this.accountName.setValue(accName);
			this.fundCode.setText(fundCode);
			this.fundCode.setValue(fundCode);
		}
		else if ("save".equals(this.actionName)) {
			if("NEW".equals(procType)) {
				new SimpleMessage("확인", "등록이 완료되었습니다.");
				callback.execute(userModel.getEmail());
			} else {
				callback.execute(null);
			}
			hide();
		}
	}

}