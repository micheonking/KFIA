package myApp.client.vi.home.product;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.StartPage;

public class SmartPrivateBond extends ContentPanel {

	public interface HTMLTemplate extends XTemplates {
		//웹에디터 HTML 설정
		@XTemplate(source="smartPrivateBond.html")
		SafeHtml getTemplate();
	}
	
	public SmartPrivateBond() {

		this.setHeaderVisible(false);

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());

		VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
		rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
		totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
		
		
		HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
		HtmlLayoutContainer content = new HtmlLayoutContainer(htmlTemplate.getTemplate());
//
		totalHBar.add(content, new BoxLayoutData(new Margins(20, 0, 5, 45)));
		gridVBox.add(StartPage.FuncTextContents("스마트 사모 채권"),new BoxLayoutData(new Margins(0, 0, 15, 0)));
		gridVBox.add(lineBar0,new BoxLayoutData(new Margins(0, 0, 0, 40)));
		gridVBox.add(totalHBar);

		this.add(gridVBox);

	}
}
