package myApp.client.vi;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.service.GridDeleteData;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.emp.model.Emp00_InfoModel;
import myApp.client.vi.opr.model.Opr01_CreateModel;
import myApp.client.vi.MainFrame;
import myApp.client.vi.cst.Cst01_Lookup_MemberJoin;
import myApp.client.vi.cst.Cst01_Lookup_MemberJoinAccount;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.sys.Sys00_Admin;
import myApp.client.vi.sys.model.Sys02_UserModel;

public class LoginPage implements InterfaceServiceCall {
	
//	private final Dialog loginDialog = new Dialog();
	private TextField firstName = new TextField();
	private PasswordField password= new PasswordField();
	private PasswordField otpNumber = new PasswordField();
    private CenterLayoutContainer container = new CenterLayoutContainer();
    Viewport viewport = new Viewport();
    
    Cst01_UserModel userModel = new Cst01_UserModel();
     
	public void open() {
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();

//		HTML image = new HTML("<center><div><img src='img/KFIALogin.png' width='423' height='103'></center></div>"); 
		HTML image = new HTML("<div><img src='img/KFIALogin.png' width='333'></div>");
		vlc.add(image, new VerticalLayoutData(1, -1, new Margins(0, 0, 20, 0)));

		FieldLabel loginFieldLabel = new FieldLabel(firstName, "ID (E-Mail) ");
		loginFieldLabel.setLabelWidth(100);
		loginFieldLabel.setWidth(300);
//		firstName.setText("chorus-21@hanmail.net");
		firstName.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == 13) {
					login(); 
				}
			}
		}); 
		
		FieldLabel otpNumberFieldLabel = new FieldLabel(otpNumber, "OTP 인증번호 ");
		otpNumberFieldLabel.setLabelWidth(100);
		otpNumberFieldLabel.setWidth(300);
//		otpNumber.setText("1111");
		otpNumber.addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == 13) {
					login();
				}
			}
		});

		TextButton okButton = new TextButton("확인");
		okButton.setWidth(65);
		okButton.setHeight(75);
		okButton.setBorders(false);
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				login();
			}
		});

		TextButton cancelButton = new TextButton("취소");
		cancelButton.setWidth(65);
		cancelButton.setHeight(75);
		cancelButton.setBorders(false);
		cancelButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Window.Location.reload();
			}
		});

		TextButton adminButton = new TextButton("관리자"); 
//		adminButton.setWidth(65);
//		adminButton.setHeight(80);
//		adminButton.setBorders(true);
		adminButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				firstName.setText("admin");
				otpNumber.setText("1111");
				login(); 
			}
		});

		TextButton imsiButton = new TextButton("miCheon@k-fs.co.kr"); 
		imsiButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				firstName.setText("miCheon@k-fs.co.kr");
				otpNumber.setText("1111");
				login(); 
			}
		});

		VBoxLayoutContainer vBoxLayout = new VBoxLayoutContainer();
		vBoxLayout.add(loginFieldLabel    , new BoxLayoutData(new Margins(1, 0, 0, 0)));
		vBoxLayout.add(otpNumberFieldLabel, new BoxLayoutData(new Margins(1, 0, 0, 0)));
		
		HBoxLayoutContainer hBoxLayout = new HBoxLayoutContainer();
		hBoxLayout.add(vBoxLayout  , new BoxLayoutData(new Margins(0, 0, 0, 0)));
		hBoxLayout.add(okButton    , new BoxLayoutData(new Margins(0, 0, 0, 2)));
//		hBoxLayout.add(cancelButton, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		vlc.add(hBoxLayout, new VerticalLayoutData(700, -1, new Margins(0, 0, 0, 0)));
//		vlc.add(adminButton, new VerticalLayoutData(1, -1, new Margins(10, 0, 0, 0)));
//		vlc.add(imsiButton, new VerticalLayoutData(1, -1, new Margins(0, 0, 0, 0)));

		Label loginDesc = new HTML("<font size='2'>※ 회원가입 후 로그인이 가능합니다. </font>");
		Label memberJoin = new HTML("<font size='2'> ▶  <a href=\"#\">회원가입</a></font>");
		memberJoin.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cst01_Lookup_MemberJoin memberJoinLookup = new Cst01_Lookup_MemberJoin();
				memberJoinLookup.open(null, new InterfaceCallbackResult() {
					@Override
					public void execute(Object result) {
						firstName.setText(result.toString());
					}
				});
			}
		});
		Label eMailSearch = new HTML("<font size='2'><a href=\"#\">ID(E-Mail)찾기</a></font>");
		eMailSearch.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Info.display("로그인ID(E-Mail) 찾기", "GoGo!!!");
			}
		});
