package myApp.client.vi.home.entrust;

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

public class EntrustInvestment extends ContentPanel{
	public interface HTMLTemplate extends XTemplates {
		//웹에디터 HTML 설정
		@XTemplate(source="invest.html")
		SafeHtml getTemplate();
	}
	
		public EntrustInvestment() {

			this.setHeaderVisible(false);

			VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
			gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
			gridVBox.setWidth(800);
			gridVBox.setHeight(1000);

			Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
			gridVBox.add(lineBar0,new BoxLayoutData(new Margins(0, 0, 0, 0)));

			VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
			rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
			
			HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
			HtmlLayoutContainer content = new HtmlLayoutContainer(htmlTemplate.getTemplate());

			HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
			totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
			totalHBar.add(content, new BoxLayoutData(new Margins(20, 0, 5, 45)));

			gridVBox.add(StartPage.FuncTextContents("투자일임"),new BoxLayoutData(new Margins(0,0,15,0)));
			gridVBox.add(lineBar0,new BoxLayoutData(new Margins(0, 0, 0, 40)));
//			gridVBox.add(mapImage,new BoxLayoutData(new Margins(20, 0, 0, 40)));
			gridVBox.add(totalHBar);

			this.add(gridVBox);
}
}
