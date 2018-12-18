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
		
		List<Long> companyList = sqlSession.selectList("hom03_operating.selectByComanyId");
		
		String rowString = "";	
		
		rowString += this.tdGrey("소속(본부)", 50, "center",1); 	
		rowString += this.tdGrey("성명/직책", 70, "center",1); 
		rowString += this.tdGrey("담당증권사", 100, "center",1);
		rowString += this.tdGrey("주요경력", 150, "center",1);
		rowString += this.tdGrey("학력/자격증", 120, "center",1);
		rowString += this.tdGrey("연락처", 70, "center",1);
		
		rowList.add(this.tr(rowString)) ; 

		int	j = 0;
		for(Long orgCodeId : companyList) {
			
			List<Hom03_OperatingModel> list = sqlSession.selectList("hom03_operating.selectByOrgCode", orgCodeId);	

			for(int i = 0 ; i<list.size(); i++) {
				
				Hom03_OperatingModel operatingModel = list.get(i);
				
				rowString = "";	
				
				if(i == 0) {
//					rowString += this.tdRowSpanGrey(list.size(), operatingModel.getOrgName(), 50, "center");
					rowString += this.tdRowSpan(list.size(), operatingModel.getOrgName(), 50, "center", j%2);
				}

				rowString += this.tdCenter(operatingModel.getNameTitle(), 60, j%2); 

				if(operatingModel.getChargeStockFirmCnt() == operatingModel.getChargeStockFirmMax()) {
					if(operatingModel.getChargeStockFirmMax() > 1) {
						rowString += this.tdRowSpan(operatingModel.getChargeStockFirmMax(),nullString(operatingModel.getChargeStockFirm()), 100, "center", j%2);
					}
					else {
						rowString += this.tdCenter(nullString(operatingModel.getChargeStockFirm()), 100, j%2);
					}
				}
//				else {
//					int kk = operatingModel.getChargeStockFirmMax();
//					rowString += this.tdRowSpan(kk,nullString(operatingModel.getChargeStockFirm()), 100, "left", j%2);
//				}
//				rowString += this.tdCenter(nullString(operatingModel.getChargeStockFirm()), 100, j%2);
				rowString += this.tdData(operatingModel.getMajorCareer(), 150, j%2);
				rowString += this.tdData(operatingModel.getAcademicCertificate(), 140, j%2);
				rowString += this.tdCenter(nullString(operatingModel.getContactInfomation()), 70, j%2);
				
				j += 1;
				rowList.add(this.tr(rowString)) ; 
			}
		}
		
		sqlSession.close();
		
		String tableString = "<table border=1 style='width:99%; margin:0px; font-size:12px; border-collapse:collapse; border:1px silver solid; padding:0px;'>";
		
		
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

		return "<td style='width:" + width + "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdCenter(String data, int width, int rowcount) {
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td style='text-align:center; width:" + width + "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdRowSpan(int rowSpan, String data, int width, String align, int rowcount) {
		
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
		
		String rowChange = " ";
		if(rowcount == 1) rowChange = " background-color:#f5f5f5; "; 

		return "<td rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px;" + rowChange + "padding:5px; height:auto; word-wrap:break-word;' >" + data 
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
