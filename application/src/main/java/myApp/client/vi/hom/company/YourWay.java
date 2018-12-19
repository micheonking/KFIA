package myApp.client.vi.hom.company;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.XTemplate;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.hom.StartPage;
import myApp.client.vi.hom.company.CompanyOpening.HTMLTemplate;

public class YourWay extends ContentPanel {
	
	public interface HTMLTemplate extends XTemplates {
		//웹에디터 HTML 설정
//		@XTemplate(source="yourWay.html")
//		SafeHtml getTemplate();
//	    @XTemplate("<iframe id='yourWay' frameborder=0 src='{pageName}' width='770' height='{pageHeight}'/> ")
	    @XTemplate("<iframe src='{pageName}' align= left marginheight=1 style='border:0px; width:97%; height:100%;'/> ")
		SafeHtml getTemplate(String pageName);
	}
	
	public YourWay() {
		this.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();
			}
		});
		resize();
	}
	
	private void resize() {
//	public YourWay() {

		this.setHeaderVisible(false);
//		this.isAutoHeight();
		HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
		String pageName = "yourWay.html";

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
		
		Margins getTextMargins = new Margins(0, 0, 15, 0);
		Margins totalHBarMargins = new Margins(5, 0, 5, 30);
		Margins lineBar0Margins = new Margins(10, 0, 20, 30);

		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());

		VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
		rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
		
		
//		HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
//		HtmlLayoutContainer content = new HtmlLayoutContainer(htmlTemplate.getTemplate());
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.getTemplate(pageName));

		htmlLayoutContainer.setWidth(StartPage.CURRENTWIDTH);
		StartPage.CURRENTHEIGHT = Window.getClientHeight()-300;//StartPage.startPageFooter.getAbsoluteTop() + 70;
//		Info.display("",""+StartPage.CURRENTHEIGHT);
		htmlLayoutContainer.setHeight(StartPage.CURRENTHEIGHT); //600 - StartPage.CURRENTHEIGHT);

		HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
		totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
		totalHBar.add(htmlLayoutContainer, new BoxLayoutData(totalHBarMargins));
		
		gridVBox.add(StartPage.getTextContents("찾아오시는길"),new BoxLayoutData(getTextMargins));
		gridVBox.add(lineBar0,new BoxLayoutData(lineBar0Margins));
		gridVBox.add(totalHBar);
		this.add(gridVBox);

	}
}
