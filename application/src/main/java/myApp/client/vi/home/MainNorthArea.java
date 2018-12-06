package myApp.client.vi.home;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginPage;
import myApp.client.vi.LoginUser;
import myApp.client.vi.MainFrame;
import myApp.client.vi.emp.model.Emp00_InfoModel;
import myApp.client.vi.sys.model.Sys02_UserModel;

public class MainNorthArea extends BorderLayoutContainer {

	private TextField firstName = new TextField();
	private PasswordField password= new PasswordField();
//	private TextField otpNumber = new TextField();
	private PasswordField otpNumber = new PasswordField();
    private CenterLayoutContainer container = new CenterLayoutContainer();
	Viewport viewport = new Viewport();

	public MainNorthArea() {

		VBoxLayoutContainer center = new VBoxLayoutContainer();
		center.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);

		HBoxLayoutContainer header = new HBoxLayoutContainer();
		header.setPadding(new Padding(2));
		header.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

		// 홈페이지 상단 회사로고
		SafeHtml logoHtml = SafeHtmlUtils.fromTrustedString("<img src='img/_KFIAMLogo.png' style='margin:0px 0px'> </img>");
		CellButtonBase mainButton = new CellButtonBase<>();
//		mainButton.setIconAlign(IconAlign.TOP);
//		mainButton.setIcon(ResourceIcon.INSTANCE.getLogo());
		mainButton.setSize("170", "44");
		mainButton.setHTML(logoHtml);
		mainButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				MainFramePage.openTabPage(MainFramePage.tabPanel, "");
			}
		});
		header.add(mainButton, new BoxLayoutData(new Margins(15, 280, 0, 20)));
		
		// 버튼생성
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<font color='#606060' style='font-size:16px;'>회사소개</font> ");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString("<font color='#606060' style='font-size:16px;'>투자일임</font> ");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString("<font color='#606060' style='font-size:16px;'>상품안내</font> ");
		SafeHtml button4Html = SafeHtmlUtils.fromTrustedString("<font color='#606060' style='font-size:16px;'>KFIA소식</font> ");
		SafeHtml button5Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #1d7bbb;'><font color='#dddddd' style='font-size:16px; '>고객정보</font></div>" );
//		SafeHtml label1Html = SafeHtmlUtils.fromTrustedString(	"<center>"
//				+	"<div style='background-color: #1d7bbb; line-height:130%; '>"
//				+	"<span style='font-size:0.1em;'><br></span>"
//				+	"<div><img src='img/icon_left.png' width='32' height='40'></div>"
//				+	"<span style='font-size:0.1em;'><br></span>"
//				+	"<span style='font-weight:normal; font-size:1.2em;'>"
//				+	"<font color='#eeeeee'>총 운용규모<br>약 3조 1,426억</font></span>"
//				+	"<span style='font-size:0.1em;'><br><br></span>"
//				+	"</div>"
//				);

		BoxLayoutData boxLayoutData = new BoxLayoutData(new Margins(25, 0, 0, 25));
		TextButton textButton1 = new TextButton("");
		TextButton textButton2 = new TextButton("");
		TextButton textButton3 = new TextButton("");
		TextButton textButton4 = new TextButton("");
		TextButton textButton5 = new TextButton("");

//		header.add(new Label(), boxLayoutData);
		textButton1.setHTML(button1Html);
		textButton1.setWidth(120);
		textButton1.setHeight(40);
		textButton1.setBorders(true);
//		textButton1.setIcon(ResourceIcon.INSTANCE.blank());
//		textButton1.setIconAlign(IconAlign.TOP);
		header.add(textButton1, boxLayoutData);
		textButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				MainFramePage.openTabPage(MainFramePage.tabPanel, "회사소개");
			}
		});
		textButton2.setHTML(button2Html);
		textButton2.setWidth(120);
		textButton2.setHeight(40);
		textButton2.setBorders(true);
//		textButton2.setIconAlign(IconAlign.BOTTOM);
//		textButton2.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton2, boxLayoutData);
		textButton2.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				MainFramePage.openTabPage(MainFramePage.tabPanel, "투자일임");
			}
		});
		textButton3.setHTML(button3Html);
		textButton3.setWidth(120);
		textButton3.setHeight(40);
		textButton3.setBorders(true);
//		textButton3.setIconAlign(IconAlign.BOTTOM);
//		textButton3.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton3, boxLayoutData);
		textButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				MainFramePage.openTabPage(MainFramePage.tabPanel, "상품안내");
			}
		});
		textButton4.setHTML(button4Html);
		textButton4.setWidth(120);
		textButton4.setHeight(40);
		textButton4.setBorders(true);
