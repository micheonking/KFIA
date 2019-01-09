package myApp.client.vi.cst.model;

import java.util.Date;
import myApp.client.utils.GridDataModel;

public class Cst03_IcamAccountsModel implements GridDataModel {
	
	private Long	icamAccountId;
	private	String	fundCode;
	private	String	fundName;
	private	String	accNo;
	private	Date	seoljDate;
	private	String	seriesGb;
	private	Long	seoljYear;
	private	Long	sintakGigan;
	private	Long	basicPer;
	private	Long	bmPer;
	private	Long	successPer;
	private	String	unyongName;
	private	String	csGb;
	private	Long	aek;
	private	String	code;
	private	String	codeName;
	private	String	name;
	private	String	jjName;
	private	Date	gyulDate;

	@Override
	public void setKeyId(Long id) {
		this.setIcamAccountId(id);
	}

	@Override
	public Long getKeyId() {
		
		return this.getIcamAccountId();
	}
	
	public Long getIcamAccountId() {
		return icamAccountId;
	}

	public void setIcamAccountId(Long icamAccountId) {
		this.icamAccountId = icamAccountId;
	}
	
	public String getFundCode() {
		return fundCode;
	}
	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public Date getSeoljDate() {
		return seoljDate;
	}
	public void setSeoljDate(Date seoljDate) {
		this.seoljDate = seoljDate;
	}
	public String getSeriesGb() {
		return seriesGb;
	}
	public void setSeriesGb(String seriesGb) {
		this.seriesGb = seriesGb;
	}
	public Long getSeoljYear() {
		return seoljYear;
	}
	public void setSeoljYear(Long seoljYear) {
		this.seoljYear = seoljYear;
	}
	public Long getSintakGigan() {
		return sintakGigan;
	}
	public void setSintakGigan(Long sintakGigan) {
		this.sintakGigan = sintakGigan;
	}
	public Long getBasicPer() {
		return basicPer;
	}
	public void setBasicPer(Long basicPer) {
		this.basicPer = basicPer;
	}
	public Long getBmPer() {
		return bmPer;
	}
	public void setBmPer(Long bmPer) {
		this.bmPer = bmPer;
	}
	public Long getSuccessPer() {
		return successPer;
	}
	public void setSuccessPer(Long successPer) {
		this.successPer = successPer;
	}
	public String getUnyongName() {
		return unyongName;
	}
	public void setUnyongName(String unyongName) {
		this.unyongName = unyongName;
	}
	public String getCsGb() {
		return csGb;
	}
	public void setCsGb(String csGb) {
		this.csGb = csGb;
	}
	public Long getAek() {
		return aek;
	}
	public void setAek(Long aek) {
		this.aek = aek;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJjName() {
		return jjName;
	}
	public void setJjName(String jjName) {
		this.jjName = jjName;
	}
	public Date getGyulDate() {
		return gyulDate;
	}
	public void setGyulDate(Date gyulDate) {
		this.gyulDate = gyulDate;
	}

	

}
