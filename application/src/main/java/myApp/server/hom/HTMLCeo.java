package myApp.server.hom;
 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.vi.hom.company.model.Hom03_OperatingModel;
import myApp.server.utils.db.DatabaseFactory;

public class HTMLCeo implements javax.servlet.Servlet {

	private String actionCode = "retrieve"; 

	private String nullString(String data) {
		if("null".equals(data) || data == null) {
			return ""; 
		}
		else {
			String str = data.replaceAll("\r", "<br>"); 
			str = str.replaceAll("\n", "<br>");
			return  str; 
		}
	}
	
	private String getHtml() {
		
		String header = ""; //"대표전화 번호 " + "02-782-5100<br>";  
		
		List<String> rowList = new ArrayList<String>(); 
		
		SqlSession sqlSession = DatabaseFactory.openSession(); 
		
//		List<Long> companyList = sqlSession.selectList("hom03_operating.selectByComanyId");
		
		String rowString = "";
		
//		rowString += this.tdGrey("소속(본부)", 50, "center",1); 	
//		rowString += this.tdGrey("성명/직책", 70, "center",1); 
//		rowString += this.tdGrey("담당증권사", 100, "center",1);
//		rowString += this.tdGrey("주요경력", 150, "center",1);
//		rowString += this.tdGrey("학력/자격증", 120, "center",1);
//		rowString += this.tdGrey("연락처", 70, "center",1);
		
//		rowList.add(this.tr(rowString)) ; 
//
//		int	j = 0;
//		for(Long orgCodeId : companyList) {
//			
//			List<Hom03_OperatingModel> list = sqlSession.selectList("hom03_operating.selectByOrgCode", orgCodeId);	
//
//			for(int i = 0 ; i<list.size(); i++) {
//				
//				Hom03_OperatingModel operatingModel = list.get(i);
//				
//				rowString = "";	
//				
//				if(i == 0) {
////					rowString += this.tdRowSpanGrey(list.size(), operatingModel.getOrgName(), 50, "center");
//					rowString += this.tdRowSpan(list.size(), operatingModel.getOrgName(), 50, "center", j%2);
//				}
//
//				rowString += this.tdCenter(operatingModel.getNameTitle(), 60, j%2); 
//
//				if(operatingModel.getChargeStockFirmCnt() == operatingModel.getChargeStockFirmMax()) {
//					if(operatingModel.getChargeStockFirmMax() > 1) {
//						rowString += this.tdRowSpan(operatingModel.getChargeStockFirmMax(),nullString(operatingModel.getChargeStockFirm()), 100, "center", j%2);
//					}
//					else {
//						rowString += this.tdCenter(nullString(operatingModel.getChargeStockFirm()), 100, j%2);
//					}
//				}
////				else {
////					int kk = operatingModel.getChargeStockFirmMax();
////					rowString += this.tdRowSpan(kk,nullString(operatingModel.getChargeStockFirm()), 100, "left", j%2);
////				}
////				rowString += this.tdCenter(nullString(operatingModel.getChargeStockFirm()), 100, j%2);
//				rowString += this.tdData(operatingModel.getMajorCareer(), 150, j%2);
//				rowString += this.tdData(operatingModel.getAcademicCertificate(), 140, j%2);
//				rowString += this.tdCenter(nullString(operatingModel.getContactInfomation()), 70, j%2);
//				
//				j += 1;
//				rowList.add(this.tr(rowString)) ; 
//			}
//		}


		String ceoimage = "<div><img src='img/ceo.jpg' width='217' height='267'></div>";

		String content = "<font color='#606060' >"
				+	"<span style=\"font-weight:bold;font-size:1.2em;\">안녕하세요!</span><br>"
				+	"<br><span style=\"font-weight:normal;font-size:0.9em;\">"
				+	"먼저 한국채권투자자문 홈페이지를 찾아주신 데 대해 감사 드립니다.<br>"
				+	"저희 회사는 채권과 펀드에 특화된 투자자문회사로 투자일임업과 투자자문업을 영위하고 있습니다.<br>"
				+	"<br>"
				+	"자본시장이 발전하면서 투자관련 정교한 이론과 모델이 개발되고, 유가증권을 발행한 기업에 대한 공시의무가 강화되었습니다.<br>"
				+	"자본시장 종사자의 실력도 향상되어 이제는 내용을 몰라서 투자에 실패하는 일은 찾아보기 어렵습니다.<br>"
				+	"그럼에도 불구하고 관련 임직원들의 윤리의식 제고는 여전히 요구되고 있습니다.<br>"
				+	"<br>"
				+	"저희 회사는 CFA 또는 CFA3차 합격자만 펀드매니저가 될 수 있도록 함으로써 철저한 윤리의식을 강조하고 있습니다.<br>"
				+	"회사나 개인의 이익보다 고객의 이익을 먼저 고려하는 것은 펀드매니저의 기본자세입니다.<br>"
				+	"높은 윤리의식과 실력으로 자산운용분야에서 Global Top이 되도록 최선을 다하겠습니다. 고객 여러분들의 관심을 부탁 드립니다.<br>"
				+	"<br>"
				+	"감사합니다.<br>"
				+	"<br></span>"
				+	"<div><span style='font-weight:bold;font-size:1.2em;'>한국채권투자자문㈜ 대표이사 김형호 </span><img src='img/ceo_name.jpg' width='100' height='39'></div>"
				;
		
		String education = "<font color='#606060' >"
					+	"<br>"
				 	+	"<img src='img/smallTitle.png'>"
				 	+	"<span style=\"font-weight:bold;\">  학력 및 자격사항</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍCFA</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍKDI School 자산운용 석사</span><br>"
				 	+	"<span style=\"font-size:0.8em;\">ㆍ부산대학교 경제학과</span><br>"
				 	;
		
		String career = "<font color='#606060' >"
					+	"<br>"
					+	"<img src='img/smallTitle.png'>"
					+	"<span style=\"font-weight:bold;\"> 주요이력 </span><br>"
					+	"<span style=\"font-size:0.8em;\">現) 한국채권투자자문 대표이사</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 아이투신 채권운용본부장</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 동양투신 채권운용본부장</span><br>"
					+	"<span style=\"font-size:0.8em;\">前) 조흥투신 채권운용팀장</span><br>"
					;
		
		rowString += this.tdRowSpan(3, content, 500, "left", 0);
		rowString += this.tdData1(ceoimage, 220, 0);
		rowList.add(this.tr(rowString)) ; 
		rowString = "";
		rowString += this.tdData1(education, 220, 0); 
		rowList.add(this.tr(rowString)) ;
		rowString = "";
		rowString += this.tdData1(career, 220, 0); 
		rowList.add(this.tr(rowString)) ; 
		sqlSession.close();
		
		String tableString = "<table style='width:99%; margin:0px; font-size:16px; border-collapse:collapse; padding:0px;'>";
		
		for(String rowString1 : rowList) {
			tableString += rowString1; 
		}
		
		tableString += "</table>" ; 
		
		String returnHtml = header + tableString ; 
		
		return returnHtml; 
	}

	
	private String tr(String data) {
		return "<tr style='height:auto;'>" + data + "</tr>"; 
	}

