package myApp.client.vi.rpt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.field.MyDateField;
import myApp.client.grid.CommonComboBoxField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.service.InterfaceCallback;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;

public class Rpt04_Tab_BalanceAccounts extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceServiceCall {
	
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();
	CommonComboBoxField settleDateCombo = null;

	private String retrieveYn = "Y";
	private String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";
	
	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Rpt04_Tab_BalanceAccounts() {
		this.setBorders(false); 
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fundCode", LoginUser.getCstUserModel().getFundCode());
		settleDateCombo = new CommonComboBoxField("cst.Cst02_Account.selectSettlementDateCombo", param, new InterfaceCallback() {
			@Override
			public void execute() {
				if(settleDateCombo.getCode() == null) {
					new SimpleMessage("확인", "결산정산내역이 없습니다.");
					retrieveYn = "N";
				} else {
					retrieveYn = "Y";
				}
				retrieve();
			}
		});
		FieldLabel dateField = new FieldLabel(settleDateCombo, "결산일");
		dateField.setWidth(170);
		dateField.setLabelWidth(55);
		searchBarBuilder.getSearchBar().add(dateField);
		
		searchBarBuilder.addRetrieveButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(1, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	@Override
	public void retrieve() {
		if("Y".equals(retrieveYn)) {
			ServiceRequest request = new ServiceRequest("cst.Cst02_Account.getSettlementDate");
			request.putStringParam("fundCode", LoginUser.getCstUserModel().getFundCode());
			request.putStringParam("ymd", settleDateCombo.getCode());
			ServiceCall service = new ServiceCall();
			service.execute(request, this);
		} else {
			rdSampleCall();
		}
	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() > 0) {
			String bf_ymd = result.getMessage().substring(0, 10);
			String af_ymd = result.getMessage().substring(10, 20);
			rdCall(bf_ymd, af_ymd);
		} else {
			new SimpleMessage("확인", result.getMessage());
			rdSampleCall();
		}
	}

	private void rdCall(String bf_ymd, String af_ymd) {
		
		String companyCode = LoginUser.getCstUserModel().getCompanyModel().getCompanyCode();
		String fundCode = LoginUser.getCstUserModel().getFundCode(); 
		String ymd = settleDateCombo.getCode();
		if (fundCode == null) {
			new SimpleMessage("확인","계좌를 선택하여 주십시오.");
			rdSampleCall();
		} else {
			String pageNameAdd = this.pageName + "web_sja020.html?fund_cd=" + fundCode;
			pageNameAdd += "&ymd=" + ymd;
			pageNameAdd += "&corp_gr=" + companyCode;
			pageNameAdd += "&tr_cd=D41";
			pageNameAdd += "&bf_ymd=" + bf_ymd;
			pageNameAdd += "&af_ymd=" + af_ymd;

			RDTemplate rdTemplate = GWT.create(RDTemplate.class);
			HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageNameAdd));
			rdLayoutContainer.clear();
			rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1, 1));
			
			this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
		}
	}

	private void rdSampleCall() {
		String pageNameAdd = this.pageName + "sample.html";
		RDTemplate rdTemplate = GWT.create(RDTemplate.class);
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageNameAdd));
		rdLayoutContainer.clear();
		rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1, 1));
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