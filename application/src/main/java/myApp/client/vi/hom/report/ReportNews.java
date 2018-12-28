package myApp.client.vi.hom.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.RowExpander;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.grid.InterfaceGridOperate;
import myApp.client.resource.ResourceIcon;
import myApp.client.service.GridRetrieveData;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModelProperties;
import myApp.client.vi.hom.StartPage;

public class ReportNews extends ContentPanel implements InterfaceGridOperate{
	
	private Grid<Bbs02_BoardModel> grid;
	Bbs02_BoardModelProperties properties = GWT.create(Bbs02_BoardModelProperties.class);
	Bbs02_BoardModel bbsModel = new Bbs02_BoardModel();
	
	TextField searchText = new TextField();
	TextButton searchButton = new TextButton();

	public ReportNews() {

		this.setHeaderVisible(false);
		searchButton.setWidth(50);
		searchButton.setText("검색");
		searchText.addKeyPressHandler(new KeyPressHandler() {

			@Override
			public void onKeyPress(KeyPressEvent event) {
				if(event.getCharCode() == 13) { // enter key event
                    retrieve();
                }				
			}
        });
		
		HorizontalLayoutData hld = new HorizontalLayoutData();
		hld.setMargins(new Margins(0,0,0,5));
		
		HorizontalLayoutData hld2 = new HorizontalLayoutData();
		hld.setMargins(new Margins(0,80,0,0));
		
		HorizontalLayoutContainer hzl = new HorizontalLayoutContainer();
		hzl.add(searchText,hld2);
		hzl.add(searchButton,hld);
		
		VBoxLayoutContainer gridBox = new VBoxLayoutContainer();
		gridBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
		gridBox.add(StartPage.getTextContents("보도자료"),new BoxLayoutData(new Margins(0,0,15,0)));
		gridBox.add(lineBar0, new BoxLayoutData(new Margins(10,0,20,30)));
		gridBox.add(hzl, new BoxLayoutData(new Margins(0,0,20,30)));
		gridBox.add(rowTest(),new BoxLayoutData(new Margins(20,0,0,30)));
		this.add(gridBox);
		
		
		retrieve();
	}

	private Widget rowTest() {
		RowExpander<Bbs02_BoardModel> rowExpander = new RowExpander<>(new AbstractCell<Bbs02_BoardModel>() {
			@Override
			public void render(Context context, Bbs02_BoardModel value, SafeHtmlBuilder sb) {
				sb.appendHtmlConstant("<p style='margin:10px 20px 30px;font-size: 12px'><b> </b>"+value.getContents()+"</p>");
				
			}
		});

		ColumnConfig<Bbs02_BoardModel, String> titleName = new ColumnConfig<Bbs02_BoardModel,String>(properties.titleName(),40,"제목");
		ColumnConfig<Bbs02_BoardModel, Date> setDate = new ColumnConfig<Bbs02_BoardModel, Date>(properties.settleDate(), 100, "작성일");
		ColumnConfig<Bbs02_BoardModel, Long> cnt = new ColumnConfig<Bbs02_BoardModel, Long>(properties.cnt(), 100, "조회수");
		
		List<ColumnConfig<Bbs02_BoardModel, ?>> columns = new ArrayList<ColumnConfig<Bbs02_BoardModel, ?>>();
		columns.add(rowExpander);
		columns.add(titleName);
		
		setDate.setCell(new DateCell(DateTimeFormat.getFormat("yyyy-MM-dd")));
		setDate.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		columns.add(setDate);
		
		cnt.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER );
		columns.add(cnt);
		
		ColumnModel<Bbs02_BoardModel> cm = new ColumnModel<Bbs02_BoardModel>(columns);
		ListStore<Bbs02_BoardModel> store = new ListStore<Bbs02_BoardModel>(properties.keyId());
		
		grid = new Grid<Bbs02_BoardModel>(store,cm);
		grid.getView().setAutoExpandColumn(titleName);
		grid.setBorders(false);
		grid.getView().setStripeRows(true);
		grid.getView().setColumnLines(true);
		
		rowExpander.initPlugin(grid);
		ContentPanel panel = new ContentPanel();
	    panel.add(grid);
	    grid.setHeight(600);
	    panel.setHeaderVisible(false);
	    return panel;
		}

	@Override
	public void retrieve() {
		
		GridRetrieveData<Bbs02_BoardModel> service2 = new GridRetrieveData<Bbs02_BoardModel>(grid.getStore());
		service2.addParam("typeCode", "release");
		service2.addParam("setCount", (long)1000);
		service2.addParam("titleName",searchText.getText());
		service2.retrieve("bbs.Bbs02_Board.selectByTypeCode");
		
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
