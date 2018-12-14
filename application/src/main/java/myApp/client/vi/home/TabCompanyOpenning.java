package myApp.client.vi.home;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.company.CeoGreeting;
import myApp.client.vi.home.company.CompanyOpening;
import myApp.client.vi.home.company.OperationOrganization;
import myApp.client.vi.home.company.YourWay;
import myApp.theme.tritium.custom.client.button.white.WhiteButtonCellAppearance;

public class TabCompanyOpenning extends ContentPanel {

	private CeoGreeting tabCeoGreeting  = new CeoGreeting();
	private CompanyOpening tabCompanyOpening  = new CompanyOpening();
	private OperationOrganization tabOperationOrganization  = new OperationOrganization();
	private YourWay tabYourWay  = new YourWay();
	
	protected static final int MAX_WIDTH = 1024;
	protected static final int MENU_WIDTH = 180;
	
	protected static final int CON_WIDTH = 800;
	protected static final int CON_HEIGHT = 750;
	
	protected static final String BTN_WIDTH = ""+MENU_WIDTH;
	protected static final String BTN_HEIGHT = "40";

	ContentPanel contentPanel  = new ContentPanel();

//	public TabCompanyOpenning() {
	public TabCompanyOpenning(StartPage startPage) {
		this.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize(startPage);
			}
		});
	}
	
	private void resize(StartPage startPage) {

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

		Margins lineImageMargins = new Margins(0, 0, 0, 0);
		Margins buttonMargins = new Margins(0, 0, 0, 0);
		
		Image lineImage0 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage1 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage2 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage3 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage4 = new Image(ResourceIcon.INSTANCE.verticalBar());

		headerVBox.add(StartPage.getLabelToolItem((Window.getClientWidth() - (CON_WIDTH+MENU_WIDTH))/2, "회사소개"));
		//, new BoxLayoutData(new Margins(2, 50, 100, 0)));
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
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍCEO 인사말　　　　　</font></div> ");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ회사개요　　　　　　</font></div> ");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ운용조직／전문인력　</font></div> ");
		SafeHtml button4Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ찾아오시는길　　　　</font></div> ");

		TextButton menuButton1 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton2 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton3 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton4 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
//		CellButtonBase menuButton1 = new CellButtonBase<>();
		menuButton1.setSize(BTN_WIDTH, BTN_HEIGHT);
		menuButton1.setHTML(button1Html);
//		menuButton1.setBorders(true);
		menuButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCeoGreeting();
			}
		});
		menuVBox.add(menuButton1, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage1, new BoxLayoutData(lineImageMargins));

//		CellButtonBase menuButton2 = new CellButtonBase<>();
		menuButton2.setSize(BTN_WIDTH, BTN_HEIGHT);
		menuButton2.setHTML(button2Html);
//		menuButton2.setBorders(true);
		menuButton2.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCompanyOpening();
			}
		});
		menuVBox.add(menuButton2, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage2, new BoxLayoutData(lineImageMargins));

//		CellButtonBase menuButton3 = new CellButtonBase<>();
		menuButton3.setSize(BTN_WIDTH, BTN_HEIGHT);
		menuButton3.setHTML(button3Html);
//		menuButton3.setBorders(true);
		menuButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getOperationOrganization();
			}
		});
		menuVBox.add(menuButton3, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage3, new BoxLayoutData(lineImageMargins));

//		CellButtonBase menuButton4 = new CellButtonBase<>();
		menuButton4.setSize(BTN_WIDTH, BTN_HEIGHT);
		menuButton4.setHTML(button4Html);
//		menuButton4.setBorders(true);
		menuButton4.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getYourWay();
			}
		});
		menuVBox.add(menuButton4, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage4, new BoxLayoutData(lineImageMargins));

		menuVBox.setWidth(MENU_WIDTH);
		menuVBox.setHeight(CON_HEIGHT);
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
		contentPanel.setWidth(CON_WIDTH);
		contentPanel.setHeight(CON_HEIGHT);
		contentPanel.setWidget(tabCeoGreeting);

		return contentPanel;
	}

	private ContentPanel getCompanyOpening() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(CON_WIDTH);
		contentPanel.setHeight(CON_HEIGHT);
		contentPanel.setWidget(tabCompanyOpening);

		return contentPanel;
	}
	
	private ContentPanel getOperationOrganization() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(CON_WIDTH);
		contentPanel.setHeight(CON_HEIGHT);
		contentPanel.setWidget(tabOperationOrganization);

		return contentPanel;
	}

	private ContentPanel getYourWay() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(CON_WIDTH);
		contentPanel.setHeight(CON_HEIGHT);
		contentPanel.setWidget(tabYourWay);

		return contentPanel;
	}

}