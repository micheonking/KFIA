package myApp.client.vi.home.company;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;

import myApp.client.resource.ResourceIcon;
import myApp.client.vi.home.StartPage;

public class CeoGreeting extends ContentPanel {
	
	public CeoGreeting() {

		this.setHeaderVisible(false);

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

//		Image lineBar0 = new Image(ResourceIcon.INSTANCE.lineBar());
		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());

		HBoxLayoutContainer gridHBox = new HBoxLayoutContainer();
		gridHBox.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
		
		HTML ceoimage = new HTML("<div><img src='img/ceo.jpg' width='217' height='267'></div>"); //인물사진

		VBoxLayoutContainer leftVBox = new VBoxLayoutContainer();
		leftVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		VBoxLayoutContainer rightVBox = new VBoxLayoutContainer();
		rightVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		HorizontalLayoutContainer totalHBar = new HorizontalLayoutContainer();
		HorizontalLayoutData hld = new HorizontalLayoutData();
		Margins margins = new Margins();
		margins.setTop(1);
		margins.setLeft(45);
		margins.setRight(30);
		margins.setBottom(5);
		hld.setMargins(margins);
		hld.setWidth(570);
//		totalHBar.setWidth(800);

	    
		Label content = new HTML("<font color='#606060' >"
				+	"<span style=\"font-weight:bold;font-size:1.2em;\">안녕하세요!</span><br>"
				+	"<br><span style=\"font-weight:bold;font-size:0.9em;\">"
				+	"먼저 한국채권투자자문 홈페이지를 찾아주신 데 대해 감사 드립니다.<br>"
				+	"저희 회사는 채권과 펀드에 특화된 투자자문회사로 투자일임업과 투자자문업을 영위하고 있습니다.<br>"
				+	"<br>"
				+	"자본시장이 발전하면서 투자관련 정교한 이론과 모델이 개발되고, 유가증권을 발행한 기업에 대한 공시의무가 강화되었습니다.<br>"
				+	"자본시장 종사자의 실력도 향상되어 이제는 내용을 몰라서 투자에 실패하는 일은 찾아보기 어렵습니다.<br>"
				+	"그럼에도 불구하고 관련 임직원들의 윤리의식 제고는 여전히 요구되고 있습니다.<br>"
				+	"<br>"
				+	"저희 회사는 CFA 또는 CFA3차 합격자만 펀드매니저가 될 수 있도록 함으로써 철저한 윤리의식을 강조하고 있습니다.<br>"
				+	"회사나 개인의 이익보다 고객의 이익을 먼저 고려하는 것은 펀드매니저의 기본자세입니다.<br>"
				+	"높은 윤리의식과 실력으로 자산운용분야에서 Global Top이 되도록 최선을 다하겠습니다. 고객 여러분들의 관심을 부탁 드립니다.<br>"
				+	"<br>"
				+	"감사합니다.<br>"
				+	"<br></span>"
				+	"<div><span style='font-weight:bold;font-size:1.2em;'>한국채권투자자문㈜ 대표이사 김형호 </span><img src='img/ceo_name.jpg' width='100' height='39'></div>"
					);
		
		content.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);

		Label education = new HTML("<font color='#606060' >"
					+	"<br>"
				 	+	"<img src='img/smallTitle.png'>"
				 	+	"<span style=\"font-weight:bold;\">  학력 및 자격사항</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍCFA</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍKDI School 자산운용 석사</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍ부산대학교 경제학과</span><br>"
				 	);
		
		Label career = new HTML("<font color='#606060' >"
					+	"<br>"
					+	"<img src='img/smallTitle.png'>"
					+	"<span style=\"font-weight:bold;\"> 주요이력 </span><br>"
					+	"<span style=\"font-size:0.8em;\">現) 한국채권투자자문 대표이사</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 아이투신 채권운용본부장</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 동양투신 채권운용본부장</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 조흥투신 채권운용팀장</span><br>"
					);
		
		gridVBox.add(StartPage.FuncTextContents("CEO 인사말"));
		gridVBox.add(lineBar0,new BoxLayoutData(new Margins(10, 0, 20, 40)));

		rightVBox.add(ceoimage, new BoxLayoutData(new Margins(5, 0, 0, 0)));
		rightVBox.add(education,new BoxLayoutData(new Margins(0, 0, 0, 0)));
		rightVBox.add(career,   new BoxLayoutData(new Margins(0, 0, 0, 0)));
		
		totalHBar.add(content,hld);
		totalHBar.add(rightVBox);

		gridVBox.add(totalHBar);

		this.add(gridVBox);

	}
}
