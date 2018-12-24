package myApp.client.vi;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class TabBackGround extends ContentPanel {
	
	public TabBackGround() {
		this.setVisible(false);
		this.setHeaderVisible(false);
		SafeHtml logoHtml = SafeHtmlUtils.fromTrustedString("<div><img src='img/_KFIALogin.png' width='500' height='128'></img></div>");
		LabelToolItem labelToolItem = new LabelToolItem(logoHtml);

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.RIGHT);
//		Info.display("", ""+Window.getClientHeight());
		gridVBox.add(labelToolItem, new BoxLayoutData(new Margins(Window.getClientHeight()-300,60,40,10)));

		this.add(gridVBox);
	}
}