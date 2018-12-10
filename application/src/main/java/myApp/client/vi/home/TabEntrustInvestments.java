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

public class TabEntrustInvestments extends ContentPanel {

	private EntrustInvestment tabEntrustInvestment  = new EntrustInvestment();
	
	ContentPanel contentPanel  = new ContentPanel();

	public TabEntrustInvestments() {

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

		headerVBox.add(StartPage.getLabelToolItem("투자일임"));

		menuVBox.add(lineImage0, new BoxLayoutData(new Margins(20, 0, 0, 0)));
		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<div style='background-color: #ffffff;'><font color='#606060' style='font-size:14px;'>ㆍ투자일임안내　　　　</font></div> ");
		CellButtonBase mainButton1 = new CellButtonBase<>();
		mainButton1.setSize("200", "40");
		mainButton1.setHTML(button1Html);
//		mainButton1.setBorders(true);
		mainButton1.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				getEntrustInvestment();
			}
		});
		menuVBox.add(mainButton1, new BoxLayoutData(new Margins(1, 3, 1, 3)));
		menuVBox.add(lineImage1, new BoxLayoutData(new Margins(0, 0, 0, 0)));

		menuVBox.setWidth(210);
		menuVBox.setHeight(600);
		menuHBar.add(menuVBox, boxLayoutData);

		totalHBar.add(menuHBar);
		totalHBar.add(getEntrustInvestment());
		centerVBox.add(totalHBar);

		headerVBox.add(centerVBox);
		this.add(headerVBox);

	}

	private ContentPanel getEntrustInvestment() {
		
		contentPanel.setHeaderVisible(false);
		contentPanel.setBorders(false);
		contentPanel.setWidth(880);
		contentPanel.setHeight(700);
		contentPanel.setWidget(tabEntrustInvestment);

		return contentPanel;
	}

}
