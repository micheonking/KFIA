package myApp.client.vi.bbs;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.cell.core.client.TextButtonCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.CellButtonBase;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

import myApp.client.grid.GridBuilder;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.service.GridDeleteData;
import myApp.client.service.GridInsertRow;
import myApp.client.service.GridRetrieveData;
import myApp.client.service.GridUpdate;
import myApp.client.vi.MenuOpener;
import myApp.client.vi.MenuTree;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModelProperties;
import myApp.client.vi.hom.StartPage;
import myApp.client.vi.hom.report.Notification;

public class Bbs02_Tab_Board extends BorderLayoutContainer implements InterfaceGridOperate {

	Bbs02_BoardModelProperties properties = GWT.create(Bbs02_BoardModelProperties.class);

	private Grid<Bbs02_BoardModel> grid = this.buildGrid();

	private StartPage startPage;
	
	public 	CellButtonBase mainButton = new CellButtonBase<>();
	
	public Bbs02_Tab_Board() {

		
		BorderLayoutData centerBorLayoutData = new BorderLayoutData(1);
		centerBorLayoutData.setMargins(new Margins(0, 0, 0, 0));
		centerBorLayoutData.setSplit(true);
		centerBorLayoutData.setMaxSize(1000);

        grid.setHideHeaders(true);
		grid.setColumnReordering(false);
		grid.getView().setStripeRows(false);
		grid.getView().setColumnLines(false); 
		grid.getView().setAdjustForHScroll(false);
//		grid.getView().setTrackMouseOver(false);	// 마우스 Over
		grid.getView().setEnableRowBody(false);
//		grid.setVisible(false);
		grid.setColumnResize(true);
		grid.setHeight(20);

		this.setCenterWidget(this.grid, centerBorLayoutData);
		this.grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Bbs02_BoardModel>(){
		
		@Override
		public void onSelectionChanged(SelectionChangedEvent<Bbs02_BoardModel> event) {
//			popupPage();
//			int xPosition = mainButton.getAbsoluteLeft();
			startPage.changePage("4");
		}
		});
			
		retrieve();
	}

	private void popupPage() {

//		textButton4.addSelectHandler(new SelectHandler() {
//		@Override
//		public void onSelect(SelectEvent event) {
	Info.display("","asd");
//			int xPosition = mainButton.getAbsoluteLeft();
			startPage.changePage("4");
//		}
		
		Info.display("","123456");
		Notification notification = new Notification();
		notification.show();	
	}

	public Grid<Bbs02_BoardModel> buildGrid() {
		GridBuilder<Bbs02_BoardModel> gridBuilder = new GridBuilder<Bbs02_BoardModel>(properties.keyId());
//		gridBuilder.setChecked(SelectionMode.SINGLE);

		gridBuilder.addText(properties.titleName(), 321, "제목");
		gridBuilder.addDate(properties.settleDate(), 90, "작성일");

//		gridBuilder.setMenuDisable(true);
//		gridBuilder.rowNum.setHidden(true);
		return gridBuilder.getGrid();
	}

	@Override
	public void retrieve() {
		GridRetrieveData<Bbs02_BoardModel> service = new GridRetrieveData<Bbs02_BoardModel>(grid.getStore());
		service.addParam("typeCode", "notice");
		service.addParam("setCount", (long)4);
		service.retrieve("bbs.Bbs02_Board.selectByTypeCode2");
	}

	@Override
	public void update() {
		GridUpdate<Bbs02_BoardModel> service = new GridUpdate<Bbs02_BoardModel>();
		// service.addParam("boardId", LoginUser.getUserId());
		service.update(grid.getStore(), "bbs.Bbs02_Board.update");
		
	}

	@Override
	public void insertRow() {
		GridInsertRow<Bbs02_BoardModel> service = new GridInsertRow<Bbs02_BoardModel>();
		Bbs02_BoardModel addrBookModel = new Bbs02_BoardModel();
		// addrBookModel.setEmpId(LoginUser.getUserId());
		service.insertRow(grid, addrBookModel);
	}

	@Override
	public void deleteRow() {
		GridDeleteData<Bbs02_BoardModel> service = new GridDeleteData<Bbs02_BoardModel>();
		List<Bbs02_BoardModel> checkedList = grid.getSelectionModel().getSelectedItems();
		service.delete(grid.getStore(), checkedList, "bbs.Bbs02_Board.delete");
	}

	protected void businessDown() {
		// TODO Auto-generated method stub
//        FirmHeadCenterView homePage = new FirmHeadCenterView();
//        homePage.open();		
	}

}
