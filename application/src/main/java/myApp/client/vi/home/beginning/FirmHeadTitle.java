package myApp.client.vi.home.beginning;

import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

public class FirmHeadTitle extends BorderLayoutContainer {

	public FirmHeadTitle() {

		HorizontalLayoutData rowLayout = new HorizontalLayoutData(1, 70, new Margins(0, 0, 0, 0));
		// 1번 버튼

		HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
		row00.add(new LabelToolItem(""), rowLayout);
		row00.add(new LabelToolItem(""), rowLayout);
		row00.add(new HTML("<left><p style=\"font-family:나눔고딕; font-weight:bold;\">업무현황</p>"), rowLayout);

		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
		layoutContainer.add(row00, new VerticalLayoutData(1, -1, new Margins(5, 10, 5, 10)));
		layoutContainer.setBorders(true);
		this.add(layoutContainer);

		
//		CeoGreeting
//		CompanyOpening
//		OperationOrganizationAndProfessionalPersonnel
//		YourWay
	}
}
