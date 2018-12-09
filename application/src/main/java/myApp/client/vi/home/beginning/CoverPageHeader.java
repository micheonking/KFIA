package myApp.client.vi.home.beginning;

import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;

public class CoverPageHeader extends BorderLayoutContainer {

	// 메인 이미지 페이지
	public CoverPageHeader() {

		BorderLayoutData northLayoutData = new BorderLayoutData(10);
		HTML image = new HTML("<center><div> <img src='img/KFIAVisual.gif' style='border-bottom: 5px solid orange; ' width='1950' height='378'></div>");
		this.setNorthWidget(image, northLayoutData);

	}
}
