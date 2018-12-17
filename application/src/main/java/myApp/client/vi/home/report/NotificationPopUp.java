package myApp.client.vi.home.report;

import java.util.Map;

import org.apache.tools.ant.taskdefs.Javadoc.Html;

import com.gargoylesoftware.htmlunit.html.HtmlArea;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.sencha.gxt.core.client.util.DelayedTask;
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
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;
import myApp.client.utils.InterfaceCallbackResult;
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
	
	Sys10_Lookup_MultiFile fileForm = new Sys10_Lookup_MultiFile() ;
	
	
	public void open( Bbs02_BoardModel boardModel,InterfaceCallbackResult callback) {
		
		this.callback = callback;
		this.boardModel = boardModel;
		this.setHeaderVisible(false);
		this.setModal(true);
		this.setBorders(false);
		this.setResizable(false);
//		this.setSize("1000",  "920");
		this.grid = grid;
		this.setSize("900",  "920");
		editDriver.initialize(this);
		editDriver.edit(boardModel);
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.add(this.getEditor());
		fileForm.retrieve(boardModel.getBoardId());
		
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


	private FormPanel getEditor() {
		
		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
		
		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(titleName, "제목"), new HorizontalLayoutData(700,-1, new Margins(10, 10, 0, 50)));
		
		
		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(settleDate,"작성일"), new HorizontalLayoutData(300,-1, new Margins(30,0,10,50)));
	
		//html content 내용
		Label labelHtml = new HTML(	
				"<div style='overflow:scroll; height:150;'>"
				+contents.getText()
				+"</div>"
				);

		HorizontalLayoutData labelLayout = new HorizontalLayoutData( 120,  -1, new Margins(10,0,0,52));	//라벨Size
		
		HorizontalLayoutContainer row0 = new HorizontalLayoutContainer();
		fileForm = new Sys10_Lookup_MultiFile(boardModel.getBoardId(), "Y", 70) ;//파일업로드
//		row0.add(fileForm, new HorizontalLayoutData(800, 300, new Margins(80,0,0,90)));
		
		//첨부파일
		row0.add(new LabelToolItem("첨부파일:"), labelLayout);
				FieldSet filefieldSet = new FieldSet();
				filefieldSet.setCollapsible(false);
				filefieldSet.add(fileForm);
				row0.add(filefieldSet, new HorizontalLayoutData(653, -1));
		
		
		
		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(StartPage.getTextContents("공지사항"));
		layout.add(lineBar0,new VerticalLayoutData(1.2,1.2, new Margins(10, 0, 10, 45)));
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(16))); //제목
		layout.add(row03, new VerticalLayoutData(1, -1, new Margins(16))); //작성일
		layout.add(row0, new VerticalLayoutData(1, 300, new Margins(50,0,0,0)));  //첨부파일 파일등록 grid
		layout.add(labelHtml,new VerticalLayoutData(1,1, new Margins(2, 50, 0, 90))); //html content 내용
		
//		form setting 입니다.
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(50); // 모든 field 적용 후 설정한다.
//		form.setSize("820", "384");
		
	return form;
}


	

	


	
}