	private String tdData(String data, int width, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td style='width:" + width + "px;" + rowChange + "padding:0px 0px 0px 20px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdData1(String data, int width, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td style='width:" + width + "px;" + rowChange + "padding:0px 0px 0px 20px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdCenter(String data, int width, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td style='text-align:center; width:" + width + "px;" + rowChange + "padding:0px 0px 0px 20px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdRowSpan(int rowSpan, String data, int width, String align, int rowcount) {
		
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
		
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px;" + rowChange + "padding:0px 0px 0px 0px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}
	
	private String tdGrey(String data, int width, String align, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td bgcolor='#ebebec' style='text-align:" + align + "; width:" + width
				+ "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	private String tdColSpanGrey(int colSpan, String data, int width, String align, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td bgcolor='#ebebec' colSpan =" + colSpan
				+ " style='text-align:" + align + "; width:" + width
				+ "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	
	private String tdRowSpanGrey(int rowSpan, String data, int width, String align, int rowcount) {
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
	
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td bgcolor='#ebebec' rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}
	
		 
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		
		arg1.setContentType("text/html");
		arg1.setCharacterEncoding("UTF-8");
		
		this.actionCode = arg0.getParameter("actionCode");
		String empId = arg0.getParameter("empId");
		String evalTargetId = arg0.getParameter("evalTargetId");
		
		System.out.println("actionCode : " + actionCode);
		System.out.println("evalTargetId : " + evalTargetId);
		System.out.println("empId : "  + empId);
		
		String returnHtml = this.getHtml();
		
		PrintWriter out = arg1.getWriter();
		out.println(returnHtml);
		
	}
	@Override
	public void destroy() { 
	}
	@Override
	public ServletConfig getServletConfig() {
		return null;
	}
	@Override
	public String getServletInfo() {
		return null;
	}
	@Override
	public void init(ServletConfig arg0) throws ServletException {
	}
}
