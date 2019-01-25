package myApp.client.vi;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.resource.ResourceIcon;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.vi.LoginUser;
import myApp.client.vi.cst.Cst01_Lookup_MemberJoin;

public class MainFrameNorthLayout extends BorderLayoutContainer {

	Viewport viewport = new Viewport();
	CommonComboBoxField fundComboBox = null;
	
	public MainFrameNorthLayout() {

		HBoxLayoutContainer header = new HBoxLayoutContainer();
//		header.setPadding(new Padding(5));
		header.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);

//		BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
//		flex.setFlex(1);
		    
		// KFIA-logo
		Image image = new Image();
		image.setResource(ResourceIcon.INSTANCE.getLogo());
//		image.setPixelSize(330, 26);
		image.setPixelSize(200,42);
		header.add(image, new BoxLayoutData(new Margins(5, 0, 0, 20)));
		
		BoxLayoutData boxLayoutData = new BoxLayoutData(new Margins(0, 0, 0, 0)); 
		boxLayoutData.setFlex(1);
		header.add(new Label(), boxLayoutData);


		//고객정보
//		String userInfo = LoginUser.getCompanyId() + " " + LoginUser.getUserName();
		String userInfo = LoginUser.getUserName();
		userInfo = "<p style='color:#808080; font-size:14px; font-weight:normal'>반갑습니다. " +  userInfo + "님</p>" ;
		SafeHtml safeEscapedHtml = SafeHtmlUtils.fromTrustedString(userInfo);
		LabelToolItem label = new LabelToolItem(safeEscapedHtml);
		header.add(label, new BoxLayoutData(new Margins(9, 0, 0, 5)));

		//고객정보 수정
//		String userInfoModi = "<p style='color:#808080; font-size:14px; font-weight:normal'><a href=\"#\">[정보수정]</a></p>" ;
//		SafeHtml safeEscapedHtml_userInfoModi = SafeHtmlUtils.fromTrustedString(userInfoModi);
//		LabelToolItem label_userInfoModi = new LabelToolItem(safeEscapedHtml_userInfoModi);
		Label userInfoModi = new HTML("<p style='color:#808080; font-size:14px; font-weight:normal'><a href=\"#\">[정보수정</a></p>");
		userInfoModi.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				Cst01_Lookup_MemberJoin memberJoinLookup = new Cst01_Lookup_MemberJoin();
				memberJoinLookup.open(LoginUser.getUserId(), new InterfaceCallbackResult() {
					@Override
					public void execute(Object result) {
					}
				});
			}
		});

		Label gb = new Label("/");

		//로그아웃
		Label userLogout = new HTML("<p style='color:#808080; font-size:14px; font-weight:normal'><a href=\"#\">로그아웃] </a></p>");
		userLogout.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				final ConfirmMessageBox msgBox = new ConfirmMessageBox("로그아웃", "로그아웃 하시겠습니까?");
				msgBox.addDialogHideHandler(new DialogHideHandler() {
					@Override
					public void onDialogHide(DialogHideEvent event) {
						switch (event.getHideButton()) {
						case YES:
							Window.Location.reload();
							break;
						case NO:
						default:
							break;
						}
					}
				});
				msgBox.setWidth(300);
				msgBox.show();
			}
		});
		HBoxLayoutContainer hlc = new HBoxLayoutContainer();
		hlc.add(userInfoModi, new BoxLayoutData(new Margins(0,0,0,0)));
		hlc.add(gb, new BoxLayoutData(new Margins(0,2,0,2)));
		hlc.add(userLogout, new BoxLayoutData(new Margins(0,0,0,0)));
		hlc.setWidth(150);
		header.add(hlc, new BoxLayoutData(new Margins(9, 0, 0, 0)));

		//로그아웃 이미지
//		TextButton logoutButton = new TextButton(new TextButtonCell(new WhiteGreyButtonCellAppearance<>()));
//		logoutButton.setText("");
//		logoutButton.setIcon(ResourceIcon.INSTANCE.closeButton());
//		logoutButton.setBorders(false);
//		logoutButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				Window.Location.reload();
//			}
//		});
//		header.add(logoutButton, new BoxLayoutData(new Margins(4, 40, 0, 0)));
//		header.setHeight(90);

//		Image borderBar = new Image(ResourceIcon.INSTANCE.borderBox());

		ContentPanel cp = new ContentPanel();
		cp.setBodyStyle("backgroundColor:#ffffff; color:#808080"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조
//		cp.setBodyStyle("backgroundColor:#217346; color:red"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조
//		cp.setBodyStyle("backgroundColor:#024059; color:red"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조
//		cp.setBodyStyle("backgroundColor:#28384a; color:red"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조

		cp.add(header);
//		cp.add(header, new BoxLayoutData(new Margins(0, 0, 0, 0)));
//		cp.add(borderBar, new BoxLayoutData(new Margins(0, 0, 0, 0)));
		
		cp.forceLayout();
		cp.setHeaderVisible(false);
//		cp.setHeight(90);
		cp.getButtonBar().setHeight(0);
//		cp.setBorders(true);
		
		this.add(cp);
	}
}