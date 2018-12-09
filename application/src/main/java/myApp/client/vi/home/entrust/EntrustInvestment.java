package myApp.client.vi.home.entrust;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.info.Info;

public class EntrustInvestment extends VBoxLayoutContainer {

	public EntrustInvestment() {

//		this.setHeaderVisible(false);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		SelectHandler selectHandler = new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
			}
		};

		ContentPanel panelStart = new ContentPanel();
		// Align buttons to the start or left in ltr
//		panelStart.setHeaderVisible(false);
		panelStart.setHeading("Test Button");
		panelStart.setButtonAlign(BoxLayoutPack.END); // Left
		panelStart.setPosition(100, 100);
		panelStart.setHeading("Button Aligning — " + BoxLayoutPack.END);
		TextButton textButton1 = new TextButton("Button 1", selectHandler);
		TextButton textButton2 = new TextButton("Button 2", selectHandler);
		TextButton textButton3 = new TextButton("Button 3", selectHandler);
		textButton1.setWidth(100);
		textButton1.setHeight(70);
		textButton1.setBorders(true);

//		panelStart.setWidth(500);
//		panelStart.setHeight(200);
		panelStart.addButton(textButton1);
		panelStart.addButton(textButton2);
		panelStart.addButton(textButton3);

		panelStart.setBorders(true);

		BoxLayoutData flex = new BoxLayoutData(new Margins(10, 0, 10, 0));
		flex.setFlex(1);

//		this = new VBoxLayoutContainer(VBoxLayoutAlign.STRETCH);
		this.add(panelStart, flex);

//		this.add(panelStart, new MarginData(new Margins(1, 1, 1, 1)));
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}
}
