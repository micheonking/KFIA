package myApp.client.vi.home.company;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent;
import com.sencha.gxt.widget.core.client.event.BeforeHideEvent.BeforeHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

import myApp.client.service.InterfaceCallback;
import myApp.client.service.InterfaceServiceCall;
import myApp.client.service.ServiceCall;
import myApp.client.service.ServiceRequest;
import myApp.client.service.ServiceResult;
import myApp.client.utils.InterfaceCallbackResult;
import myApp.client.utils.SimpleMessage;
import myApp.client.vi.home.company.model.Hom02_OperatingModel;

public class Pmx06_Popup_02MboTopDown extends Window implements InterfaceServiceCall {
	
	SimpleHTMLTemplate htmlTemplate = GWT.create(SimpleHTMLTemplate.class);
	private Hom02_OperatingModel evalTargetModel; 
	private String actionCode = "retrieve" ;
	private HtmlLayoutContainer htmlLayoutContainer ; 
	private InterfaceCallback callback; 
	private TextButton rejectNoteButton = new TextButton("반려사유");
	
	public interface SimpleHTMLTemplate extends XTemplates {
		@XTemplate(source="HTMLMboTopDown.html") 
		SafeHtml setParam(String actionCode, Long evalTargetId, Long empId);
	}
	
//	private native void excelDownload() /*-{
//		$doc.getElementById("mboTopDownIFrame").contentWindow.mboTopDownToExcel();
//	}-*/;
//
//	
	public Pmx06_Popup_02MboTopDown(InterfaceCallback callback) {
		this.setModal(true);
		this.setHeading("업적목표 Matrix Top/Down");
		this.setResizable(false);
		this.setPixelSize(1400, 800);
		this.callback = callback;
//		
//		Pmx06_MboJSCaller.setOpenMboMatrixCallback(new InterfaceCallbackResult() {
//			@Override
//			public void execute(Object result) {
//				String evalTargetId = result.toString();
//				Pmx06_Popup_01MboBottomUp evalBottomUp = new Pmx06_Popup_01MboBottomUp(); 
//				evalBottomUp.open(Long.parseLong(evalTargetId)); 
//			}
//		}); 
//
//		Pmx06_MboJSCaller.setViewMboMemoCallback(new InterfaceCallbackResult() {
//			@Override
//			public void execute(Object result) {
//				String mboId = result.toString();
//				Pmx06_Edit_02MboNote editMboMemo = new Pmx06_Edit_02MboNote(); 
//				editMboMemo.viewMbo(Long.parseLong(mboId)); 
//			}
//		});
//		
//		this.addBeforeHideHandler(new BeforeHideHandler() {
//			@Override
//			public void onBeforeHide(BeforeHideEvent event) {
//				callback.execute();
//				
//			}
//		}); 
//		
//		TextButton downloadExcel = new TextButton("Excel Download"); 
//		downloadExcel.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				excelDownload(); 
//			}
//		}); 
//		this.getButtonBar().add(downloadExcel);
//
//		
//		rejectNoteButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				String msg = evalTargetModel.getStep10_ApprNote(); 
//				if(msg == null || "".equals(msg)) {
//					msg = "등록된 반려사유가 없습니다."; 
//				}
//				new SimpleMessage("반려사유", msg); 
//			}
//		}); 
//
//		this.getButtonBar().add(rejectNoteButton);
//	}
//	
//	public void openTopMbo(Pmx05_EvalTargetModel evalTargetModel) {
//
//		rejectNoteButton.setVisible(false); 
//		this.evalTargetModel = evalTargetModel ; 
//		
//		if("30".equals(evalTargetModel.getStep10_StateCode())) {
//			this.actionCode = "retrieve";
//		}
//		else { //  아직 확정이 아닌 경우...확정버튼 보이기...
//			
//			this.setJSCaller();
//			this.actionCode = "edit"; 
//			TextButton requestButton = new TextButton("확정");
//			requestButton.setWidth(80);
//			requestButton.addSelectHandler(new SelectHandler() {
//				@Override
//				public void onSelect(SelectEvent event) {
//					updateState("30"); // 확정처리함.   
//				}
//			});
//			this.getButtonBar().add(requestButton);
//		}
//		
//		TextButton closeButton = new TextButton("닫기"); 
//		closeButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				hide(); 
//			}
//		}); 
//		this.getButtonBar().add(closeButton);
//		this.setButtonAlign(BoxLayoutPack.CENTER);
//
//		this.htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.setParam(this.actionCode, this.evalTargetModel.getEvalTargetId(), this.evalTargetModel.getTargetEmpId()));
//		htmlLayoutContainer.setBorders(true);
//		this.add(htmlLayoutContainer, new MarginData(10));
//
//		this.show();
//		
//	}
//
//	private void updateState(String stateCode) {
//		// 반려사유 등록. 
//		this.updateState(stateCode, evalTargetModel.getStep10_ApprNote());
//	}
//		
//	private void updateState(String stateCode, String apprNote) {
//		// 결재상태 변경처리 
//		ServiceRequest request = new ServiceRequest("pmx.Pmx05_EvalTarget.updateStep10_StateCode");
//		request.addParam("evalTargetId", this.evalTargetModel.getEvalTargetId());
//		request.addParam("step10_StateCode", stateCode);
//		request.addParam("step10_ApprNote", apprNote);
//		
//		ServiceCall service = new ServiceCall();
//		service.execute(request, this);
//	}
//	
//	public void openMyMbo(Pmx05_EvalTargetModel evalTargetModel, Boolean topApproved) {
//
//		this.evalTargetModel = evalTargetModel ; 
//		this.actionCode = "edit";
//		String stateCode = this.evalTargetModel.getStep10_StateCode();
//
//		TextButton requestButton = new TextButton("승인요청");
//		TextButton cancelButton = new TextButton("요청취소");
//		requestButton.setEnabled(false);
//		cancelButton.setEnabled(false);
//		
//		if(topApproved) { 
//			// 상위자의 업적목표가 승인되었다. // 결재요청을 할 수 있다.
//			
//			if("20".equals(stateCode)) {
//				// 결재요청 후 결재전이다. 결재취소 할 수 있다. 
//				this.actionCode = "myRetrieve";
//				cancelButton.setEnabled(true);
//			} 
//			else if("30".equals(stateCode)) {
//				// 내꺼도 결재요청 중이거나 결재되었다. 
//				this.actionCode = "myRetrieve";
//			}
//			else { 
//				// 내꺼 결재 전이면 승인요청 할 수 있다. (작성중 & 반려상태일 경우...)
//				requestButton.setEnabled(true);
//				this.actionCode = "edit";
//				this.setJSCaller();
//			}
//			
//		}
//		else {
//			// 상위자가 아직 미승인상태라도 성과책임이 완료되었기에 내꺼는 작성할 수 있다. 
//			this.actionCode = "edit";
//			this.setJSCaller();
//		}
//		
//		requestButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				updateState("20"); 
//			}
//		}); 
//
//		cancelButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				updateState("10"); // 반려로 간다. 
//			}
//		}); 
//
//		TextButton closeButton = new TextButton("닫기"); 
//		closeButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				hide(); 
//			}
//		}); 
//
//		
//		this.getButtonBar().add(requestButton);
//		this.getButtonBar().add(cancelButton);
//		this.getButtonBar().add(closeButton);
//		this.setButtonAlign(BoxLayoutPack.CENTER);
//		
//		this.htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.setParam(this.actionCode, this.evalTargetModel.getEvalTargetId(), this.evalTargetModel.getTargetEmpId()));
//		htmlLayoutContainer.setBorders(true);
//		this.add(htmlLayoutContainer, new MarginData(10));
//		
//		this.show();
//
//	}
//
//	public void openBottomMbo(Pmx05_EvalTargetModel evalTargetModel) {
//
//		this.evalTargetModel = evalTargetModel ; 
//		String stateCode = this.evalTargetModel.getStep10_StateCode();
//		
//		TextButton confirmButton = new TextButton("승인");
//		confirmButton.setEnabled(false);
//		
//		TextButton rejectButton = new TextButton("반려");
//		rejectButton.setEnabled(false);
//		
//		if("20".equals(stateCode)) {
//			// 승인요청일 경우에만   
// 			confirmButton.addSelectHandler(new SelectHandler() {
//				@Override
//				public void onSelect(SelectEvent event) {
//					updateState("30"); 
//				}
//			});
// 			confirmButton.setEnabled(true);
// 			
// 			rejectButton.addSelectHandler(new SelectHandler() {
//				@Override
//				public void onSelect(SelectEvent event) {
//					Pmx04_Edit_RejectNote rejectNote = new Pmx04_Edit_RejectNote(new InterfaceCallbackResult() {
//						@Override
//						public void execute(Object result) {
//							String apprNote = result.toString(); //반려사유
//								updateState("90", apprNote); // 반려처리		
//							}
//					}); 
//					rejectNote.open(evalTargetModel.getStep10_ApprNote());
//				}
//			});
// 			
// 			rejectButton.setEnabled(true);
//
//		}
//		
//		this.htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.setParam(this.actionCode, this.evalTargetModel.getEvalTargetId(), this.evalTargetModel.getTargetEmpId()));
//		
//		htmlLayoutContainer.setBorders(true);
//		this.add(htmlLayoutContainer, new MarginData(10));
//		
//		TextButton closeButton = new TextButton("닫기"); 
//		closeButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				hide(); 
//			}
//		}); 
//		this.setJSCaller();
//		
//		this.getButtonBar().add(confirmButton);
//		this.getButtonBar().add(rejectButton);
//		this.getButtonBar().add(closeButton);
//		this.setButtonAlign(BoxLayoutPack.CENTER);
//		this.show();
//
//	}
//	
//	private void retrieve() {
//		this.htmlLayoutContainer.setHTML(htmlTemplate.setParam(this.actionCode, this.evalTargetModel.getEvalTargetId(), this.evalTargetModel.getTargetEmpId()));
//	}
//
//	public void setJSCaller() {
//		Pmx06_MboJSCaller.setAddMboCallback(new InterfaceCallbackResult() {
//			@Override
//			public void execute(Object result) {
//				String respId = result.toString();
//				Pmx06_Edit_01Mbo editMbo = new Pmx06_Edit_01Mbo(); 
//				editMbo.newMbo(Long.parseLong(respId), evalTargetModel.getEvalTargetId(), new InterfaceCallback() {
//					@Override
//					public void execute() {
//						retrieve(); 
//					}
//				}); 
//			}
//		});
//		Pmx06_MboJSCaller.setEditMboCallback(new InterfaceCallbackResult() {
//			@Override
//			public void execute(Object result) {
//				String mboId = result.toString();
//				Pmx06_Edit_01Mbo editMbo = new Pmx06_Edit_01Mbo(); 
//				editMbo.editMbo(Long.parseLong(mboId), new InterfaceCallback() {
//					@Override
//					public void execute() {
//						retrieve(); 
//					}
//				}); 
//			}
//		}); 
//	}
//
//	public void openAdmin(Pmx05_EvalTargetModel evalTargetModel) {
//
//		this.evalTargetModel = evalTargetModel ; 
//
//		TextButton cancelButton = new TextButton("승인취소");
//		cancelButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				updateState("10"); // 작성중으로 이동한다.  
//			}
//		}); 
//
//		TextButton closeButton = new TextButton("닫기"); 
//		closeButton.addSelectHandler(new SelectHandler() {
//			@Override
//			public void onSelect(SelectEvent event) {
//				hide(); 
//			}
//		}); 
//
//		this.getButtonBar().add(cancelButton);
//		this.getButtonBar().add(closeButton);
//		this.setButtonAlign(BoxLayoutPack.CENTER);
//
//		this.actionCode = "admin";
//		this.setJSCaller();
//		
//		this.htmlLayoutContainer = new HtmlLayoutContainer(htmlTemplate.setParam(this.actionCode, this.evalTargetModel.getEvalTargetId(), this.evalTargetModel.getTargetEmpId()));
//		htmlLayoutContainer.setBorders(true);
//		this.add(htmlLayoutContainer, new MarginData(10));
//		
//		this.show();
	}

	@Override
	public void getServiceResult(ServiceResult result) {
		if(result.getStatus() < 0){
			new SimpleMessage(result.getMessage());
			return ; 
		}
		else { 
			new SimpleMessage(result.getMessage());
			this.callback.execute(); 
			hide();
		}

	}
}
