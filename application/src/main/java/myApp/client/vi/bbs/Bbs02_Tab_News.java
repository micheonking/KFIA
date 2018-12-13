package myApp.client.vi.bbs;
 
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.Grid;

import myApp.client.grid.GridBuilder;
import myApp.client.grid.InterfaceGridOperate;
import myApp.client.service.GridDeleteData;
import myApp.client.service.GridInsertRow;
import myApp.client.service.GridRetrieveData;
import myApp.client.service.GridUpdate;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModelProperties;
 
public class Bbs02_Tab_News extends BorderLayoutContainer implements InterfaceGridOperate {
 
	Bbs02_BoardModelProperties properties = GWT.create(Bbs02_BoardModelProperties.class);
   
    private Grid<Bbs02_BoardModel> grid = this.buildGrid();
 
    public Bbs02_Tab_News() {
 
//		this.setBorders(false); 
//
//		SearchBarBuilder searchBarBuilder = new SearchBarBuilder(this);
//		searchBarBuilder.addRetrieveButton();
//		searchBarBuilder.addUpdateButton();
//		searchBarBuilder.addInsertButton();
//		searchBarBuilder.addDeleteButton();
//
//		this.setBorders(false);
//		this.setNorthWidget(searchBarBuilder.getSearchBar(), new BorderLayoutData(50));
 
        BorderLayoutData centerBorLayoutData = new BorderLayoutData(1);
        centerBorLayoutData.setMargins(new Margins(0, 0, 0, 0));
        centerBorLayoutData.setSplit(true);
        centerBorLayoutData.setMaxSize(1000);
 
        grid.setHideHeaders(true);
		grid.setColumnReordering(false);
		grid.getView().setStripeRows(false);
		grid.getView().setColumnLines(false); 
		grid.getView().setAdjustForHScroll(false);
		grid.getView().setTrackMouseOver(false);	// 마우스 Over
		grid.getView().setEnableRowBody(false);
		grid.getView().setStripeRows(false);
		grid.getView().setEnableRowBody(false);
		grid.setBorders(true);
		grid.getElement().setBorders(false);
		grid.getView().setShowDirtyCells(false);
		grid.getView().setAdjustForHScroll(false);
//		grid.setVisible(false);
//		grid.setColumnResize(false);

//		this.buildGrid().setBorders(true);
		this.setCenterWidget(this.grid, centerBorLayoutData);
        
        retrieve();
    }
 
    public Grid<Bbs02_BoardModel> buildGrid(){
        GridBuilder<Bbs02_BoardModel> gridBuilder = new GridBuilder<Bbs02_BoardModel>(properties.keyId());  
//		gridBuilder.setChecked(SelectionMode.SINGLE);
        
		gridBuilder.addText	(properties.titleName(),	321,"제목");
		gridBuilder.addDate	(properties.setdate(),		90,	"작성일");
//		gridBuilder.addLong	(properties.cnt(),			90,	"작성일");
        
        return gridBuilder.getGrid(); 
    }
    
	@Override
	public void retrieve(){
    	GridRetrieveData<Bbs02_BoardModel> service = new GridRetrieveData<Bbs02_BoardModel>(grid.getStore());
		service.addParam("typeCode", "release");
		service.addParam("setCount", (long)4);
        service.retrieve("bbs.Bbs02_Board.selectByTypeCode");
	}

	@Override
	public void update(){
		GridUpdate<Bbs02_BoardModel> service = new GridUpdate<Bbs02_BoardModel>();
//		service.addParam("boardId", LoginUser.getUserId()); 
		service.update(grid.getStore(), "bbs.Bbs02_Board.update"); 
	}

	@Override
	public void insertRow(){
		GridInsertRow<Bbs02_BoardModel> service = new GridInsertRow<Bbs02_BoardModel>(); 
		Bbs02_BoardModel addrBookModel = new Bbs02_BoardModel();
//		addrBookModel.setEmpId(LoginUser.getUserId());
		service.insertRow(grid, addrBookModel); 
	}
	

	@Override
	public void deleteRow(){
		GridDeleteData<Bbs02_BoardModel> service = new GridDeleteData<Bbs02_BoardModel>();
		List<Bbs02_BoardModel> checkedList = grid.getSelectionModel().getSelectedItems() ; 
		service.delete(grid.getStore(), checkedList, "bbs.Bbs02_Board.delete");
	}

}
 
 