package myApp.client.vi.home;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.LoginPage;
import myApp.theme.tritium.custom.client.button.purple.PurpleButtonCellAppearance;
import myApp.theme.tritium.custom.client.button.red.RedButtonCellAppearance;
import myApp.theme.tritium.custom.client.button.white.WhiteButtonCellAppearance;

public class StartPageHeader extends BorderLayoutContainer {

	protected static final String BTN_WIDTH = "80";
	protected static final String BTN_HEIGHT = "30";

	Viewport viewport = new Viewport();
//	private StartPage startPage;

	public StartPageHeader(StartPage startPage) {

//		this.startPage = startPage;

		VBoxLayoutContainer center = new VBoxLayoutContainer();
		center.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);

		HBoxLayoutContainer header = new HBoxLayoutContainer();
		header.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

		Image lineImage1 = new Image(ResourceIcon.INSTANCE.horizontalTitle());
		Image lineImage2 = new Image(ResourceIcon.INSTANCE.horizontalTitle());
		Image lineImage3 = new Image(ResourceIcon.INSTANCE.horizontalTitle());

		// 홈페이지 상단 회사로고 style='border-bottom: 5px solid orange;' 
		SafeHtml logoHtml = SafeHtmlUtils.fromTrustedString("<left><div><img src='img/_KFIALogo.png' width='170' height='44'></img></div>");
		
	    TextButton logoButton = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
//		logoButton.setIconAlign(IconAlign.TOP);
//		logoButton.setIcon(ResourceIcon.INSTANCE.getLogo());
		logoButton.setSize("191", "58");
		logoButton.setHTML(logoHtml);
		logoButton.setBorders(false);
//		logoButton.setLayoutData(new BoxLayoutData(new Margins(1, 1, 1, 1)));
//		logoButton.getElement().getMargins(Side.LEFT);
		logoButton.getElement().setMargins(new Margins(0, 0, 0, 0));
		logoButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				startPage.openTabPage(startPage.tabPanel, "");
				int xPosition = logoButton.getAbsoluteLeft();
				startPage.changePage(xPosition, "0");
			}
		});
		header.add(logoButton, new BoxLayoutData(new Margins(6, 180, 0, 5)));
		
		// 버튼생성
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString(
				"<div style='background-color: transparent;'><font color='#606060' style='font-size:16px;font-weight:bold'>회사소개</font></div>");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString(
				"<div style='background-color: transparent;'><font color='#606060' style='font-size:16px;font-weight:bold'>투자일임</font></div>");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString(
				"<div style='background-color: transparent;'><font color='#606060' style='font-size:16px;font-weight:bold'>상품안내</font></div>");
		SafeHtml button4Html = SafeHtmlUtils.fromTrustedString(
				"<div style='background-color: transparent;'><font color='#606060' style='font-size:16px;font-weight:bold'>KFIA소식</font></div>");
//		SafeHtml button5Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #5fa2dd;'><font color='#dddddd' style='font-size:16px;font-weight:bold'>고객정보</font></div>" );
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

		BoxLayoutData boxLayoutData = new BoxLayoutData(new Margins(20, 25, 0, 0));
		BoxLayoutData boxLayoutData1 = new BoxLayoutData(new Margins(30, 25, 0, 0));

		// WhiteButton
	    TextButton textButton1 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
	    TextButton textButton2 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
	    TextButton textButton3 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
	    TextButton textButton4 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
//		TextButton textButton1 = new TextButton("");
//		TextButton textButton2 = new TextButton("");
//		TextButton textButton3 = new TextButton("");
//		TextButton textButton4 = new TextButton("");
		TextButton textButton5 = new TextButton("");

//		header.add(new Label(), boxLayoutData);
		textButton1.setHTML(button1Html);
//		textButton1.setText("회사소개");
		textButton1.setSize(BTN_WIDTH, BTN_HEIGHT);
//		textButton1.getElement().setMargins(new Margins(7, 7, 7, 7));
//		textButton1.setBorders(true);
//		textButton1.setIcon(ResourceIcon.INSTANCE.blank());
//		textButton1.setIconAlign(IconAlign.TOP);
//		textButton1.getElement().getStyle().setProperty("color", "#606060"); // font color 변경
//		textButton1.getElement().getStyle().setProperty("fontWeight", "bold"); // font color 변경
//		textButton1.getElement().getStyle().setProperty("fontSize", "16px"); // font color 변경

		header.add(textButton1, boxLayoutData);
		header.add(lineImage1, boxLayoutData1);
		textButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				startPage.openTabPage(startPage.tabPanel, "회사소개");
//				Info.display("", "" + logoButton.getAbsoluteLeft());
				int xPosition = logoButton.getAbsoluteLeft();
				startPage.changePage(xPosition, "1");
			}
		});
		textButton2.setHTML(button2Html);
		textButton2.setSize(BTN_WIDTH, BTN_HEIGHT);
//		textButton2.setBorders(true);
//		textButton2.setIconAlign(IconAlign.BOTTOM);
//		textButton2.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton2, boxLayoutData);
		header.add(lineImage2, boxLayoutData1);
		textButton2.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				startPage.openTabPage(startPage.tabPanel, "투자일임");
				int xPosition = logoButton.getAbsoluteLeft();
				startPage.changePage(xPosition, "2");
			}
		});
		textButton3.setHTML(button3Html);
		textButton3.setSize(BTN_WIDTH, BTN_HEIGHT);
//		textButton3.setBorders(true);
//		textButton3.setIconAlign(IconAlign.BOTTOM);
//		textButton3.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton3, boxLayoutData);
		header.add(lineImage3, boxLayoutData1);
		textButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				startPage.openTabPage(startPage.tabPanel, "상품안내");
				int xPosition = logoButton.getAbsoluteLeft();
				startPage.changePage(xPosition, "3");
			}
		});
		textButton4.setHTML(button4Html);
		textButton4.setSize(BTN_WIDTH, BTN_HEIGHT);
//		textButton4.setBorders(true);
//		textButton4.setIconAlign(IconAlign.BOTTOM);
//		textButton4.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton4, boxLayoutData);
		textButton4.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
//				startPage.openTabPage(startPage.tabPanel, "KFIA소식");
				int xPosition = logoButton.getAbsoluteLeft();
				startPage.changePage(xPosition, "4");
			}
		});
//		textButton5.setHTML(button5Html);
		textButton5.setText("고객정보");
		textButton5.setWidth(120);
		textButton5.setHeight(30);
		textButton5.setBorders(false);
//		textButton5.setIconAlign(IconAlign.BOTTOM);
//		textButton5.setIcon(ResourceIcon.INSTANCE.blank());
		header.add(textButton5, new BoxLayoutData(new Margins(20, 5, 0, 15)));
		textButton5.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

				RootPanel.get().remove(0);
				myApp.client.vi.LoginPage login = new LoginPage();
				login.open();

			}
		});

		center.add(header);
		
		ContentPanel cp = new ContentPanel();
		cp.setBodyStyle("backgroundColor:white; color:red"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조

		cp.add(center);
		
		cp.forceLayout();
		cp.setHeaderVisible(false);
//		cp.setBorders(true);
//		cp.setHeight(70);
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
//				viewport.add(new startPage(), new MarginData(0, 0, 0, 0));
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