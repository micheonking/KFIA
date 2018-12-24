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
import myApp.client.vi.hom.company.OperatingOrganization.HTMLTemplate;

public class CompanyOpening extends ContentPanel {
	
	public interface HTMLTemplate extends XTemplates {
//		//웹에디터 HTML 설정
//		@XTemplate(source="companyOpening.html")
		//웹에디터 HTML 설정
//	    @XTemplate("<iframe id='companyOpening' frameborder=0 src='{pageName}' width='770' height='{pageHeight}'/> ")
	    @XTemplate("<iframe src='{pageName}' align= left marginheight=1 style='border:0px; width:100%; height:97%;'/> ")
		SafeHtml getTemplate(String pageName);
//		SafeHtml getTemplate();
	}
	
	public CompanyOpening() {
		this.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				resize();
			}
		});
		resize();
	}
	
	private void resize() {
//	public CompanyOpening() {

		this.setHeaderVisible(false);
//		this.isAutoHeight();
		HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
		String pageName = "companyOpening.html";

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		Margins getTextMargins = new Margins(0, 0, 15, 0);
		Margins totalHBarMargins = new Margins(5, 0, 5, 30);
		Margins lineBar0Margins = new Margins(10, 0, 20, 30);

		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());

		VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
		rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		HBoxLayoutContainer totalHBar = new HBoxLayoutContainer();
		totalHBar.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
		
		
//		HTMLTemplate htmlTemplate = GWT.create(HTMLTemplate.class);
//		HtmlLayoutContainer content = new HtmlLayoutContainer(htmlTemplate.getTemplate());
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.getTemplate(pageName));
		
//		Label content = new HTML("
//					);
//		content.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
//
		htmlLayoutContainer.setWidth(StartPage.CURRENTWIDTH);
		StartPage.CURRENTHEIGHT = Window.getClientHeight()-300;//StartPage.startPageFooter.getAbsoluteTop() + 70;
//		Info.display("",""+StartPage.CURRENTHEIGHT);
		htmlLayoutContainer.setHeight(StartPage.CURRENTHEIGHT); //600 - StartPage.CURRENTHEIGHT);
		totalHBar.add(htmlLayoutContainer, new BoxLayoutData(totalHBarMargins));

		gridVBox.add(StartPage.getTextContents("회사개요"),new BoxLayoutData(getTextMargins));
		gridVBox.add(lineBar0,new BoxLayoutData(lineBar0Margins));
		gridVBox.add(totalHBar);

		this.add(gridVBox);

	}
}
