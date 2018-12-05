package myApp.client.vi.home;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.util.Margins;
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

	private MainNorthArea mainNorthArea = new MainNorthArea();
	private MainSouthArea mainSouthArea = new MainSouthArea();
	public static PlainTabPanel tabPanel = new PlainTabPanel();

	public MainFramePage() {

		this.setBorders(false);

		BorderLayoutData northLayoutData = new BorderLayoutData(70);
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		this.setNorthWidget(mainNorthArea, northLayoutData);
		northLayoutData.setSplit(true);
		
		BorderLayoutData southLayoutData = new BorderLayoutData(70);
		southLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		this.setSouthWidget(mainSouthArea, southLayoutData);
//		southLayoutData.setSplit(true);
		
		BorderLayoutData centerLayoutData = new BorderLayoutData(250);
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		centerLayoutData.setSplit(true);

		tabPanel.setBorders(false);
		tabPanel.setBodyBorder(true);

		tabPanel.setHideMode(HideMode.DISPLAY);
		tabPanel.setTabScroll(false);
		
		tabPanel.add(new TabFrontPage(), "");

		TabOurCompany tabAboutUs = new TabOurCompany();
		tabPanel.add(tabAboutUs, "회사소개");

		TabOurInvestment tabBusiness = new TabOurInvestment();
		tabPanel.add(tabBusiness, "투자일임");
		
		TabOurProduct tabSolution = new TabOurProduct();
		tabPanel.add(tabSolution, "상품안내");
		
		TabOurNews tabNews = new TabOurNews();
		tabPanel.add(tabNews, "KFIA소식");
		
//		this.add(tabPanel, new MarginData(3));
		this.setCenterWidget(tabPanel, centerLayoutData);
		
	}

	public static void openTabPage(TabPanel tabPanel, String pageName) {
		//
		Widget tabPage = tabPanel.findItem(pageName, true);
		tabPanel.setActiveWidget(tabPage);

	}
	
	public static HorizontalLayoutContainer FuncLabelToolItem(String textHtml) {
		// TODO Auto-generated constructor stub  023d69
		SafeHtml label1Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:130%; '>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"<span style='font-weight:bold; font-size:2.3em;'>"
															+	"<font color='#ffffff'><br></font></span>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"</div>"
															);
		LabelToolItem labelToolItem1 = new LabelToolItem(label1Html);
		labelToolItem1.setWidth(460);
		labelToolItem1.setHeight(65);

		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:130%; '>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"<span style='font-weight:bold; font-size:2.3em;'>"
															+	"<font color='#ffffff'>" + textHtml + "<br></font></span>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"</div>"
															);

//		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString("<left><font color='#ffffff' style='font-size:32px;font-weight:bold;'><p style='background-color: #023d69;'>"+ textHtml +"</p></font>");
		LabelToolItem labelToolItem2 = new LabelToolItem(label2Html);
		labelToolItem2.setWidth(200);
		labelToolItem2.setHeight(65);

//		SafeHtml label3Html = SafeHtmlUtils.fromTrustedString("<left><font color='#ffffff' style='font-size:32px;font-weight:bold;'><p style='background-color: #023d69;'><br><br>　<br><br></p></font>");
		SafeHtml label3Html = SafeHtmlUtils.fromTrustedString(	"<div style='background-color: #023d69; line-height:130%; '>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"<span style='font-weight:bold; font-size:2.3em;'>"
															+	"<font color='#ffffff'><br></font></span>"
															+	"<span style='font-size:0.1em;'><br></span>"
															+	"</div>"
															);
		LabelToolItem labelToolItem3 = new LabelToolItem(label3Html);
		labelToolItem3.setWidth(1340);
		labelToolItem3.setHeight(65);

		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
		HorizontalLayoutData hld = new HorizontalLayoutData();
		hlc.setBorders(false);
		Margins margins = new Margins();
		margins.setTop(0);
		margins.setLeft(0);
		margins.setRight(0);
		margins.setBottom(0);

		hlc.setWidth(2000);
		hlc.setHeight(65);
		hld.setMargins(margins);
		hlc.add(labelToolItem1, hld);
		hlc.add(labelToolItem2, hld);
		hlc.add(labelToolItem3, hld);
		
		return hlc;
	}

	public static HBoxLayoutContainer FuncTextContents(String textHtml) {
		SafeHtml labelHtml = SafeHtmlUtils.fromTrustedString("<left><font color='#015ca3' font-family='Nanum Gothic' size='5'><p style='font-weight:bold;'>"+ textHtml +"</p></font>");
		LabelToolItem labelToolItem = new LabelToolItem(labelHtml);
		
		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.setHBoxLayoutAlign(HBoxLayoutAlign.BOTTOM);
		hblc.add(labelToolItem, new BoxLayoutData(new Margins(30, 600, 2, 40)));
		
		return hblc;
	}
}