//		textButton4.setIconAlign(IconAlign.BOTTOM);
//		textButton4.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton4, boxLayoutData);
		textButton4.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				MainFramePage.openTabPage(MainFramePage.tabPanel, "KFIA소식");
			}
		});
		textButton5.setHTML(button5Html);
		textButton5.setWidth(120);
		textButton5.setHeight(40);
		textButton5.setBorders(true);
//		textButton5.setIconAlign(IconAlign.BOTTOM);
//		textButton5.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton5, boxLayoutData);
		textButton5.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

//				myApp.client.vi.LoginPage2 login = new LoginPage2();
//
				RootPanel.get().remove(0);
				myApp.client.vi.LoginPage login = new LoginPage();
				login.open();  
//				Viewport viewport = new Viewport();
////				viewport.add(login.getContainer(), new MarginData(0, 0, 0, 0));
//				
//				ContentPanel cp =  new ContentPanel();
//				
//				
//				cp.add(new TextButton("찾기")); 
//				
//				
////				cp.add(login.getContainer()); 
//				
//				viewport.add(cp, new MarginData(0, 0, 0, 0));
//				RootPanel.get().add(viewport);
//				
//				
////				login.open();  
//				
//				FieldLabel loginFieldLabel = new FieldLabel(firstName, "로그인ID ");
//				loginFieldLabel.setLabelWidth(85);
//				firstName.setText("");
//				firstName.addKeyPressHandler(new KeyPressHandler() {
//					@Override
//					public void onKeyPress(KeyPressEvent event) {
//						if(event.getCharCode() == 13) {
//							login(); 
//						}
//					}
//				}); 
				
//				openFrame();
//				Viewport viewport = new Viewport();
//				MainFrame window = new MainFrame(); 
//				viewport.add(window.getMainWindow());
//				RootPanel.get().add(viewport);
				
				
			}
		});

		center.add(header);
		
		ContentPanel cp = new ContentPanel();
		cp.setBodyStyle("backgroundColor:ffffff"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조

		cp.add(center);
		
		cp.forceLayout();
		cp.setHeaderVisible(false);
//		cp.setBorders(true);
		cp.setHeight(60);
		cp.getButtonBar().setHeight(0);
		
		this.add(cp);
	}

//	public void login(){
//
//		if (otpNumber.getValue() == null) {
//			new SimpleMessage("확인", "OTP인증번호를 입력하여 주십시오.");
//			return;
//		}
//
//		// admin id & pw 관리필요. 
//		if("admin".equals(firstName.getText())) {
//
//			LoginUser.setIsAdmin(true);
//
//			ServiceRequest request = new ServiceRequest("sys.Sys02_User.getLoginAdminInfo");
//			request.putLongParam  ("companyId", (long)2000940);	//2000940:한국펀드서비스
//			request.putStringParam("otpNumber", "1111");//otpNumber.getValue());
//			ServiceCall service = new ServiceCall(); 
//			service.execute(request, this);
//		} 
//		else {
//			// 로그인 정보를 찾는다. 
//			ServiceRequest request = new ServiceRequest("sys.Sys02_User.getLoginInfo");
//			request.putStringParam("loginId", firstName.getText());
//			request.putStringParam("passwd", password.getText());
//			request.putStringParam("otpNumber", otpNumber.getValue());
//			ServiceCall service = new ServiceCall(); 
//			service.execute(request, this);
//		}
//	}
//	
////	@Override
//	public void getServiceResult(ServiceResult result) {
//		
//		if (LoginUser.getIsAdmin()) {
//			if(result.getStatus() == 10 ) {
//				this.viewport.remove(container);
//				viewport.add(new MainFramePage(), new MarginData(0, 0, 0, 0));
//				RootPanel.get().add(viewport);
//			} else {
//				new SimpleMessage("로그인 정보 확인", result.getMessage());
//			}
//			return;
//		} else {
//			if(result.getStatus() == 10 ) { // 일반사용자 접속
//				// get userModel
//				Emp00_InfoModel empModel = (Emp00_InfoModel) result.getResult(0);
//				LoginUser.setEmpModel(empModel); 
//			}
//			else if(result.getStatus() == 20) { // 회사관리자 접속
//				// get userModel
//				Sys02_UserModel userModel = (Sys02_UserModel) result.getResult(0); 
//				LoginUser.setUserModel(userModel); 
//			}
//			else { // 로그인 정보를 찾을 수 없다.  
//				new SimpleMessage("로그인 정보 확인", result.getMessage());
//				return ; 
//			}
//			openFrame();		
//		}
//	}
//	
//	private void openFrame(){
//		// 일반 사용자이다. 회사 선택없이 로드인한다. 
////		this.viewport.remove(container);
//		MainFrame window = new MainFrame(); 
//		viewport.add(window.getMainWindow());
//		RootPanel.get().add(viewport);
//	}
}