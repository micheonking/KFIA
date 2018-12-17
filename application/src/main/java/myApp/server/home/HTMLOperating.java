package myApp.server.home;
 
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

import myApp.client.vi.home.company.model.Hom02_OperatingModel;
import myApp.server.utils.db.DatabaseFactory;

public class HTMLOperating implements javax.servlet.Servlet {

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
		
		String header = "대표전화 번호 " + "02-782-5100";  
		
		List<String> rowList = new ArrayList<String>(); 
		
		SqlSession sqlSession = DatabaseFactory.openSession(); 
		
		List<String> companyList = sqlSession.selectList("hom03_operating.selectByComanyName");
		
		for(String companyName : companyList) {
			
			List<Hom02_OperatingModel> list = sqlSession.selectList("hom03_operating.selectByCodeName", companyName);	
			
			for(int i = 0 ; i<list.size(); i++) {
				
				Hom02_OperatingModel operatingModel = list.get(i); 
				
				String rowString = "";	
				
				if(i == 0) {
					rowString += this.tdRowSpanGrey(list.size(), operatingModel.getOrgCodeId()+"", 100, "center"); 	
				}

				rowString += this.tdData(operatingModel.getNameTitle(), 100); 
				rowString += this.tdData(operatingModel.getChargeStockFirm(), 100);
				rowString += this.tdData(operatingModel.getMajorCareer(), 100);
				rowString += this.tdData(operatingModel.getAcademicCertificate(), 100);
				rowString += this.tdData(operatingModel.getContactInfomation(), 100);
				
				rowList.add(this.tr(rowString)) ; 
			}
		}
		
		sqlSession.close();
		
		String tableString = "<table border=1 style='width:100%; margin:10px; font-size:12px; border-collapse:collapse; border:1px silver solid; padding:0px;'>";
		
		
		for(String rowString : rowList) {
			tableString += rowString; 
		}
		
		tableString += "</table>" ; 
		
		String returnHtml = header + "<br>" + tableString ; 
		
		return returnHtml; 
		
	}
	private String tr(String data) {
		return "<tr style='height:auto;'>" + data + "</tr>"; 
	}

	private String tdData(String data, int width) {
		return "<td style='width:" + width + "px; padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdCenter(String data, int width) {
		return "<td style='text-align:center; width:" + width + "px; padding:5px; height:auto; word-wrap:break-word;' >" + data + "</td>" ; 
	}

	private String tdRowSpan(int rowSpan, String data, int width, String align) {
		
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
		
		return "<td rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px; padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}
	
	private String tdGrey(String data, int width, String align) {
		return "<td bgcolor='#ebebec' style='text-align:" + align + "; width:" + width+ "px; "
				+ " padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	private String tdColSpanGrey(int colSpan, String data, int width, String align) {
		return "<td bgcolor='#ebebec' colSpan =" + colSpan
				+ " style='text-align:" + align + "; width:" + width + "px; "
				+ "padding:5px; height:auto; word-wrap:break-word;' >" + data 
				+ "</td>" ; 
	}

	
	private String tdRowSpanGrey(int rowSpan, String data, int width, String align) {
		int rs = rowSpan; 
		if(rs<1) rs = 1 ;
	
		return "<td bgcolor='#ebebec' rowspan=" + rs 
				+ " style='text-align:" + align + "; width:" + width 
				+ "px; padding:5px; height:auto; word-wrap:break-word;' >" + data 
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
