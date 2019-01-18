package myApp.client.vi.cst;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gargoylesoftware.htmlunit.protocol.javascript.Handler;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;
import myApp.client.service.DBUtil;
import myApp.client.service.GridUpdate;
import myApp.client.service.InterfaceCallback;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.GridDataModel;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.hom.StartPage;

public class Cst01_Lookup_MemberJoin extends Window implements Editor<Cst01_UserModel>, InterfaceServiceCall {

	Cst01_UserModel userModel = new Cst01_UserModel();
	Cst01_Lookup_MemberJoinAccount grid = new Cst01_Lookup_MemberJoinAccount();

	interface EditDriver extends SimpleBeanEditorDriver<Cst01_UserModel, Cst01_Lookup_MemberJoin> {
	}
	EditDriver editDriver = GWT.create(EditDriver.class);

	private Long userId;
	private Long companyId = (long)2062721;	//홈페이지고객지원

	TextField email = new TextField();
	TextField emailChk = new TextField();
	TextField userName = new TextField();
	TextField phoneNo1 = new TextField();
	TextField phoneNo2 = new TextField();
	TextField phoneNo3 = new TextField();

	private InterfaceCallbackResult callback;
	private String actionName;

	public void open (Long userId, InterfaceCallbackResult callback) {
		
		this.callback = callback;
		this.userId = userId;

		editDriver.initialize(this);
		this.setHeaderVisible(false);
		this.setModal(true);
		this.setBorders(false);
		this.setResizable(false);
		this.setSize("650", "650");

		init();
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(this.getForm(), new VerticalLayoutData(-1, 385, new Margins(0, 0, 0, 0)));
		vlc.add(this.grid     , new VerticalLayoutData(1, 1, new Margins(0, 30, 0, 30)));
		this.add(vlc);

		TextButton accAddButton = new TextButton("계좌추가");
		accAddButton.setWidth(80);
		accAddButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				grid.insertRow(userModel.getUserId());
			}
		});
		this.getButtonBar().add(accAddButton);

		TextButton accDelButton = new TextButton("계좌삭제");
		accDelButton.setWidth(80);
		accDelButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				grid.deleteRow();
			}
		});
		this.getButtonBar().add(accDelButton);

		TextButton regiButton = new TextButton("등록"); 
		regiButton.setWidth(50);
		regiButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		}); 
		this.getButtonBar().add(regiButton);
		
		TextButton closeButton = new TextButton("닫기"); 
		closeButton.setWidth(50);
		closeButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		}); 
		this.getButtonBar().add(closeButton);
		this.getButtonBar().setHeight(75);
		this.setButtonAlign(BoxLayoutPack.CENTER);

		this.show();
	}

	private void init() {
		userModel.setEmailChk("false");
		if (this.userId == null) {
			DBUtil dbUtil = new DBUtil();
			dbUtil.setSeq(userModel, new InterfaceCallback() {
				@Override
				public void execute() {
					editDriver.edit(userModel);
					userId = userModel.getUserId();
				}
			});
		} else {
			//ID존재시 해당 User정보 가져오는 로직 추가필요
			editDriver.edit(userModel);
		}
	}

	private FormPanel getForm() {

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		vlc.add(StartPage.getTextContents("회원가입 및 계좌등록"),new VerticalLayoutData(-1, -1, new Margins(10, 0, 0, 10)));
		Image lineBar = new Image(ResourceIcon.INSTANCE.verticalTitle());
		vlc.add(lineBar,new VerticalLayoutData(0.45, -1, new Margins(0, 0, 0, 40)));

		//------------------------------------------------------------
		//	E-Mail
		//------------------------------------------------------------
		email.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				emailChk.setText("false");
			}
		});
		FieldLabel emailField = new FieldLabel(email, "ID (E-Mail) ");
		emailField.setWidth(300);
		emailField.setLabelWidth(100);

		TextButton emailCheckButton = new TextButton("중복확인"); 
		emailCheckButton.setWidth(80);
		emailCheckButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				checkEmail(); 
			}
		});

		HBoxLayoutContainer emailBoxLayout = new HBoxLayoutContainer();
		emailBoxLayout.add(emailField      , new BoxLayoutData(new Margins(0, 5, 0, 0)));
		emailBoxLayout.add(emailCheckButton, new BoxLayoutData(new Margins(2, 0, 0, 0)));
		emailBoxLayout.add(emailChk, new BoxLayoutData(new Margins(0, 0, 0, 10)));
		vlc.add(emailBoxLayout, new VerticalLayoutData(-1, -1, new Margins(25, 0, 0, 20)));

		Label emailDesc = new HTML("<font size='2'>※ <span style='color:red'><b>증권사 계좌 개설시 등록</b></span>된 E-Mail을 입력하여 주십시오.</font>");
		vlc.add(emailDesc, new VerticalLayoutData(-1, -1, new Margins(5, 0, 0, 125)));

		//------------------------------------------------------------
		//	이름
		//------------------------------------------------------------
		FieldLabel userNameField = new FieldLabel(userName, "이름 ");
		userNameField.setWidth(300);
		userNameField.setLabelWidth(100);
		vlc.add(userNameField, new VerticalLayoutData(-1, -1, new Margins(20, 0, 0, 20)));
		
		//------------------------------------------------------------
		//	휴대폰번호
		//------------------------------------------------------------
		phoneNo1.setText("010");
		FieldLabel phone1Field = new FieldLabel(phoneNo1, "휴대폰번호 ");
		phone1Field.setWidth(161);
		phone1Field.setLabelWidth(100);
		
		FieldLabel phone2Field = new FieldLabel(phoneNo2, "- ");
		phone2Field.setWidth(65);
		phone2Field.setLabelWidth(5);
		phone2Field.setLabelSeparator(" ");

		FieldLabel phone3Field = new FieldLabel(phoneNo3, "- ");
		phone3Field.setWidth(65);
		phone3Field.setLabelWidth(5);
		phone3Field.setLabelSeparator(" ");

		HBoxLayoutContainer phoneBoxLayout = new HBoxLayoutContainer();
		phoneBoxLayout.add(phone1Field, new BoxLayoutData(new Margins(0, 0, 0, 0)));
		phoneBoxLayout.add(phone2Field, new BoxLayoutData(new Margins(0, 0, 0, 4)));
		phoneBoxLayout.add(phone3Field, new BoxLayoutData(new Margins(0, 0, 0, 4)));
		vlc.add(phoneBoxLayout, new VerticalLayoutData(-1, -1, new Margins(10, 0, 0, 20)));

		Label phoneDesc = new HTML("<font size='2'>※ <span style='color:red'><b>증권사 계좌 개설시 등록</b></span>된 핸드폰번호를 입력하여 주십시오.</font>");
		vlc.add(phoneDesc, new VerticalLayoutData(-1, -1, new Margins(5, 0, 0, 125)));

		//------------------------------------------------------------
		//	OTP인증번호
		//------------------------------------------------------------
		LabelToolItem otpLabel = new LabelToolItem("OTP인증번호 : ");
		Label otpDesc1 = new HTML("<font size='2'>※ 개인정보보호를 위해 인증수단으로 <span style='color:red'><b>핸드폰 OTP인증번호</b></span>를 사용합니다.</font>");

		HBoxLayoutContainer otpBoxLayout1 = new HBoxLayoutContainer();
		otpBoxLayout1.add(otpLabel, new BoxLayoutData(new Margins(5,0,0,5)));
		otpBoxLayout1.add(otpDesc1, new BoxLayoutData(new Margins(0,0,0,1)));
		otpBoxLayout1.setHeight(25);
		
		Label otpDesc2 = new HTML("<font size='2'>※ OTP인증 App 설치 및 사용방법  </font>");
		Label otpAndroid = new HTML("<font size='2'> ▶ <a href=\"#\">Android</a>");
		otpAndroid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				Window.open("/BaroOTP_Android.html", "optwin", "width=800,height=800,menubars=0,toolbars=0,location=0,scrollbars=yes");
			}
		});
		Label otpIphone = new HTML("<font size='2'> ▶ <a href=\"#\">IOS(iPhone)</a>");
		otpIphone.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
