package myApp.client.vi.rpt;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.grid.CommonComboBoxField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.service.InterfaceCallback;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;

public class Rpt03_Tab_EntrustInvestment extends VerticalLayoutContainer implements InterfaceGridOperate {

	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();
	private CommonComboBoxField fundComboBox = null;
	private CommonComboBoxField yearComboBox = null;
	private String mmdd = null;
	private Radio radio1 = new Radio();
	private Radio radio2 = new Radio();
	private Radio radio3 = new Radio();
	private Radio radio4 = new Radio();

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Rpt03_Tab_EntrustInvestment() {
 
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		//계좌
		Map<String, Object> fundFaram = new HashMap<String, Object>();
		fundFaram.put("userId", LoginUser.getUserId());
		fundComboBox = new CommonComboBoxField("cst.Cst02_Account.selectFundCodeList", fundFaram, new InterfaceCallback() {
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
				reSetYearComboBox(fundComboBox.getCode());
			}
		});
		FieldLabel fundCodeField = new FieldLabel(fundComboBox, "계좌 ");
		fundCodeField.setLabelWidth(50);
		fundCodeField.setWidth(350);
		fundComboBox.setWidth(300);
		fundComboBox.setValue(LoginUser.getCstUserModel().getFundComboBoxName());

		//년도
		Map<String, Object> yearParam = new HashMap<String, Object>();
		yearParam.put("fundCode", LoginUser.getCstUserModel().getFundCode());
		yearComboBox = new CommonComboBoxField("cst.Cst02_Account.selectYearCombo", yearParam, new InterfaceCallback() {
			@Override
			public void execute() {
				retrieve();
			}
		});
		FieldLabel yearField = new FieldLabel(yearComboBox, "년도 ");
		yearField.setWidth(150);
		yearField.setLabelWidth(50);

		//분기
		radio1.setBoxLabel("1분기");
		radio2.setBoxLabel("2분기");
		radio3.setBoxLabel("3분기");
		radio4.setBoxLabel("4분기");

		String month = DateTimeFormat.getFormat("MM").format(LoginUser.getToday());
		switch (month) {
			case "01":
			case "02":
			case "03":
				radio1.setValue(false);radio2.setValue(false);radio3.setValue(false);radio4.setValue(true);
				mmdd = ".12.31"; 
				break;
			case "04":
			case "05":
			case "06":
				radio1.setValue(true);radio2.setValue(false);radio3.setValue(false);radio4.setValue(false);
				mmdd = ".03.31";
				break;
			case "07":
			case "08":
			case "09":
				radio1.setValue(false);radio2.setValue(true);radio3.setValue(false);radio4.setValue(false);
				mmdd = ".06.30";
				break;
			case "10":
			case "11":
			case "12":
				radio1.setValue(false);radio2.setValue(false);radio3.setValue(true);radio4.setValue(false);
				mmdd = ".09.30";
				break;
		}

		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.add(radio1);
		toggleGroup.add(radio2);
		toggleGroup.add(radio3);
		toggleGroup.add(radio4);
		toggleGroup.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
			@Override
			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
				ToggleGroup group = (ToggleGroup)event.getSource();
				Radio radio = (Radio)group.getValue();
				if(radio.getBoxLabel().toString().indexOf("1분기") > 0) {
					mmdd = ".03.31";
				}
				else if(radio.getBoxLabel().toString().indexOf("2분기") > 0) {
					mmdd = ".06.30";
				}
				else if(radio.getBoxLabel().toString().indexOf("3분기") > 0) {
					mmdd = ".09.30";
				}
				else if(radio.getBoxLabel().toString().indexOf("4분기") > 0) {
					mmdd = ".12.31";
				}
			}
		});

		HorizontalPanel hbox = new HorizontalPanel();
		hbox.add(radio1);
		hbox.add(radio2);
		hbox.add(radio3);
		hbox.add(radio4);
		FieldLabel radioField = new FieldLabel(hbox, " ");
		radioField.setLabelSeparator(" ");
		radioField.setWidth(300);
		radioField.setLabelWidth(10);
		radioField.setHeight(30);

		searchBarBuilder.getSearchBar().add(fundCodeField);
		searchBarBuilder.getSearchBar().add(yearField);
		searchBarBuilder.getSearchBar().add(radioField);
		searchBarBuilder.addRetrieveButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(1, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	private void reSetYearComboBox(String fundCode) {
		Map<String, Object> yearParam = new HashMap<String, Object>();
		yearParam.put("fundCode", fundCode);
		yearComboBox.retrieve("cst.Cst02_Account.selectYearCombo", yearParam);
	}

	@Override
	public void retrieve() {
		String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";
		String companyCode = LoginUser.getCstUserModel().getCompanyModel().getCompanyCode();
		String fundCode = fundComboBox.getCode(); 
		String ymd = yearComboBox.getCode() + mmdd;
		if (fundCode == null) {
			new SimpleMessage("확인","계좌를 선택하여 주십시오.");
			pageName += "sample.html";
		} else {
			pageName = pageName + "web_sics050.html?fund_cd=" + fundCode;
			pageName += "&ymd="+ymd;
			pageName += "&corp_gr="+companyCode;
		}

		RDTemplate rdTemplate = GWT.create(RDTemplate.class);
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));
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