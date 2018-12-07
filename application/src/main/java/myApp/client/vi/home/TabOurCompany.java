package myApp.client.vi.home;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.company.TabAboutUsCeo;
import myApp.client.vi.home.company.TabAboutUsCompany;
import myApp.client.vi.home.company.TabAboutUsHistory;
import myApp.client.vi.home.company.TabAboutUsMap;

public class TabOurCompany extends ContentPanel {

	private TabAboutUsCeo tabAboutUsCeo  = new TabAboutUsCeo();
	private TabAboutUsCompany tabAboutUsCompany  = new TabAboutUsCompany();
	private TabAboutUsHistory tabAboutUsHistory  = new TabAboutUsHistory();
	private TabAboutUsMap tabAboutUsMap  = new TabAboutUsMap();
	
	ContentPanel contentPanel  = new ContentPanel();

	public TabOurCompany() {

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

		headerVBox.add(MainFramePage.FuncLabelToolItem("회사소개"));

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
				getAboutUsCeo();
			}
		});
		menuVBox.add(mainButton1, new BoxLayoutData(new Margins(1, 3, 1, 3)));
//		menuVBox.add(lineImage1, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton2 = new CellButtonBase<>();
		mainButton2.setSize("200", "40");
		mainButton2.setHTML(button2Html);
//		mainButton2.setBorders(true);
		mainButton2.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getAboutUsCompany();
			}
		});
		menuVBox.add(mainButton2, new BoxLayoutData(new Margins(1, 3, 1, 3)));
//		menuVBox.add(lineImage2, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton3 = new CellButtonBase<>();
		mainButton3.setSize("200", "40");
		mainButton3.setHTML(button3Html);
//		mainButton3.setBorders(true);
		mainButton3.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getAboutUsHistory();
			}
		});
		menuVBox.add(mainButton3, new BoxLayoutData(new Margins(1, 3, 1, 3)));
//		menuVBox.add(lineImage3, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		CellButtonBase mainButton4 = new CellButtonBase<>();
		mainButton4.setSize("200", "40");
		mainButton4.setHTML(button4Html);
//		mainButton4.setBorders(true);
		mainButton4.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getAboutUsMap();
			}
		});
		menuVBox.add(mainButton4, new BoxLayoutData(new Margins(1, 3, 1, 3)));
//		menuVBox.add(lineImage4, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		menuVBox.setWidth(210);
		menuVBox.setHeight(1000);
//		menuVBox.setBorders(true);

		menuHBar.add(menuVBox, boxLayoutData);
//		menuHBar.setBorders(true);

		totalHBar.add(menuHBar);
		totalHBar.add(getAboutUsCeo());

		centerVBox.add(totalHBar);

		headerVBox.add(centerVBox);
		this.add(headerVBox);

	}

	private ContentPanel getAboutUsCeo() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(1000);
		contentPanel.setWidget(tabAboutUsCeo);

		return contentPanel;
	}

	private ContentPanel getAboutUsCompany() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(800);
		contentPanel.setHeight(1000);
		contentPanel.setWidget(tabAboutUsCompany);

		return contentPanel;
	}
	
	private ContentPanel getAboutUsHistory() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(800);
		contentPanel.setHeight(1000);
		contentPanel.setWidget(tabAboutUsHistory);

		return contentPanel;
	}

	private ContentPanel getAboutUsMap() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(800);
		contentPanel.setHeight(1000);
		contentPanel.setWidget(tabAboutUsMap);

		return contentPanel;
	}

}