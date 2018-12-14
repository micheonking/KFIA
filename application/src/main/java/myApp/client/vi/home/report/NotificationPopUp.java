package myApp.client.vi.home.report;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.resource.ResourceIcon;
import myApp.client.service.InterfaceCallback;
import myApp.client.utils.FileUpdownForm;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.JSCaller;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.home.StartPage;
import myApp.client.vi.sys.Sys10_Lookup_MultiFile;

public class NotificationPopUp extends Window implements Editor<Bbs02_BoardModel> {


	interface EditDriver extends SimpleBeanEditorDriver<Bbs02_BoardModel, NotificationPopUp>{}
	
	EditDriver editDriver = GWT.create(EditDriver.class);
	Grid<Bbs02_BoardModel> grid;
	Bbs02_BoardModel boardModel = new Bbs02_BoardModel();
	TextField titleName = new TextField();
	TextField filePath = new TextField();
	DateField settleDate = new DateField();
	TextArea contents  = new TextArea();
	
	private InterfaceCallbackResult callback;
	
	Sys10_Lookup_MultiFile fileForm = new Sys10_Lookup_MultiFile(null, "Y", 120) ;
//	private FileUpdownForm fileUpdownForm = new FileUpdownForm();
	
	
	public void open( Bbs02_BoardModel boardModel,InterfaceCallbackResult callback) {
		
		this.callback = callback;
		this.boardModel = boardModel;
		this.setHeaderVisible(false);
		this.setModal(true);
		this.setBorders(false);
		this.setResizable(false);
		this.setSize("900",  "920");
		editDriver.initialize(this);
		editDriver.edit(boardModel);
		fileForm.retrieve(boardModel.getBoardId());
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.add(this.getEditor());
		

		TextButton updateButton = new TextButton("저장"); 
		updateButton.setWidth(50);
		updateButton.addSelectHandler(new SelectHandler(){

			@Override
			public void onSelect(SelectEvent event) {
				update(); 
			}
		}); 
		this.getButtonBar().add(updateButton);
		
		TextButton closeButton = new TextButton("닫기"); 
		closeButton.setWidth(50);
		closeButton.addSelectHandler(new SelectHandler(){

			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		}); 
		this.getButtonBar().add(closeButton);
		this.show();
	}

	protected void retrieve() {
		
	}

	protected void update() {
		
	}

	private FormPanel getEditor() {
		
		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
		
		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(titleName, "제목"), new HorizontalLayoutData(680,-1, new Margins(10, 10, 0, 50)));
		
		HorizontalLayoutContainer row02 = new HorizontalLayoutContainer();
		row02.add(new FieldLabel(filePath,"파일"), new HorizontalLayoutData(670,-1, new Margins(20,0,10,50)));
		
		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(settleDate,"작성일"), new HorizontalLayoutData(300,-1, new Margins(30,0,10,50)));
		
		
//		Label dateHtml = new HTML(	
//				"<table>"
//						+"<tr><td style='background-color: #f7f7f7;' align='center' style= width : 10px;>작성일</td>"
//						+"<td>"+setdate.getText()+"</td>"
//						+"</tr>"
//						+ "</table>");
//		
	
		//html content 내용
		Label labelHtml = new HTML(	
				"<div>"
				+contents.getText()
				+"</div>"
				);

		HorizontalLayoutContainer row0 = new HorizontalLayoutContainer();
		Info.display("","boardModel  :  "+boardModel.getBoardId());
		Sys10_Lookup_MultiFile fileForm = new Sys10_Lookup_MultiFile(boardModel.getBoardId(), "Y", 120) ;//파일업로드
		
		row0.add(fileForm, new HorizontalLayoutData(800, 1, new Margins(80,0,0,90)));

		
		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(StartPage.getTextContents("공지사항"));
		layout.add(lineBar0,new VerticalLayoutData(1.2,1.2, new Margins(10, 0, 20, 45)));
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row02, new VerticalLayoutData(1, -1, new Margins(16)));
		layout.add(row03, new VerticalLayoutData(1, -1, new Margins(16)));
//		layout.add(labelHtml,new VerticalLayoutData(1,1, new Margins(60, 50, 0, 90))); //html content 내용
		layout.add(row0, new VerticalLayoutData(1, -1, new Margins(70,0,0,0)));  //첨부파일 파일등록 grid
//		layout.add(titleHtml,new VerticalLayoutData(0,0, new Margins(10, 50, 0, 90)));
//		layout.add(dateHtml,new VerticalLayoutData(0,0, new Margins(0, 50, 0, 90)));
//		layout.add(filePathHtml,new VerticalLayoutData(1.1,1.1, new Margins(33, 50, 20, 90)));
//		layout.add(row04, new VerticalLayoutData(1, -1, new Margins(16)));
//		layout.add(fileUpdownForm.getForm(),new VerticalLayoutData(1,1, new Margins(450,0,0,0)));
		
//		form setting
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(50); // 모든 field 적용 후 설정한다.
//		form.setSize("820", "384");
		
	return form;
}


	

	


	
}
