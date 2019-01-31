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
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;

public class Rpt05_Tab_TerminationAll extends VerticalLayoutContainer implements InterfaceGridOperate, InterfaceServiceCall {
	
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();
	private CommonComboBoxField fundComboBox = null;
	private CommonComboBoxField closeDateCombo = null;
	private String retrieveYn = "Y";
	private String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Rpt05_Tab_TerminationAll() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		//계좌
		Map<String, Object> fundFaram = new HashMap<String, Object>();
		fundFaram.put("userId", LoginUser.getUserId());
		fundComboBox = new CommonComboBoxField("cst.Cst02_Account.selectFundCodeList", fundFaram, new InterfaceCallback() {
			@Override
			public void execute() {
				fundComboBox.setText(LoginUser.getCstUserModel().getFundComboBoxName());
			}
		});
		fundComboBox.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				LoginUser.getCstUserModel().setFundCode(fundComboBox.getCode());
				LoginUser.getCstUserModel().setFundComboBoxName(fundComboBox.getText());
				reSetCloseDateComboBox(fundComboBox.getCode());
			}
		});
		FieldLabel fundCodeField = new FieldLabel(fundComboBox, "계좌 ");
		fundCodeField.setLabelWidth(50);
		fundCodeField.setWidth(350);
		fundComboBox.setWidth(300);
		fundComboBox.setValue(LoginUser.getCstUserModel().getFundComboBoxName());

		//해지일
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fundCode", LoginUser.getCstUserModel().getFundCode());
		closeDateCombo = new CommonComboBoxField("cst.Cst02_Account.selectCloseDateCombo", param, new InterfaceCallback() {
			@Override
			public void execute() {
				if(closeDateCombo.getCode() == null) {
					new SimpleMessage("확인", "전부해지 정산내역이 없습니다.");
					retrieveYn = "N";
				} else {
					retrieveYn = "Y";
				}
				retrieve();
			}
		});
		FieldLabel dateField = new FieldLabel(closeDateCombo, "해지일 ");
		dateField.setWidth(170);
		dateField.setLabelWidth(60);

		searchBarBuilder.getSearchBar().add(fundCodeField);
		searchBarBuilder.getSearchBar().add(dateField);
		searchBarBuilder.addRetrieveButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(1, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	private void reSetCloseDateComboBox(String fundCode) {
		Map<String, Object> Param = new HashMap<String, Object>();
		Param.put("fundCode", fundCode);
		closeDateCombo.retrieve("cst.Cst02_Account.selectCloseDateCombo", Param);
	}

	@Override
	public void retrieve() {
		if("Y".equals(retrieveYn)) {
			ServiceRequest request = new ServiceRequest("cst.Cst02_Account.getCloseDate");
			request.putStringParam("fundCode", LoginUser.getCstUserModel().getFundCode());
			request.putStringParam("ymd", closeDateCombo.getCode());
			ServiceCall service = new ServiceCall();
			service.execute(request, this);
		} else {
			rdSampleCall();
		}
	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() > 0) {
			String[] list = result.getMessage().split("\\|");
			String bf_ymd = list[0].toString();
			String af_ymd = list[1].toString();
			String bf_pay_ymd = list[2].toString();
			String af_pay_ymd = list[3].toString();
			rdCall(bf_ymd, af_ymd, bf_pay_ymd, af_pay_ymd); 
		} else {
			new SimpleMessage("확인", result.getMessage());
			rdSampleCall();
		}
	}

	private void rdCall(String bf_ymd, String af_ymd, String bf_pay_ymd, String af_pay_ymd) {
		
		String companyCode = LoginUser.getCstUserModel().getCompanyModel().getCompanyCode();
		String ymd = closeDateCombo.getCode();
		String fundCode = fundComboBox.getCode(); 
		if (fundCode == null) {
			new SimpleMessage("확인","계좌를 선택하여 주십시오.");
			rdSampleCall();
		} else {
			String pageNameAdd = this.pageName + "web_sja031.html?fund_cd=" + fundCode;
			pageNameAdd += "&ymd=" + ymd;
			pageNameAdd += "&corp_gr=" + companyCode;
			pageNameAdd += "&tr_cd=D44";
			pageNameAdd += "&bf_ymd=" + bf_ymd;
			pageNameAdd += "&af_ymd=" + af_ymd;
			pageNameAdd += "&bf_bosu_ymd=" + bf_pay_ymd;
			pageNameAdd += "&af_bosu_ymd=" + af_pay_ymd;
			
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