package myApp.client.vi.home.report;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Image;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.grid.GridBuilder;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.resource.ResourceIcon;
import myApp.client.service.GridRetrieveData;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.vi.bbs.model.Bbs01_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModelProperties;
import myApp.client.vi.fnd.model.Fnd01_FundCodeModel;
import myApp.client.vi.home.StartPage;
import myApp.client.vi.pln.Pln03_Lookup_ResrchDetail;
import myApp.client.vi.pln.model.Pln03_ResrchModel;
import myApp.client.vi.sys.model.Sys90_AfterServiceModel;

public class Notification extends ContentPanel implements InterfaceGridOperate {

	Bbs02_BoardModelProperties properties = GWT.create(Bbs02_BoardModelProperties.class);

	private Grid<Bbs02_BoardModel> grid = this.buildGrid();

	public Notification() {
		Info.display("","notification~~~~~~~~~~~~~");

		this.setHeaderVisible(false);

		VBoxLayoutContainer gridVBox = new VBoxLayoutContainer();
		gridVBox.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);

		Margins getTextMargins = new Margins(0, 0, 15, 0);
		Margins totalHBarMargins = new Margins(5, 0, 5, 30);
		Margins lineBar0Margins = new Margins(10, 0, 20, 30);

		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
		grid.setColumnResize(false);
		grid.setColumnReordering(false);
		grid.getView().setStripeRows(false);
		grid.getView().setColumnLines(false); 
		grid.getView().setAdjustForHScroll(true);
		grid.getView().setShowDirtyCells(false);
		grid.getElement().setBorders(false);
		grid.setWidth(730);
		grid.setHeight(1000);
		
		gridVBox.add(StartPage.getTextContents("공지사항"),new BoxLayoutData(getTextMargins));
		gridVBox.add(lineBar0,new BoxLayoutData(lineBar0Margins));
		gridVBox.add(this.grid, new BoxLayoutData(totalHBarMargins));

		this.add(gridVBox);
		
		retrieve();
		
		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Bbs02_BoardModel>(){
			
			@Override
			public void onSelectionChanged(SelectionChangedEvent<Bbs02_BoardModel> event) {
					popupPage(); 
			}
		});

	}

	private void popupPage() {
		
		Bbs02_BoardModel boardModel = grid.getSelectionModel().getSelectedItem();
		
		NotificationPopUp lookupResrch = new NotificationPopUp();
		lookupResrch.open(boardModel, new InterfaceCallbackResult() {
			@Override
			public void execute(Object result) {
				retrieve();
			}
		});
	}

	private Grid<Bbs02_BoardModel> buildGrid() {
		
		GridBuilder<Bbs02_BoardModel> gridBuilder = new GridBuilder<Bbs02_BoardModel>(properties.keyId());

		gridBuilder.addText(properties.titleName(), 500, "제목");
		gridBuilder.addDate(properties.settleDate(), 110, "작성일");
		gridBuilder.addLong(properties.cnt(), 50, "조회수");

		return gridBuilder.getGrid();
	}

	@Override
	public void retrieve() {
		GridRetrieveData<Bbs02_BoardModel> service = new GridRetrieveData<Bbs02_BoardModel>(grid.getStore());
		service.addParam("typeCode", "notice");
		service.addParam("setCount", (long)1000);
		service.retrieve("bbs.Bbs02_Board.selectByTypeCode");
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