//				Window.open("/BaroOTP_iPhone.html", "optwin", "width=800,height=800,menubars=0,toolbars=0,location=0,scrollbars=yes");
			}
		});

		HBoxLayoutContainer otpBoxLayout2 = new HBoxLayoutContainer();
		otpBoxLayout2.add(otpDesc2, new BoxLayoutData(new Margins(0, 0, 0, 99)));
		otpBoxLayout2.add(otpAndroid, new BoxLayoutData(new Margins(0, 0, 0, 5)));
		otpBoxLayout2.add(otpIphone, new BoxLayoutData(new Margins(0, 0, 0, 5)));

		vlc.add(otpBoxLayout1, new VerticalLayoutData(600, -1, new Margins(20, 0, 0, 25)));
		vlc.add(otpBoxLayout2, new VerticalLayoutData(600, -1, new Margins(0, 0, 0, 25)));

		//		
//		Label otpandroid = new HTML("<font size='2'> ▶ <a href=\"#\">Android</a>");
//		otpandroid.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				 Window w = new Window();
//				 w.setHeading("BaroOTP 설명서 (Android Ver)");
//				 w.setModal(false);
//				 w.setPixelSize(800, 700);
//				 w.setMaximizable(true);
////				 w.setToolTip("The GXT product page...");
//				 w.setWidget(new Frame("/BaroOTP_Android.html"));
//				 w.show();
//				 
////				Window.open("/KFIA/BaroOTP_Android.html", "optwin", "width=800,height=800,menubars=0,toolbars=0,location=0,scrollbars=yes");
//			}
//		});
//		Label otpIphone = new HTML("<font size='2'> ▶ <a href=\"#\">IOS(iPhone)</a>");
//		otpIphone.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				Window w = new Window();
//				w.setHeading("BaroOTP 설명서 (iphone Ver)");
//				w.setPixelSize(800, 700);
//				w.setWidget(new Frame("/BaroOTP_iPhone.html"));
//				w.show();
//			}
//		});
//		
		FormPanel form = new FormPanel();
		form.setWidget(vlc);
		
		return form;
	}

	protected void checkEmail() {

		actionName = "checkEmail";
		userModel  = editDriver.flush();

		if ((userModel.getEmail() == null)|| ("".equals(userModel.getEmail().replaceAll(" ", "")))) {
			new SimpleMessage("입력확인","ID(E-Mail)을(를) 입력하여 주십시오.");
			return;
		}

		ServiceRequest request = new ServiceRequest("cst.Cst01_User.checkEmail");
		request.addParam("companyId", this.companyId);
		request.addParam("email"    , userModel.getEmail().replaceAll(" ", ""));
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
		
	}

	protected void update() {

		this.actionName = "update";
		this.userModel  = editDriver.flush();

		if (checkPreUpdate()) {
			if (grid.checkPreUpdate()) {
				ServiceRequest request = new ServiceRequest("cst.Cst01_User.update");

				//User정보 담기
				request.putModelParam("userModel",  this.userModel);

				//계좌정보 담기
				List<GridDataModel> list = new ArrayList<GridDataModel>();
				for(Cst02_AccountModel accModel : grid.getGrid().getStore().getAll()) {
					list.add((GridDataModel)accModel); 
				}
				request.setList(list);
				
				//Service Call
				ServiceCall service = new ServiceCall();
				service.execute(request, this);
			}
		}
	}

	private boolean checkPreUpdate() {
		
		userModel  = editDriver.flush();
		
		if((userModel.getEmail() == null) || ("".equals(userModel.getEmail().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "ID(E-Mail)을 입력하여 주십시오.");
			return false;
		}
		if ((emailChk.getText() == null) || (!"true".equals(emailChk.getText()))) {
			new SimpleMessage("확인", "ID(E-Mail) 중복확인을 하여 주십시오. " + userModel.getEmailChk());
			return false;
		}
		if((userModel.getUserName() == null) || ("".equals(userModel.getUserName().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "이름을 입력하여 주십시오.");
			return false;
		}
		if((this.phoneNo1.getText() == null)||("".equals(this.phoneNo1.getText().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "휴대폰번호를 입력하여 주십시오.");
			return false;
		}
		if((this.phoneNo2.getText() == null)||("".equals(this.phoneNo2.getText().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "휴대폰번호를 입력하여 주십시오.");
			return false;
		}
		if((this.phoneNo3.getText() == null)||("".equals(this.phoneNo3.getText().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "휴대폰번호를 입력하여 주십시오.");
			return false;
		}
		//휴대번호 SET
		String phoneNo1 = this.phoneNo1.getText().replaceAll(" ", "");
		String phoneNo2 = this.phoneNo2.getText().replaceAll(" ", "");
		String phoneNo3 = this.phoneNo3.getText().replaceAll(" ", "");
		userModel.setPhoneNo(phoneNo1 + phoneNo2 + phoneNo3);
		
		//기타정보 SET
		userModel.setCompanyId(this.companyId);
		userModel.setRefreshTime("60");
		userModel.setStartDt(new Date());
		userModel.setMrdRole("9");

		return true;
	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if ("checkEmail".equals(this.actionName)) {
			if (result.getStatus() > -1) {
				new SimpleMessage("확인", result.getMessage());
				emailChk.setText("true");
			} else {
				new SimpleMessage("오류", result.getMessage());
				emailChk.setText("false");
			}
		} else if ("update".equals(this.actionName)) {
			if (result.getStatus() > -1) {
				new SimpleMessage("확인", "등록이 완료되었습니다.");
				callback.execute(userModel.getEmail());
				hide();
			} else {
				new SimpleMessage("오류", result.getMessage());
			}
		} 
	}
}
