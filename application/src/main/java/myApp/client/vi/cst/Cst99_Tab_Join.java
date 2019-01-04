package myApp.client.vi.cst;

import java.util.List;
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
import com.sencha.gxt.widget.core.client.form.LongField;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;

import myApp.client.resource.ResourceIcon;
import myApp.client.service.DBUtil;
import myApp.client.service.GridUpdate;
import myApp.client.service.InterfaceCallback;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.LoginUser;
import myApp.client.vi.bbs.model.Bbs01_BoardModel;
import myApp.client.vi.bbs.model.Bbs02_BoardModel;
import myApp.client.vi.cst.model.Cst01_UserModel;
import myApp.client.vi.cst.model.Cst02_AccountModel;
import myApp.client.vi.fnd.model.Fnd01_FundCodeModel;
import myApp.client.vi.fnd.model.Fnd03_ConsignmentModel;
import myApp.client.vi.hom.StartPage;
import myApp.client.vi.sys.model.Sys90_AfterServiceModel;

public class Cst99_Tab_Join extends Window implements Editor<Cst01_UserModel>, InterfaceServiceCall {


	interface EditDriver extends SimpleBeanEditorDriver<Cst01_UserModel, Cst99_Tab_Join>{}
	
	EditDriver editDriver = GWT.create(EditDriver.class);
	Grid<Cst01_UserModel> grid;
	Cst01_UserModel userModel = new Cst01_UserModel();
	
	LongField userId = new LongField();
	TextField email = new TextField();
	TextField userName = new TextField();
	TextField phoneNo = new TextField();
	TextField mrdRole = new TextField();
	DateField startDt = new DateField();
	Cst99_Edit_account accountPage = new Cst99_Edit_account();
	
	private InterfaceCallbackResult callback;
	
	
	
	public void open( Cst01_UserModel userModel2,InterfaceCallbackResult callback) {
		
		editDriver.initialize(this);
		this.callback = callback;
		this.userModel = userModel2;
		this.setHeaderVisible(false);
		this.setModal(true);
		this.setBorders(false);
		this.setResizable(false);
//		this.setSize("1000",  "920");
		this.grid = grid;
		this.setSize("900",  "920");
		editDriver.edit(userModel);
		
		this.setButtonAlign(BoxLayoutPack.CENTER);
		this.add(this.getEditor(userModel2));
		
		TextButton regiButton = new TextButton("등록"); 
		regiButton.setWidth(50);
		regiButton.addSelectHandler(new SelectHandler(){
			
			@Override
			public void onSelect(SelectEvent event) {
				update();
			}
		}); 
		this.getButtonBar().add(regiButton);
		
		TextButton closeButton = new TextButton("닫기"); 
		closeButton.setWidth(50);
		closeButton.addSelectHandler(new SelectHandler(){

			@Override
			public void onSelect(SelectEvent event) {
				hide(); 
			}
		}); 
		this.getButtonBar().add(closeButton);
		
//		seq 셍성해서 userId로 계좌추가 화면에 넣어주기.
		userModel  = editDriver.flush();
		DBUtil dbutil = new DBUtil();
		dbutil.setSeq(userModel, new InterfaceCallback() {
			@Override
			public void execute() {
				
				editDriver.edit(userModel);
				accountPage.setUserId(userModel.getUserId());
			}
		});
		
		
		
		this.show();
	}


	private FormPanel getEditor(Cst01_UserModel userModel2) {
		
		Image lineBar0 = new Image(ResourceIcon.INSTANCE.verticalTitle());
		
		HorizontalLayoutContainer row01 = new HorizontalLayoutContainer();
		row01.add(new FieldLabel(email, "email"), new HorizontalLayoutData(310 ,-1, new Margins(10, 10, 0, 50)));
		
		HorizontalLayoutContainer row03 = new HorizontalLayoutContainer();
		row03.add(new FieldLabel(userName,"이름"), new HorizontalLayoutData(300,-1, new Margins(30,0,10,50)));
		
		HorizontalLayoutContainer row05 = new HorizontalLayoutContainer();
		row05.add(new FieldLabel(phoneNo,"휴대폰번호"), new HorizontalLayoutData(300,-1, new Margins(60,0,10,50)));
		
//		HorizontalLayoutContainer row06 = new HorizontalLayoutContainer();
//		row06.add(new FieldLabel(mrdRole,"mrdRole"), new HorizontalLayoutData(300,-1, new Margins(90,0,10,50)));
		
		HorizontalLayoutContainer row07 = new HorizontalLayoutContainer();
		row07.add(new FieldLabel(startDt,"startDt"), new HorizontalLayoutData(300,-1, new Margins(120,0,10,50)));
		
		HorizontalLayoutContainer row04 = new HorizontalLayoutContainer();
		row04.add(accountPage, new HorizontalLayoutData(800, 500, new Margins(160,0,0,60)));
		
		VerticalLayoutContainer layout = new VerticalLayoutContainer();
		layout.add(StartPage.getTextContents("계좌등록 및 회원가입"));
		layout.add(lineBar0,new VerticalLayoutData(1.2,1.2, new Margins(0, 0, 10, 45)));
		layout.add(row01, new VerticalLayoutData(1, -1, new Margins(16))); //제목
		layout.add(row03, new VerticalLayoutData(1, -1, new Margins(16))); //작성일
		layout.add(row05, new VerticalLayoutData(1, -1, new Margins(16))); 
//		layout.add(row06, new VerticalLayoutData(1, -1, new Margins(16))); 
		layout.add(row07, new VerticalLayoutData(1, -1, new Margins(16))); 
		layout.add(row04, new VerticalLayoutData(1, -1, new Margins(16))); 
		
//		form setting 입니다.
		FormPanel form = new FormPanel();
		form.setWidget(layout);
		form.setLabelWidth(50); // 모든 field 적용 후 설정한다.
		form.setSize("820", "784");
		
	return form;
	}

	protected void update() {
		userModel  = editDriver.flush();
		GridUpdate<Cst02_AccountModel> service = new GridUpdate<Cst02_AccountModel>();
		service.addParam("userModel", userModel);
		service.update(accountPage.grid.getStore(), "cst.Cst99_User.update") ; 
	}

	private void update2(Cst01_UserModel userModel) {
		ServiceRequest request = new ServiceRequest("cst.Cst99_User.update");
		request.putModelParam("userModel", userModel);
		Info.display("aaaaaa",""+userModel.getEmail());
		ServiceCall service = new ServiceCall();
		service.execute(request, this);
		
	}
	

	@Override
	public void getServiceResult(ServiceResult result) {
		// TODO Auto-generated method stub
		
	}
	
}
