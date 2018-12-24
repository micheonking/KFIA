package myApp.client.vi.hom;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class StartPage extends BorderLayoutContainer {

	protected final int WIN_WIDTH = Window.getClientWidth();
	protected final int WIN_HEIGHT = Window.getClientHeight();
	protected final int HEADER_HEIGHT = 70;
	protected final int FOOTER_HEIGHT = 50;
	protected final int CENTER_HEIGHT = 250;
	protected final static int TITLE_HEIGHT = 60;

	protected static final int MAX_WIDTH = 1024;

	protected static final int MENU_WIDTH = 180;
	protected static final int MENU_HEIGHT = 900;
	protected static final int CON_WIDTH = 770;
	protected static final int CON_HEIGHT = Window.getClientHeight() - 253;
	protected static final String WBTN_WIDTH = "80";
	protected static final String WBTN_HEIGHT = "30";
	protected static final String BTN_WIDTH = ""+MENU_WIDTH;
	protected static final String BTN_HEIGHT = "40";

	protected static final int NOTE_HEIGHT = 100;	//	내용부분 높이조절용 : 높이에서 뺄 Size...
	public static final int SNOTE_HEIGHT = 300;	//	내용부분 높이조절용 : 높이에서 뺄 Size...
	
	public static int CURRENTWIDTH = 770;
	public static int CURRENTHEIGHT = 0;
	static String CURRENTPAGE = "0";

	private StartPageHeader startPageHeader = new StartPageHeader(this);
	private StartPageFooter startPageFooter = new StartPageFooter();
//	public static PlainTabPanel tabPanel = new PlainTabPanel();
//	private StartPage startPage;
	
	public StartPage() {

		this.setBorders(false);

		BorderLayoutData northLayoutData = new BorderLayoutData(HEADER_HEIGHT);
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		this.setNorthWidget(startPageHeader, northLayoutData);
//		northLayoutData.setSplit(true);

		BorderLayoutData southLayoutData = new BorderLayoutData(FOOTER_HEIGHT);
		southLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		this.setSouthWidget(startPageFooter, southLayoutData);
//		southLayoutData.setSplit(true);
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(CENTER_HEIGHT);
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
//		centerLayoutData.setSplit(true);

		this.changePage("0");

	}

	public void changePage(String pageName) {
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(CENTER_HEIGHT);
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		centerLayoutData.setSplit(true);

		switch (pageName) {
		case "1" :
			TabCompanyOpenning tabCompanyOpenning = new TabCompanyOpenning();
			this.setCenterWidget(tabCompanyOpenning, centerLayoutData);
			break;
		case "2" :
			TabEntrustInvestments tabEntrustInvestments = new TabEntrustInvestments();
			this.setCenterWidget(tabEntrustInvestments, centerLayoutData);
			break;
		case "3" :
			TabProductInformation tabProductInformation = new TabProductInformation();
			this.setCenterWidget(tabProductInformation, centerLayoutData);
			break;
		case "4" :
			TabReportNews tabReportNews = new TabReportNews();
			this.setCenterWidget(tabReportNews, centerLayoutData);
			break;
		default :
			TabBeginnigPage tabBeginnigPage = new TabBeginnigPage();
			this.setCenterWidget(tabBeginnigPage, centerLayoutData);
			break;
		}
		this.onLoad();
	}

//	public static void openTabPage(TabPanel tabPanel, String pageName) {
//		//
//		Widget tabPage = tabPanel.findItem(pageName, true);
//		tabPanel.setActiveWidget(tabPage);
//
//	}
	
	public static ContentPanel getLabelToolItem(int xPosition, String textHtml) {
		// TODO Auto-generated constructor stub  023d69

		ContentPanel contentPanel = new ContentPanel();
		contentPanel.setBodyStyle("backgroundColor:#023d69");
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(Window.getClientWidth()); 
		contentPanel.setHeight(TITLE_HEIGHT);

		Label labelHtml = new HTML(	"<div style='background-color: #023d69; line-height:90%; '>"
								+	"<span style='font-weight:bold; font-size:2.0em;'><font color='#ffffff'>" + textHtml
								+	"&nbsp;&nbsp;</font></span></div>");

//		contentPanel.add(labelHtml, new BoxLayoutData(new Margins(25, (100 * WIN_WIDTH) / 1920, 0, (490 * WIN_WIDTH) / 1920)));
//		contentPanel.add(labelHtml, new BoxLayoutData(new Margins(25, 0, 0, WIN_WIDTH - (WIN_WIDTH * 490 / WIN_WIDTH))));
		if (xPosition < 20 ) {
			xPosition = 20;
		}
		contentPanel.add(labelHtml, new BoxLayoutData(new Margins(25, 0, 0, xPosition)));
		
		return contentPanel;
//		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
//		HorizontalLayoutData hld = new HorizontalLayoutData(1, 90, new Margins(10, 1, 1, 1));
//
//		row01.setBorders(true);
//		Margins margins = new Margins();
//		margins.setTop(0);
//		margins.setLeft(0);
//		margins.setRight(0);
//		margins.setBottom(0);
//
//		row01.setWidth(3000);
//		row01.setHeight(100);
//		hld.setMargins(margins);
//		row01.add(labelHtml, hld);
//		row01.add(labelHtml, new BoxLayoutData(new Margins(10, 1, 100, 1)));

//		return row01;
		
//		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
//		layoutContainer.setBorders(true);
//		layoutContainer.add(row01, new VerticalLayoutData(1, -1, new Margins(2, 10, 2, 10)));
//
//		BorderLayoutContainer borderLayoutContainer = new BorderLayoutContainer();
//		borderLayoutContainer.add(layoutContainer);
//		borderLayoutContainer.setBorders(true);
//		return borderLayoutContainer;
		
//		SafeHtml label1Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:110%; '>"
//															+	"<span style='font-size:0.1em;'><br><br></span>"
//															+	"<span style='font-weight:bold; font-size:2.3em;'>"
//															+	"<font color='#ffffff'><br></font></span>"
//															+	"<span style='font-size:0.1em;'><br></span>"
//															+	"<span style='font-size:0.1em;'><br></span>"
//															+	"</div>"
//															);
//		LabelToolItem labelToolItem1 = new LabelToolItem(label1Html);
//		labelToolItem1.setWidth(460);
//		labelToolItem1.setHeight(70);
//		labelToolItem1.setBorders(false);
//
//		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:110%; '>"
//															+	"<span style='font-size:0.1em;'><br><br></span>"
//															+	"<span style='font-weight:bold; font-size:2.3em;'>"
//															+	"<font color='#ffffff'>" + textHtml + "<br></font></span>"
//															+	"<span style='font-size:0.1em;'><br></span>"
//															+	"</div>"
//															);
//
////		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString("<left><font color='#ffffff' style='font-size:32px;font-weight:bold;'><p style='background-color: #023d69;'>"+ textHtml +"</p></font>");
//		LabelToolItem labelToolItem2 = new LabelToolItem(label2Html);
//		labelToolItem2.setWidth(200);
//		labelToolItem2.setHeight(70);
//		labelToolItem2.setBorders(false);
//
////		SafeHtml label3Html = SafeHtmlUtils.fromTrustedString("<left><font color='#ffffff' style='font-size:32px;font-weight:bold;'><p style='background-color: #023d69;'><br><br>　<br><br></p></font>");
//		SafeHtml label3Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:110%; '>"
//															+	"<span style='font-size:0.1em;'><br><br></span>"
//															+	"<span style='font-weight:bold; font-size:2.3em;'>"
//															+	"<font color='#ffffff'><br></font></span>"
//															+	"<span style='font-size:0.1em;'><br></span>"
//															+	"<span style='font-size:0.1em;'><br></span>"
//															+	"</div>"
//															);
//		LabelToolItem labelToolItem3 = new LabelToolItem(label3Html);
//		labelToolItem3.setWidth(1340);
//		labelToolItem3.setHeight(70);
//		labelToolItem3.setBorders(false);
//
//		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
//		HorizontalLayoutData hld = new HorizontalLayoutData();
//		hlc.setBorders(false);
//		Margins margins = new Margins();
//		margins.setTop(0);
//		margins.setLeft(0);
//		margins.setRight(0);
//		margins.setBottom(0);
//
//		hlc.setWidth(2000);
//		hlc.setHeight(70);
//		hld.setMargins(margins);
//		hlc.add(labelToolItem1, hld);
//		hlc.add(labelToolItem2, hld);
//		hlc.add(labelToolItem3, hld);
//
//		hlc.add(labelHtml, hld);
//
//		return hlc;
	}

	public static HBoxLayoutContainer getTextContents(String textHtml) {
		SafeHtml labelHtml = SafeHtmlUtils.fromTrustedString("<left><font color='#015ca3' font-family='Nanum Gothic' size='5'><p style='font-weight:bold;'>"+ textHtml +"</p></font>");
		LabelToolItem labelToolItem = new LabelToolItem(labelHtml);
		
		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
		hblc.add(labelToolItem, new BoxLayoutData(new Margins(30, 600, 10, 22)));
		
		return hblc;
	}
}