package myApp.client.vi.home;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class MainFramePage extends BorderLayoutContainer {

	private MainNorthArea mainNorthArea = new MainNorthArea(this);
	private MainSouthArea mainSouthArea = new MainSouthArea();
	public static PlainTabPanel tabPanel = new PlainTabPanel();

	public MainFramePage() {

		this.setBorders(false);

		BorderLayoutData northLayoutData = new BorderLayoutData(70);
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		this.setNorthWidget(mainNorthArea, northLayoutData);
//		northLayoutData.setSplit(true);
		
		BorderLayoutData southLayoutData = new BorderLayoutData(70);
		southLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		this.setSouthWidget(mainSouthArea, southLayoutData);
//		southLayoutData.setSplit(true);
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(250);
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
//		centerLayoutData.setSplit(true);

		this.changePage("0");
		
	}

	public void changePage(String pageName) {
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(250);
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
//		centerLayoutData.setSplit(true);

		switch (pageName) {
		case "1" :
			TabOurCompany tabCompany = new TabOurCompany();
			this.setCenterWidget(tabCompany, centerLayoutData);
			break;
		case "2" :
			TabOurInvestment tabBusiness = new TabOurInvestment();
			this.setCenterWidget(tabBusiness, centerLayoutData);
			break;
		case "3" :
			TabOurProduct tabSolution = new TabOurProduct();
			this.setCenterWidget(tabSolution, centerLayoutData);
			break;
		case "4" :
			TabOurNews tabNews = new TabOurNews();
			this.setCenterWidget(tabNews, centerLayoutData);
			break;
		default :
			TabFrontPage tabFrontPage = new TabFrontPage();
			this.setCenterWidget(tabFrontPage, centerLayoutData);
			break;
		}
		this.onLoad();
	}

	public static void openTabPage(TabPanel tabPanel, String pageName) {
		//
		Widget tabPage = tabPanel.findItem(pageName, true);
		tabPanel.setActiveWidget(tabPage);

	}
	
	public static HorizontalLayoutContainer FuncLabelToolItem(String textHtml) {
		// TODO Auto-generated constructor stub  023d69
		ContentPanel contentPanel = new ContentPanel();
		
		Label labelHtml = new HTML(
//				"<center><font size='2' color=#aaaaaa><p style='background-color: #023d69;'>"+ textHtml +"</p></font></center>");
//		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString(	
				"<div style='background-color: #023d69; line-height:90%; '>"
				+	"<span style='font-size:0.1em;'><br><br></span>"
				+	"<span style='font-weight:bold; font-size:2.3em;'><font color='#ffffff'>" + textHtml + "<br></font></span>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"</div>"
				);
		labelHtml.setWidth("250");
		labelHtml.setHeight("70");
		
//		HBoxLayoutContainer labelHBar = new HBoxLayoutContainer();
//		labelHBar.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
//
//		labelHBar.add(labelHtml);

		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setBodyStyle("backgroundColor:#FFFFFF");

		contentPanel.add(labelHtml, new BoxLayoutData(new Margins(5, 420, 20, 5)));
		contentPanel.setWidth(2900); 
		contentPanel.setHeight(80);
		
		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		HorizontalLayoutData hld = new HorizontalLayoutData();
		hlc.setBorders(false);
		Margins margins = new Margins();
		margins.setTop(0);
		margins.setLeft(0);
		margins.setRight(0);
		margins.setBottom(0);

		hlc.setWidth(3000);
		hlc.setHeight(100);
		hld.setMargins(margins);
		hlc.add(contentPanel, hld);

		return hlc;
		
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

	public static HBoxLayoutContainer FuncTextContents(String textHtml) {
		SafeHtml labelHtml = SafeHtmlUtils.fromTrustedString("<left><font color='#015ca3' font-family='Nanum Gothic' size='5'><p style='font-weight:bold;'>"+ textHtml +"</p></font>");
		LabelToolItem labelToolItem = new LabelToolItem(labelHtml);
		
		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
		hblc.add(labelToolItem, new BoxLayoutData(new Margins(30, 600, 5, 40)));
		
		return hblc;
	}
}