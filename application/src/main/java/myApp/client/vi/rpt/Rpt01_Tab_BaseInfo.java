package myApp.client.vi.rpt;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.field.LookupTriggerField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;
import myApp.client.vi.MainFrameNorthLayout;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.dbm.Dbm01_Lookup_Tables;
import myApp.client.vi.dbm.model.Dbm01_TabCommentsModel;

public class Rpt01_Tab_BaseInfo extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Rpt01_Tab_BaseInfo() {
		this.setBorders(false); 
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		searchBarBuilder.addRetrieveButton();
		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 50, new Margins(5, 0, 0, 0)));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1));
		retrieve();
	}

	@Override
	public void retrieve() {
		RDTemplate rdTemplate = GWT.create(RDTemplate.class);
		String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/";
		if (LoginUser.getFundCode() == null) {
			new SimpleMessage("확인","계좌를 선택하여 주십시오.");
			pageName += "sample.html";
		} else {
			pageName += "web_cs_info.html?fund_cd="+LoginUser.getFundCode();
		}
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));
		rdLayoutContainer.clear();
		rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1,1));
		this.add(rdLayoutContainer, new VerticalLayoutData(1,1));
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