package myApp.client.vi.cst;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent;
import com.sencha.gxt.widget.core.client.event.TriggerClickEvent.TriggerClickHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.field.LookupTriggerField;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.grid.SearchBarBuilder;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.vi.dbm.Dbm01_Lookup_Tables;
import myApp.client.vi.dbm.model.Dbm01_TabCommentsModel;

public class Cst01_Tab_BaseInfo extends BorderLayoutContainer implements InterfaceGridOperate {
	
	private Dbm01_TabCommentsModel findTableModel = new Dbm01_TabCommentsModel(); 
	private LookupTriggerField lookupTableField = new LookupTriggerField();
	TextField tableNameField = new TextField();
	VerticalLayoutContainer rdLayoutContainer = new VerticalLayoutContainer();

	public interface RDTemplate extends XTemplates {
	    @XTemplate("<iframe id='reportDesinger' border=1 src='{pageName}' width='100%' height='100%' /> ")
	    SafeHtml getTemplate(String pageName);
	}

	public Cst01_Tab_BaseInfo() {
		this.setBorders(false); 
		
		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
		lookupTableField.addTriggerClickHandler(new TriggerClickHandler(){
			@Override
			public void onTriggerClick(TriggerClickEvent event) {
				openLookupTable();
			}
		}); 
		
		FieldLabel tableNameLabel = new FieldLabel(lookupTableField, "Table 찾기");
		tableNameLabel.setLabelWidth(100);
		tableNameLabel.setWidth(350);
//		tableNameLabel.setBorders(true);
		searchBarBuilder.getSearchBar().add(tableNameLabel); 
		lookupTableField.setEmptyText("전체");

		searchBarBuilder.addRetrieveButton();

		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(55));

		this.setCenterWidget(rdLayoutContainer);
		
		retrieve();
	}

	private void setReportDesigner() {
		
		String tableName = lookupTableField.getValue();
		String pageName = "";

		RDTemplate rdTemplate = GWT.create(RDTemplate.class);

		if(tableName != null) {
			tableName = "%" + tableName + "%";  
			pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/rd_Table_Define.html?table_in=" + tableName;
			HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));

			rdLayoutContainer.clear();
			rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1, 1));
			this.setCenterWidget(rdLayoutContainer);

		}
		else {
			pageName = "http://172.20.200.252:8283/ReportingServer/html5/RDhtml/sample.html";
			HtmlLayoutContainer htmlLayoutContainer = new HtmlLayoutContainer(rdTemplate.getTemplate(pageName));

			rdLayoutContainer.clear();
			rdLayoutContainer.add(htmlLayoutContainer, new VerticalLayoutData(1, 1));
			this.setCenterWidget(rdLayoutContainer);

		}

	}

	private void openLookupTable() {
		Dbm01_Lookup_Tables lookupTable = new Dbm01_Lookup_Tables();
		lookupTable.open(new InterfaceCallbackResult() {
			@Override
			public void execute(Object result) {
				findTableModel = (Dbm01_TabCommentsModel)result;
				lookupTableField.setValue(findTableModel.getTableName());
			}
		});

	}

	@Override
	public void retrieve() {
		setReportDesigner();
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