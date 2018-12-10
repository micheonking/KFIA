package myApp.client.vi.home;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.resource.ResourceIcon;
import myApp.client.vi.frm.Calendar;
import myApp.client.vi.frm.Monitoring_Page_Appr;
import myApp.client.vi.frm.Monitoring_Page_Outstanding;
import myApp.client.vi.frm.Monitoring_Page_ToDoList;
import myApp.client.vi.home.beginning.CoverPageBody;
import myApp.client.vi.home.beginning.CoverPageHeader;
import myApp.client.vi.home.beginning.CoverPageFooter;

public class TabBeginnigPage extends BorderLayoutContainer {
	
	private StartPage startPage;

	public TabBeginnigPage(StartPage startPage) {

		CoverPageHeader north = new CoverPageHeader();
		CoverPageBody center = new CoverPageBody();
		CoverPageFooter south = new CoverPageFooter(startPage);
		
		// center-north 이미지 사진
		BorderLayoutData northLayoutData = new BorderLayoutData(380);
		northLayoutData.setSplit(false); // 크기조절
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		northLayoutData.setSize(385);
//		northLayoutData.setMaxSize(700);
		this.setNorthWidget(north, northLayoutData);

		// center-center
		BorderLayoutData centerLayoutData = new BorderLayoutData(250);
		centerLayoutData.setSplit(false); // 크기조절
		centerLayoutData.setMargins(new Margins(0, 0, 0, 0));
//		centerLayoutData.setSize(100);
//		centerLayoutData.setMaxSize(700);
		this.setCenterWidget(center, centerLayoutData);

		// center-south
		BorderLayoutData southBorderLayoutData = new BorderLayoutData(220);
		southBorderLayoutData.setSplit(false); // 크기조절
		southBorderLayoutData.setMargins(new Margins(0, 0, 0, 0));
//		southBorderLayoutData.setSize(220);
//		southBorderLayoutData.setMaxSize(700);
		this.setSouthWidget(south, southBorderLayoutData);

	}
}