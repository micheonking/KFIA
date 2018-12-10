package myApp.client.vi.home.product;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.XTemplate;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.StartPage;
import myApp.client.vi.home.entrust.EntrustInvestment.HTMLTemplate;

public class GlobalFund extends ContentPanel {
	public interface HTMLTemplate extends XTemplates {
		//웹에디터 HTML 설정
		@XTemplate(source="globalFund.html")
		SafeHtml getTemplate();
	}
	
		public GlobalFund() {

			this.setHeaderVisible(false);

			VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
			gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
			gridVBox.setWidth(800);
			gridVBox.setHeight(1000);


//			HTML mapImage = new HTML("<img src='img/org_bg.jpg' width='656' height='440'>"); //조직도
			
			Image verticalTitle = new Image(ResourceIcon.INSTANCE.verticalTitle());
			gridVBox.add(verticalTitle,new BoxLayoutData(new Margins(40, 40,40, 40)));

			VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
			rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
			
			HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
			HtmlLayoutContainer content = new HtmlLayoutContainer(htmlTemplate.getTemplate());

			HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
			totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
			totalHBar.add(content, new BoxLayoutData(new Margins(20, 0, 5, 45)));
			
			gridVBox.add(StartPage.getTextContents("글로벌 펀드일임 투자"),new BoxLayoutData(new Margins(0, 0, 15, 0)));
			gridVBox.add(verticalTitle,new BoxLayoutData(new Margins(0, 0, 0, 40)));
			gridVBox.add(totalHBar);

			this.add(gridVBox);
	}
}
