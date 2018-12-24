package myApp.client.vi.hom;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
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
import myApp.client.vi.hom.company.CeoGreeting;
import myApp.client.vi.hom.company.CeoGreeting1;
import myApp.client.vi.hom.company.CompanyOpening;
import myApp.client.vi.hom.company.CompanyOpening1;
import myApp.client.vi.hom.company.OperatingOrganization;
import myApp.client.vi.hom.company.OperatingOrganization1;
import myApp.client.vi.hom.company.YourWay;
import myApp.client.vi.hom.company.YourWay1;
import myApp.theme.tritium.custom.client.button.white.WhiteButtonCellAppearance;

public class TabCompanyOpenning extends ContentPanel {

	private CeoGreeting tabCeoGreeting  = new CeoGreeting();
	private CompanyOpening tabCompanyOpening  = new CompanyOpening();
	private OperatingOrganization tabOperatingOrganization  = new OperatingOrganization();
	private YourWay tabYourWay  = new YourWay();

	private CeoGreeting1 tabCeoGreeting1  = new CeoGreeting1();
	private CompanyOpening1 tabCompanyOpening1  = new CompanyOpening1();
	private OperatingOrganization1 tabOperatingOrganization1  = new OperatingOrganization1();
	private YourWay1 tabYourWay1  = new YourWay1();
	
	ContentPanel contentPanel  = new ContentPanel();

	public TabCompanyOpenning() {
		this.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();
			}
		});
		resize();
	}
	
	private void resize() {
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
		Image lineImage5 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage6 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage7 = new Image(ResourceIcon.INSTANCE.verticalBar());
		Image lineImage8 = new Image(ResourceIcon.INSTANCE.verticalBar());

		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍCEO 인사말　　　　　</font></div> ");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ회사개요　　　　　　</font></div> ");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ운용조직／전문인력　</font></div> ");
		SafeHtml button4Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ찾아오시는길　　　　</font></div> ");
		SafeHtml button5Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍCEO 인사말　　　　　</font></div> ");
		SafeHtml button6Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ회사개요　　　　　　</font></div> ");
		SafeHtml button7Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ운용조직／전문인력　</font></div> ");
		SafeHtml button8Html = SafeHtmlUtils.fromTrustedString("<left><div style='background-color: transparent;'><font color='#606060' style='font-size:14px;'>ㆍ찾아오시는길　　　　</font></div> ");

		TextButton menuButton1 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton2 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton3 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton4 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton5 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton6 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton7 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));
		TextButton menuButton8 = new TextButton(new TextButtonCell(new WhiteButtonCellAppearance<>()));

		// 타이틀 넣기
		headerVBox.add(StartPage.getLabelToolItem((Window.getClientWidth() - (StartPage.CON_WIDTH+StartPage.MENU_WIDTH))/2, "회사소개"));
		menuVBox.add(lineImage0, new BoxLayoutData(new Margins(20, 0, 0, 0)));

		menuButton1.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
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

		menuButton2.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
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

		menuButton3.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
		menuButton3.setHTML(button3Html);
//		menuButton3.setBorders(true);
		menuButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getOperatingOrganization();
			}
		});
		menuVBox.add(menuButton3, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage3, new BoxLayoutData(lineImageMargins));

		menuButton4.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
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

		menuButton5.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
		menuButton5.setHTML(button5Html);
		menuButton5.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCeoGreeting1();
			}
		});
		menuVBox.add(menuButton5, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage5, new BoxLayoutData(lineImageMargins));

		menuButton6.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
		menuButton6.setHTML(button6Html);
		menuButton6.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getCompanyOpening1();
			}
		});
		menuVBox.add(menuButton6, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage6, new BoxLayoutData(lineImageMargins));

		menuButton7.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
		menuButton7.setHTML(button7Html);
		menuButton7.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getOperatingOrganization1();
			}
		});
		menuVBox.add(menuButton7, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage7, new BoxLayoutData(lineImageMargins));

		menuButton8.setSize(StartPage.BTN_WIDTH, StartPage.BTN_HEIGHT);
		menuButton8.setHTML(button8Html);
		menuButton8.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getYourWay1();
			}
		});
		menuVBox.add(menuButton8, new BoxLayoutData(buttonMargins));
		menuVBox.add(lineImage8, new BoxLayoutData(lineImageMargins));

		menuHBar.add(menuVBox, boxLayoutData);
//		menuHBar.setBorders(true);

		totalHBar.add(menuHBar);
		switch (StartPage.CURRENTPAGE) {
		case "1" :
			totalHBar.add(getCeoGreeting());
			break;
		case "2" :
			totalHBar.add(getCompanyOpening());
			break;
		case "3" :
			totalHBar.add(getOperatingOrganization());
			break;
		case "4" :
			totalHBar.add(getYourWay());
			break;
		case "5" :
			totalHBar.add(getCeoGreeting1());
			break;
		case "6" :
			totalHBar.add(getCompanyOpening1());
			break;
		case "7" :
			totalHBar.add(getOperatingOrganization1());
			break;
		case "8" :
			totalHBar.add(getYourWay1());
			break;
		default :
			totalHBar.add(getCeoGreeting());
			break;
		}
		centerVBox.add(totalHBar);
		headerVBox.add(centerVBox);
		this.add(headerVBox);
	}

	private ContentPanel getCeoGreeting() {

		StartPage.CURRENTPAGE = "1";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabCeoGreeting);

		return contentPanel;
	}

	private ContentPanel getCompanyOpening() {

		StartPage.CURRENTPAGE = "2";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabCompanyOpening);

		return contentPanel;
	}
	
	private ContentPanel getOperatingOrganization() {

		StartPage.CURRENTPAGE = "3";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabOperatingOrganization);

		return contentPanel;
	}

	private ContentPanel getYourWay() {

		StartPage.CURRENTPAGE = "4";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabYourWay);

		return contentPanel;
	}
	
	private ContentPanel getCeoGreeting1() {

		StartPage.CURRENTPAGE = "5";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabCeoGreeting1);

		return contentPanel;
	}

	private ContentPanel getCompanyOpening1() {

		StartPage.CURRENTPAGE = "6";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabCompanyOpening1);

		return contentPanel;
	}
	
	private ContentPanel getOperatingOrganization1() {

		StartPage.CURRENTPAGE = "7";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabOperatingOrganization1);

		return contentPanel;
	}

	private ContentPanel getYourWay1() {

		StartPage.CURRENTPAGE = "8";
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(StartPage.CON_WIDTH);
		contentPanel.setHeight(Window.getClientHeight() - StartPage.NOTE_HEIGHT);
		contentPanel.setWidget(tabYourWay1);

		return contentPanel;
	}
}