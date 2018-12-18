package myApp.client.vi;

import com.google.gwt.core.shared.GWT;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.PlainTabPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class MainFrame extends BorderLayoutContainer {

	private PlainTabPanel tabPanel = new PlainTabPanel();
	private MainFrameNorthLayout mainFrameNorthLayout = new MainFrameNorthLayout();
	private MenuTree treeMenu = new MenuTree(tabPanel); 
	
	private MainFrameSouthLayout mainFrameSouthLayout = new MainFrameSouthLayout();

	public MainFrame getMainWindow() {
		
		// 상단 Bar 등록  
		BorderLayoutData northLayoutData = new BorderLayoutData(70);
		northLayoutData.setMargins(new Margins(0, 0, 0, 0));
		this.setNorthWidget(mainFrameNorthLayout, northLayoutData); 
		
		// West Layout setting 
		BorderLayoutData westLayoutData = new BorderLayoutData(250);
		westLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		westLayoutData.setCollapsible(true);
		westLayoutData.setCollapseHeaderVisible(true);
		westLayoutData.setSplit(true);

		//westLayoutData.setCollapseMini(true);
		this.setWestWidget(this.getWestLayout(), westLayoutData);
		//this.setWestWidget(treeMenu.getMenuTree()); 

		BorderLayoutData southLayoutData = new BorderLayoutData(25);
		southLayoutData.setMargins(new Margins(0, 0, 0, 0)); // 앞쪽에 보이는 가로 줄을 없애준다
		this.setSouthWidget(mainFrameSouthLayout, southLayoutData);

		tabPanel.getElement().getStyle().clearMarginLeft();
		//.setLeft(7);
		//.getElement().get.getStyle().set("color", "#666666"); // font color 변경
		tabPanel.setTabMargin(8);
		tabPanel.setTabScroll(true);
//		tabPanel.setBorders(true);
        tabPanel.setAnimScroll(true);
        tabPanel.setTabScroll(true);
        tabPanel.setCloseContextMenu(true);
//        
//        Widget item = t.getSelectedItem();
//        TabItemConfig config = tabPanel.getConfig(item);
//        String name = config.getText();

		tabPanel.add(new TabBorder(), "달력"); // my page setting

		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(tabPanel, new VerticalLayoutData(1, 1, new Margins(0, 0, 0, 0)));
		
		this.setCenterWidget(vlc);

		return this; 
	}
	
	private ContentPanel getWestLayout(){
		
		AccordionLayoutAppearance accordianLayout = GWT.create(AccordionLayoutAppearance.class); 
		ContentPanel treeAccordianPanel = new ContentPanel(accordianLayout); 
		treeAccordianPanel.setHeading("고객정보메뉴");	
		treeAccordianPanel.add(this.treeMenu.getMenuTree()); // tree menu setting 
		treeAccordianPanel.getHeader().setHeight(16);
		AccordionLayoutContainer accordianContainer = new AccordionLayoutContainer();
		
//		accordianContainer.setBorders(true);
		accordianContainer.setExpandMode(ExpandMode.SINGLE_FILL);
		accordianContainer.setHideCollapseTool(true); // 감추기 버튼 감추기
		accordianContainer.setTitleCollapse(false); // 감추기 버튼 작동안하기 
//		accordianContainer.setBorders(true);

		accordianContainer.add(treeAccordianPanel);
		accordianContainer.setActiveWidget(treeAccordianPanel);
		
		VerticalLayoutContainer vlc = new VerticalLayoutContainer(); 
		vlc.add(accordianContainer, new VerticalLayoutData(1, 1, new Margins(3, 2, 2, 2)));
		
		return treeAccordianPanel; 
		
	}
	
//	private ContentPanel getNorthLayout(){
//		BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
//		flex.setFlex(1);
//		    
//		HBoxLayoutContainer header = new HBoxLayoutContainer();
//		header.setPadding(new Padding(5));
//		header.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
////		header.setHeight(60);
//
//		// KFIA
//		Image image = new Image();
////		image.setResource(ResourceIcon.INSTANCE.getLogo());
//		image.setPixelSize(280, 26);
//		header.add(image, new BoxLayoutData(new Margins(15, 0, 0, 20)));
//
//		BoxLayoutData boxLayoutData = new BoxLayoutData(new Margins(0, 0, 0, 0)); 
//		boxLayoutData.setFlex(1);
//		
//		header.add(new Label(), boxLayoutData);
//		 
//		String userInfo = LoginUser.getOrgKorName() + " " + LoginUser.getUserName() + " 님" ; ;
//		userInfo = "<p style='color:#666666; font-size:14px; font-weight:bold'>" +  userInfo + "</p>" ;  
//		
//		SafeHtml safeEscapedHtml = SafeHtmlUtils.fromTrustedString(userInfo);
//		
//		LabelToolItem label = new LabelToolItem(safeEscapedHtml); 
//		
////		Label label = new Label(headerMessage);  GWT Label 설정. 
////		label.getElement().getStyle().setProperty("color", "#666666"); // font color 변경
////		label.getElement().getStyle().setProperty("fontWeight", "bold"); // font color 변경
////		label.getElement().getStyle().setProperty("fontSize", "14px"); // font color 변경
//
//		header.add(label, new BoxLayoutData(new Margins(25, 0, 0, 0)));
//		
////		TextButton logoutButton = new TextButton("Logout");
//////		logoutButton.setHTML(cancelButtonHtml);
////		logoutButton.setWidth(65);
////		logoutButton.setHeight(70);
////		logoutButton.setBorders(true);
////		logoutButton.addSelectHandler(new SelectHandler() {
////			@Override
////			public void onSelect(SelectEvent event) {
////
////				Window.Location.reload();
////
////			}
////		});
////		header.add(logoutButton, new BoxLayoutData(new Margins(22, 40, 0, 5)));
//
//		Image logoutImage = new Image(ResourceIcon.INSTANCE.getLogout()); 
//		logoutImage.setPixelSize(25,  25);
//		
////		ToolButton close = new ToolButton(ToolButton.DOUBLERIGHT); 
////		header.add(logoutImage, new BoxLayoutData(new Margins(22, 0, 0, 10))); 
//
//		
//		String logoutString = "<button style='cursor:pointer;' onclick=\"location.reload();\">Logout</button>" ; 
//		SafeHtml logoutHtml = SafeHtmlUtils.fromTrustedString(logoutString);
//		
//		LabelToolItem logoutLabel = new LabelToolItem(logoutHtml);
////		logoutLabel.add		
//		header.add(logoutLabel, new BoxLayoutData(new Margins(22, 40, 0, 5)));
//		
//		ContentPanel cp = new ContentPanel();
//		cp.setBodyStyle("backgroundColor:#FFFFFF"); // http://www.w3schools.com/colors/colors_names.asp 페이지 참조 
//		
//		cp.add(header);
////		cp.forceLayout();
//		cp.setHeaderVisible(false);
////		cp.setBorders(true);
////		cp.setHeight(60);
////		cp.getButtonBar().setHeight(0);
//		
//		return cp ; 
//	}
}
