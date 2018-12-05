package myApp.client.vi.home.front;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;

public class BodyAreaCenter extends BorderLayoutContainer {

	// 업무현황 버튼 3개
	public BodyAreaCenter() {

//		FirmHeadTitle titleLeft = new FirmHeadTitle();
		FirmHeadLeft west = new FirmHeadLeft();
		FirmHeadMiddle center = new FirmHeadMiddle();
		FirmHeadRight east = new FirmHeadRight();

		VBoxLayoutContainer centerVBox = new VBoxLayoutContainer();
		centerVBox.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);
//		centerVBox.setWidth(1200);
//		centerVBox.setHeight(70);

		SafeHtml label1Html = SafeHtmlUtils.fromTrustedString(	"<center>"
				+	"<div style='background-color: #1d7bbb; line-height:130%; '>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<div><img src='img/icon_left.png' width='32' height='40'></div>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<span style='font-weight:normal; font-size:1.2em;'>"
				+	"<font color='#eeeeee'>총 운용규모<br>약 3조 1,426억</font></span>"
				+	"<span style='font-size:0.1em;'><br><br></span>"
				+	"</div>"
				);
		LabelToolItem labelToolItem1 = new LabelToolItem(label1Html);
		labelToolItem1.setWidth(300);
		labelToolItem1.setHeight(130);
		labelToolItem1.setBorders(true);

		SafeHtml label2Html = SafeHtmlUtils.fromTrustedString(	"<center>"
				+	"<div style='background-color: #349da0; line-height:130%; '>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<div><img src='img/icon_middle.png' width='40' height='40'></div>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<span style='font-weight:normal; font-size:1.2em;'>"
				+	"<font color='#eeeeee'>자문형<br>약 2조 5,132억</font></span>"
				+	"<span style='font-size:0.1em;'><br><br></span>"
				+	"</div>"
				);
		LabelToolItem labelToolItem2 = new LabelToolItem(label2Html);
		labelToolItem2.setWidth(300);
		labelToolItem2.setHeight(130);
		labelToolItem2.setBorders(true);

		HBoxLayoutContainer boxHBox = new HBoxLayoutContainer();
		boxHBox.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);


		SafeHtml label3Html = SafeHtmlUtils.fromTrustedString(	"<center>"
				+	"<div style='background-color: #42339c; line-height:130%; '>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<div><img src='img/icon_right.png' width='40' height='40'></div>"
				+	"<span style='font-size:0.1em;'><br></span>"
				+	"<span style='font-weight:normal; font-size:1.2em;'>"
				+	"<font color='#eeeeee'>일임형<br>약 6,294억</font></span>"
				+	"<span style='font-size:0.1em;'><br><br></span>"
				+	"</div>"
				);
		LabelToolItem labelToolItem3 = new LabelToolItem(label3Html);
		labelToolItem3.setWidth(300);
		labelToolItem3.setHeight(130);
		labelToolItem3.setBorders(true);

		boxHBox.add(labelToolItem1, new BoxLayoutData(new Margins(10, 5, 5, 5)));
		boxHBox.add(labelToolItem2, new BoxLayoutData(new Margins(10, 5, 5, 5)));
		boxHBox.add(labelToolItem3, new BoxLayoutData(new Margins(10, 5, 5, 5)));

		SafeHtml button1Html = SafeHtmlUtils.fromTrustedString("<font color='#606060' style='font-weight:bold; font-size:18px;'>운용현황</font> ");
		Image verticalLine = new Image(ResourceIcon.INSTANCE.verticalLine());

		centerVBox.add(new LabelToolItem(button1Html), new BoxLayoutData(new Margins(15, 850, 0, 1)));
		centerVBox.add(verticalLine, new BoxLayoutData(new Margins(5, 1, 1, 0)));
		centerVBox.add(boxHBox, new BoxLayoutData(new Margins(0, 1, 1, 1)));
		this.add(centerVBox);

//		boxHBox.add(west, new BoxLayoutData(new Margins(0, 0, 0, 0)));
//		boxHBox.add(center, new BoxLayoutData(new Margins(0, 0, 0, 0)));
//		boxHBox.add(east, new BoxLayoutData(new Margins(0, 0, 0, 0)));
//		
//		west.setBorders(true);
//		center.setBorders(true);
//		east.setBorders(true);
//		boxHBox.setBorders(true);
//		centerVBox.add(boxHBox, new BoxLayoutData(new Margins(1, 1, 1, 1)));
////		centerVBox.setHeight(100);
//		centerVBox.setBorders(true);
//		
//		this.add(centerVBox);


//		HorizontalLayoutData rowLayout = new HorizontalLayoutData(0.33333, 210, new Margins(0, 0, 0, 0));
//		rowLayout.setWidth(300);
//		rowLayout.setHeight(140);

//		HorizontalLayoutContainer row00 = new HorizontalLayoutContainer();
//		row00.add(titleLeft, rowLayout);
//		row00.add(new LabelToolItem(""), rowLayout);
//		row00.add(new LabelToolItem(""), rowLayout);

//		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
//		row01.add(west, rowLayout);
//		row01.add(center, rowLayout);
//		row01.add(east, rowLayout);
//
//		VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
////		layoutContainer.add(row00, new VerticalLayoutData(1, -1, new Margins(5, 0, 5, 0)));
//		layoutContainer.add(row01, new VerticalLayoutData(1, -1, new Margins(20, 0, 5, 0)));
//
//		layoutContainer.setWidth(1200);
//		layoutContainer.setHeight(140);
//
//		this.add(layoutContainer);

	}
}
