package myApp.client.vi.home;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.company.CeoGreeting;
import myApp.client.vi.home.company.CompanyOpening;
import myApp.client.vi.home.company.OperationOrganizationAndProfessionalPersonnel;
import myApp.client.vi.home.company.YourWay;

public class TabCompanyOpenning extends ContentPanel {

	private CeoGreeting tabCeoGreeting  = new CeoGreeting();
	private CompanyOpening tabCompanyOpening  = new CompanyOpening();
	private OperationOrganizationAndProfessionalPersonnel tabOperationOrganizationAndProfessionalPersonnel  = new OperationOrganizationAndProfessionalPersonnel();
	private YourWay tabYourWay  = new YourWay();
	
	ContentPanel contentPanel  = new ContentPanel();

	public TabCompanyOpenning() {

		this.setHeaderVisible(false);
		this.setBorders(false);

		VBoxLayoutContainer headerVBox = new VBoxLayoutContainer();
		headerVBox.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);

		VBoxLayoutContainer centerVBox = new VBoxLayoutContainer();
		centerVBox.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);

		HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
		totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

		HBoxLayoutContainer menuHBar = new HBoxLayoutContainer();
		menuHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);

		BoxLayoutData boxLayoutData = new BoxLayoutData();

		VBoxLayoutContainer menuVBox = new VBoxLayoutContainer();
		menuVBox.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);

		Image lineImage0 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage1 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage2 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage3 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage4 = new Image(ResourceIcon.INSTANCE.verticalBar());

		headerVBox.add(StartPage.getLabelToolItem("회사소개"));//, new BoxLayoutData(new Margins(2, 50, 100, 0)));
//		Label labelHtml = new HTML(
////				"<center><font size='2' color=#aaaaaa><p style='background-color: #023d69;'>"+ textHtml +"</p></font></center>");
////		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString(	
//				"<div style='background-color: #023d69; line-height:90%; '>"
//				+	"<span style='font-size:0.1em;'><br><br></span>"
//				+	"<span style='font-weight:bold; font-size:2.3em;'><font color='#ffffff'>" + "회사소개" + "<br></font></span>"
//				+	"<span style='font-size:0.1em;'><br></span>"
//				+	"</div>"
//				);
//		labelHtml.setWidth("350");
//		labelHtml.setHeight("70");
//		
////		HBoxLayoutContainer labelHBar = new HBoxLayoutContainer();
////		labelHBar.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
////
////		labelHBar.add(labelHtml);
//
//		ContentPanel contentPanel = new ContentPanel();
//		contentPanel.setHeaderVisible(false);
//		contentPanel.setBorders(true);
//		contentPanel.setBodyStyle("backgroundColor:#023d69");
//
//		contentPanel.add(labelHtml, new BoxLayoutData(new Margins(5, 5, 5, 450)));
//		contentPanel.setWidth(2000); 
//		contentPanel.setHeight(80);
//		
//		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
//		HorizontalLayoutData hld = new HorizontalLayoutData(1, 90, new Margins(1, 1, 1, 1));
//
//		row01.setBorders(true);
////		Margins margins = new Margins();
////		margins.setTop(0);
////		margins.setLeft(0);
////		margins.setRight(0);
////		margins.setBottom(0);
////
////		row01.setWidth(3000);
////		row01.setHeight(100);
////		hld.setMargins(margins);
//		row01.add(contentPanel, hld);
//		headerVBox.add(row01, new BoxLayoutData(new Margins(10, 1, 100, 1)));

		menuVBox.add(lineImage0, new BoxLayoutData(new Margins(20, 0, 0, 0)));
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍCEO 인사말　　　　　</font></div> ");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ회사개요　　　　　　</font></div> ");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ운용조직 및 전문인력</font></div> ");
		SafeHtml button4Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ찾아오시는길　　　　</font></div> ");
		CellButtonBase mainButton1 = new CellButtonBase<>();
		mainButton1.setSize("200", "40");
		mainButton1.setHTML(button1Html);
//		mainButton1.setBorders(true);
		mainButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCeoGreeting();
			}
		});
		menuVBox.add(mainButton1, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage1, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton2 = new CellButtonBase<>();
		mainButton2.setSize("200", "40");
		mainButton2.setHTML(button2Html);
//		mainButton2.setBorders(true);
		mainButton2.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCompanyOpening();
			}
		});
		menuVBox.add(mainButton2, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage2, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton3 = new CellButtonBase<>();
		mainButton3.setSize("200", "40");
		mainButton3.setHTML(button3Html);
//		mainButton3.setBorders(true);
		mainButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getOperationOrganizationAndProfessionalPersonnel();
			}
		});
		menuVBox.add(mainButton3, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage3, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton4 = new CellButtonBase<>();
		mainButton4.setSize("200", "40");
		mainButton4.setHTML(button4Html);
//		mainButton4.setBorders(true);
		mainButton4.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getYourWay();
			}
		});
		menuVBox.add(mainButton4, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage4, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		menuVBox.setWidth(220);
		menuVBox.setHeight(750);
//		menuVBox.setBorders(true);

		menuHBar.add(menuVBox, boxLayoutData);
//		menuHBar.setBorders(true);

		totalHBar.add(menuHBar);
		totalHBar.add(getCeoGreeting());

		centerVBox.add(totalHBar);

		headerVBox.add(centerVBox);
		this.add(headerVBox);

	}

	private ContentPanel getCeoGreeting() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabCeoGreeting);

		return contentPanel;
	}

	private ContentPanel getCompanyOpening() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabCompanyOpening);

		return contentPanel;
	}
	
	private ContentPanel getOperationOrganizationAndProfessionalPersonnel() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabOperationOrganizationAndProfessionalPersonnel);

		return contentPanel;
	}

	private ContentPanel getYourWay() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabYourWay);

		return contentPanel;
	}

}