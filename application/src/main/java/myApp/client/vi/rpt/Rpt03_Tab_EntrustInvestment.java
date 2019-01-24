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
	
	CommonComboBoxField yearComboBox = null;
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
		this.setBorders(false); 
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		//년도 SET
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("fundCode", LoginUser.getCstUserModel().getFundCode());
		yearComboBox = new CommonComboBoxField("cst.Cst02_Account.selectYearCombo", param, new InterfaceCallback() {
			@Override
			public void execute() {
				retrieve();
			}
		});
		FieldLabel yearField = new FieldLabel(yearComboBox, "년도 ");
		yearField.setWidth(150);
		yearField.setLabelWidth(50);
		searchBarBuilder.getSearchBar().add(yearField);

		//분기 SET
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
		searchBarBuilder.getSearchBar().add(radioField);

		//조회버튼 SET
		searchBarBuilder.addRetrieveButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(1, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1, new Margins(0,10,0,0)));
	}

	@Override
	public void retrieve() {
		String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";
		String companyCode = LoginUser.getCstUserModel().getCompanyModel().getCompanyCode();
		String fundCode = LoginUser.getCstUserModel().getFundCode(); 
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