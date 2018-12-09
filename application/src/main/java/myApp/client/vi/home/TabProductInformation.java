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
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.entrust.EntrustInvestment;
import myApp.client.vi.home.product.GlobalFund;
import myApp.client.vi.home.product.SmartPrivateBond;
import myApp.client.vi.home.product.SmartHeild;

public class TabProductInformation extends ContentPanel {

	private SmartHeild tabSmartHeild  = new SmartHeild();
	private SmartPrivateBond tabSmartPrivateBond  = new SmartPrivateBond();
	private GlobalFund tabGlobalFund  = new GlobalFund();
	
	ContentPanel contentPanel  = new ContentPanel();

	public TabProductInformation() {

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

		headerVBox.add(StartPage.FuncLabelToolItem("상품안내"));

		menuVBox.add(lineImage0, new BoxLayoutData(new Margins(20, 0, 0, 0)));
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ스마트 하이일드　　 </font></div> ");
		SafeHtml button2Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ스마트 사모채권　　 </font></div> ");
		SafeHtml button3Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ글로벌 펀드　　　　 </font></div> ");
		CellButtonBase mainButton1 = new CellButtonBase<>();
		mainButton1.setSize("200", "40");
		mainButton1.setHTML(button1Html);
//		mainButton1.setBorders(true);
		mainButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getSmartHeild();
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
				getSmartPrivateBond();
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
				getGlobalFund();
			}
		});
		menuVBox.add(mainButton3, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage3, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		menuVBox.setWidth(210);
		menuVBox.setHeight(600);
		menuHBar.add(menuVBox, boxLayoutData);

		totalHBar.add(menuHBar);
		totalHBar.add(getSmartHeild());
		centerVBox.add(totalHBar);

		headerVBox.add(centerVBox);
		this.add(headerVBox);

	}

	private ContentPanel getSmartHeild() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabSmartHeild);

		return contentPanel;
	}

	private ContentPanel getSmartPrivateBond() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabSmartPrivateBond);

		return contentPanel;
	}

	private ContentPanel getGlobalFund() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabGlobalFund);

		return contentPanel;
	}
}