package myApp.client.vi.cst;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.CollapseEvent;
import com.sencha.gxt.widget.core.client.event.CollapseEvent.CollapseHandler;
import com.sencha.gxt.widget.core.client.form.TextField;

import myApp.client.grid.ComboBoxField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.vi.LoginUser;

public class Tab_CstInfo extends VerticalLayoutContainer implements InterfaceGridOperate {
	
	TextField fundCode = new TextField();
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=0 src='{pageName}' width='100%' height='99%'/> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Tab_CstInfo() {

		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("userId", ""+LoginUser.getUserId());
		final ComboBoxField fundCodeComboBox = new ComboBoxField("FundCodeCombo", "%", "전체");
		fundCodeComboBox.addCollapseHandler(new CollapseHandler() {
			@Override
			public void onCollapse(CollapseEvent event) {
				fundCode.setValue(fundCodeComboBox.getCode());
				retrieve();
			}
		});

		searchBarBuilder.addComboBox(fundCodeComboBox, "계좌 ", 230, 38);
		
		searchBarBuilder.addRetrieveButton(); 
//		searchBarBuilder.addUpdateButton();
//		searchBarBuilder.addInsertButton();
//		searchBarBuilder.addDeleteButton();

		this.add(searchBarBuilder.getSearchBar(), new VerticalLayoutData(1, 40));

		setRd();
	}

	private void setRd() {
		
		String FundCode = fundCode.getValue();

		RDTemplate rdTemplate = GWT.create(RDTemplate.class);
		String pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/web_cs_info.html?fund_cd="+FundCode;
		
		HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));

//		ContentPanel panel = new ContentPanel();
//		panel.setBorders(false);
//		panel.add(htmlLayoutContainer, new MarginData(0));
//		panel.setHeight(1);

		rdLayoutContainer.clear();
		rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1, 1));

		this.add(rdLayoutContainer, new VerticalLayoutData(1, 1));
	}

	@Override
	public void retrieve() {
		setRd();
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
	}
	@Override
	public void insertRow() {
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteRow() {
		// TODO Auto-generated method stub
	}

	
}