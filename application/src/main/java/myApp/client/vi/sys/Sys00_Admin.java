package myApp.client.vi.sys;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.MarginData;

public class Sys00_Admin extends ContentPanel {
	
	public Sys00_Admin() {

		this.setBorders(true);
//		this.setHeading("시스템 관리자");
		this.setBodyStyle("backgroundColor:#2b579a; color:#eeeeee");
		SafeHtml titleHtml = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#f2f2f2' style='font-size:18px;font-weight:bold'>&nbsp;&nbsp;시스템 관리자<br></font></div>");
		this.setHeading(titleHtml);
		this.setHeaderVisible(false);
//		this.setBodyStyle("backgroundColor:#5fa2dd; color:#eeeeee");
		
		
		PlainTabPanel tabPanel = new PlainTabPanel();
		
		Sys01_Tab_Company tabCompany = new Sys01_Tab_Company();
		tabPanel.add(tabCompany, "고객정보");

		Sys06_Tab_Menu tabMenu = new Sys06_Tab_Menu();
		tabPanel.add(tabMenu, "메뉴구성");
		
		Sys08_CodeKind tabCode = new Sys08_CodeKind();
		tabPanel.add(tabCode, "공통코드");
		
		this.add(tabPanel, new MarginData(3));
		
	}
}