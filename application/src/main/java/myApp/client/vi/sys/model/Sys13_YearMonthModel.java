package myApp.client.vi.sys.model;

import myApp.client.utils.GridDataModel;

public class Sys13_YearMonthModel implements GridDataModel {

	private	Long	yearMonthId;
	private	String	yearMonth;
	private	String	yearMonthKor;
	private	String	yearMonthEng;
	private	String	yearMonthPoint;

	@Override
	public void setKeyId(Long id) {
		this.setYearMonthId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getYearMonthId();
	}

	public	Long getYearMonthId() {
		return	yearMonthId;
	}

	public void setYearMonthId(Long yearMonthId) {
		this.yearMonthId = yearMonthId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public String getYearMonthKor() {
		return yearMonthKor;
	}

	public void setYearMonthKor(String yearMonthKor) {
		this.yearMonthKor = yearMonthKor;
	}

	public String getYearMonthEng() {
		return yearMonthEng;
	}

	public void setYearMonthEng(String yearMonthEng) {
		this.yearMonthEng = yearMonthEng;
	}

	public String getYearMonthPoint() {
		return yearMonthPoint;
	}

	public void setYearMonthPoint(String yearMonthPoint) {
		this.yearMonthPoint = yearMonthPoint;
	}

}