//		Label gb = new Label("/");

		HBoxLayoutContainer hBoxLayout1 = new HBoxLayoutContainer();
		hBoxLayout1.add(loginDesc, new BoxLayoutData(new Margins(0, 10, 0, 0)));
		hBoxLayout1.add(memberJoin, new BoxLayoutData(new Margins(0, 0, 0, 0)));
//		hBoxLayout1.add(gb, new BoxLayoutData(new Margins(0, 7, 0, 7)));
//		hBoxLayout1.add(eMailSearch, new BoxLayoutData(new Margins(0, 0, 0, 0)));
		hBoxLayout1.setHeight(23);
		vlc.add(hBoxLayout1, new VerticalLayoutData(1, -1, new Margins(15, 0, 0, 0)));

		Label otpDesc = new HTML("<font size='2'>※ 핸드폰 OTP인증 앱설치 및 사용방법을 확인하세요.<br> </font>");
		Label otpandroid = new HTML("<font size='2'> ▶ <a href=\"#\">Android</a>");
		otpandroid.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.open("/KFIA/BaroOTP_Android.html", "optwin", "width=800,height=800,menubars=0,toolbars=0,location=0,scrollbars=yes");
			}
		});
		Label otpIphone = new HTML("<font size='2'> ▶ <a href=\"#\">IOS(iPhone)</a>");
		otpIphone.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Window.open("/KFIA/BaroOTP_iPhone.html", "optwin", "width=800,height=800,menubars=0,toolbars=0,location=0,scrollbars=yes");
			}
		});

		HBoxLayoutContainer hBoxLayout3 = new HBoxLayoutContainer();
		hBoxLayout3.add(otpDesc, new BoxLayoutData(new Margins(0, 10, 0, 0)));
		hBoxLayout3.add(otpandroid, new BoxLayoutData(new Margins(0, 5, 0, 0)));
		hBoxLayout3.add(otpIphone, new BoxLayoutData(new Margins(0, 5, 0, 0)));
		hBoxLayout3.setHeight(23);
		vlc.add(hBoxLayout3, new VerticalLayoutData(1, -1, new Margins(0, 0, 0, 0)));

//		Label browserDesc = new HTML("<font size='2'>※ 본 시스템은 크롬(Chrome)브라우즈에 최적화 되어 있습니다.<br>&nbsp;&nbsp;&nbsp;크롬(Chrome)을 내려 받아 사용하시기 바랍니다.<br></font>");
		Label browserDesc = new HTML("<font size='2'>※ 본 시스템은 크롬(Chrome)브라우즈에 최적화되어 있습니다.<br></font>");
		vlc.add(browserDesc, new VerticalLayoutData(1, -1, new Margins(0, 0, 0, 0)));

