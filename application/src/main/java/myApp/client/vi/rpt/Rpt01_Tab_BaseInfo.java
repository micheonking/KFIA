package myApp.client.vi.rpt;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.service.InterfaceCallback;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;

public class Rpt01_Tab_BaseInfo extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();
	private CommonComboBoxField fundComboBox = null;

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Rpt01_Tab_BaseInfo() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", LoginUser.getUserId());
		fundComboBox = new CommonComboBoxField("cst.Cst02_Account.selectFundCodeList", param, new InterfaceCallback() {
			@Override
			public void execute() {
				fundComboBox.setText(LoginUser.getCstUserModel().getFundComboBoxName());
				retrieve();
			}
		});
		fundComboBox.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				LoginUser.getCstUserModel().setFundCode(fundComboBox.getCode());
				LoginUser.getCstUserModel().setFundComboBoxName(fundComboBox.getText());
			}
		});
		FieldLabel fundCodeField = new FieldLabel(fundComboBox, "계좌 ");
		fundCodeField.setLabelWidth(50);
		fundCodeField.setWidth(350);
		fundComboBox.setWidth(300);
		fundComboBox.setValue(LoginUser.getCstUserModel().getFundComboBoxName());

		searchBarBuilder.getSearchBar().add(fundCodeField);
		searchBarBuilder.addRetrieveButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(1, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	@Override
	public void retrieve() {

		String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";
		String companyCode = LoginUser.getCstUserModel().getCompanyModel().getCompanyCode();
		String fundCode = fundComboBox.getCode();
		if (fundCode == null) {
			new SimpleMessage("확인","계좌를 선택하여 주십시오.");
			pageName += "sample.html";
		} else {
			pageName += "web_cs_info.html?fund_cd="+fundCode;
			pageName += "&corp_gr="+companyCode;
		}
		
		RDTemplate rdTemplate = GWT.create(RDTemplate.class);
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));
		rdLayoutContainer.clear();
		rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1,1));

		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	@Override
	public void update() {
	}
	@Override
	public void insertRow() {
	}
	@Override
	public void deleteRow() {
	}
}