//		Label chromeDesc = new HTML("<font size='2'>&nbsp;&nbsp;&nbsp;▶<a href=\"#\"> CHROME 다운로드 (https://www.google.com/intl/ko_ALL/chrome/)</a></font>");
//		chromeDesc.addClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				Window.open("https://www.google.com/intl/ko_ALL/chrome/", "chromewin", "width=1200,height=700,menubars=0,toolbars=0,location=0,scrollbars=yes");
//			}
//		});
//		vlc.add(chromeDesc, new VerticalLayoutData(440, -1, new Margins(0, 0, 0, 15)));

		FormPanel formPanel = new FormPanel();
		formPanel.setWidth(500);
		formPanel.setHeight(420);

		formPanel.setWidget(vlc);
		formPanel.setBorders(false);

		container.add(formPanel);
		viewport.add(container);

		RootPanel.get().add(viewport);

		firstName.focus();
	}

	public void login(){
		
		if ((firstName.getText() == null) || ("".equals(firstName.getText().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "ID(E-Mail)을 입력하여 주십시오.");
			return;
		}

		if ((otpNumber.getText() == null) || ("".equals(otpNumber.getText().replaceAll(" ", "")))) {
			new SimpleMessage("확인", "OTP인증번호를 입력하여 주십시오.");
			return;
		}

		// 로그인 정보를 찾는다.
		if("admin".equals(firstName.getText())) {
			LoginUser.setIsAdmin(true);
			ServiceRequest request = new ServiceRequest("sys.Sys02_User.getLoginAdminInfo");
			request.putLongParam  ("companyId", (long)2000940);	//2000940:한국펀드서비스
			request.putStringParam("otpNumber", otpNumber.getText());
			ServiceCall service = new ServiceCall(); 
			service.execute(request, this);
		} else {
			ServiceRequest request = new ServiceRequest("sys.Sys02_User.getLoginInfo");
			request.putLongParam  ("companyId", (long)2062721);	//2062721:한채투 홈페이지고객지원 사이트
			request.putStringParam("loginId", firstName.getText());
			request.putStringParam("passwd", password.getText());
			request.putStringParam("otpNumber", otpNumber.getText());
			ServiceCall service = new ServiceCall(); 
			service.execute(request, this);
		}
	}
	
	@Override
	public void getServiceResult(ServiceResult result) {

		if (LoginUser.getIsAdmin()) {
			if(result.getStatus() == 10 ) {
				this.viewport.remove(container);
				viewport.add(new Sys00_Admin(), new MarginData(3, 3, 6, 3));
				RootPanel.get().add(viewport);
			} else {
				new SimpleMessage("로그인 정보 확인", result.getMessage());
			}
			return;
		} else {
			if(result.getStatus() == 10 ) { // 일반사용자 접속
				Emp00_InfoModel empModel = (Emp00_InfoModel) result.getResult(0);
				LoginUser.setEmpModel(empModel); 
			}
			else if(result.getStatus() == 20) { // 회사관리자 접속
				Sys02_UserModel userModel = (Sys02_UserModel) result.getResult(0); 
				LoginUser.setUserModel(userModel); 
			}
			else if(result.getStatus() == 30) { // 고객 접속
				Cst01_UserModel cstUserModel = (Cst01_UserModel) result.getResult(0); 
				LoginUser.setCstUserModel(cstUserModel); 
			}
			else if(result.getStatus() == 40) { // 계좌미등록 고객임. 계좌등록 POPUP OPEN!!!
				Cst01_UserModel cstUserModel = (Cst01_UserModel) result.getResult(0); 
				LoginUser.setCstUserModel(cstUserModel);
				final ConfirmMessageBox msgBox = new ConfirmMessageBox("등록확인", "계좌 미등록 고객입니다. 계좌등록을 해주십시오.");
				msgBox.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						switch (event.getHideButton()) {
						case YES:
							lookUpAccount(cstUserModel);
							break;
						case NO:
						default:
							break;
						}
					}
				});
				msgBox.setWidth(300);
				msgBox.show();
				return ; 
			}
			else { // 로그인 정보를 찾을 수 없다.  
				new SimpleMessage("로그인 정보 확인", result.getMessage());
				return ; 
			}
		}
//		if (result.getStatus() == 10) { // 일반사용자 접속
//			// get userModel
//			Emp00_InfoModel empModel = (Emp00_InfoModel) result.getResult(0);
//			LoginUser.setEmpModel(empModel);
//		} else if (result.getStatus() == 20) { // 회사관리자 접속
//			// get userModel
//			Sys02_UserModel userModel = (Sys02_UserModel) result.getResult(0);
//			LoginUser.setUserModel(userModel);
//		} else { // 로그인 정보를 찾을 수 없다.
//			new SimpleMessage("로그인 정보 확인", result.getMessage());
//			return;
//		}
		openFrame();
	}
	
	private void lookUpAccount(Cst01_UserModel cstUserModel) {
		Cst01_Lookup_MemberJoinAccount lookupJoinAccount = new Cst01_Lookup_MemberJoinAccount();
		lookupJoinAccount.open(cstUserModel, null, "NEW", new InterfaceCallbackResult() {
			@Override
			public void execute(Object result) {
			}
		});
	}

	private void openFrame(){
		// 일반 사용자이다. 회사 선택없이 로드인한다. 
		this.viewport.remove(container);
		MainFrame window = new MainFrame();
//		viewport.setBorders(true);
		viewport.add(window.getMainWindow());
		RootPanel.get().add(viewport);
	}
}

