package myApp.client.vi.cst.model;

import java.util.Date;

import com.sencha.gxt.widget.core.client.info.Info;

import myApp.client.utils.GridDataModel;

public class Cst01_UserModel implements GridDataModel {

	private	Long	userId;
	private	String	email;
	private	String	userName;
	private	String	phoneNo;
	private	String	phoneNo1;
	private	String	phoneNo2;
	private	String	phoneNo3;
	private	String	zipNo;
	private	String	zipAddress;
	private	String	refreshTime;
	private	Date	startDt;
	private	Date	endDt;
	private	String	mrdRole;
	private	Long	companyId;
	
	private String	emailChk;

	@Override
	public void setKeyId(Long id) {
		this.setUserId(id);
	}

	@Override
	public Long getKeyId() {
		return this.getUserId();
	}

	public	Long getUserId() {
		return	userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getZipNo() {
		return zipNo;
	}

	public void setZipNo(String zipNo) {
		this.zipNo = zipNo;
	}

	public String getZipAddress() {
		return zipAddress;
	}

	public void setZipAddress(String zipAddress) {
		this.zipAddress = zipAddress;
	}

	public String getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(String refreshTime) {
		this.refreshTime = refreshTime;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public Date getEndDt() {
		return endDt;
	}

	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getMrdRole() {
		return mrdRole;
	}

	public void setMrdRole(String mrdRole) {
		this.mrdRole = mrdRole;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getPhoneNo1() {
		String phoneNo = getPhoneNo();
		return phoneNo.substring(0,3);
	}

	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}

	public String getPhoneNo2() {
		String phoneNo = getPhoneNo();
		return phoneNo.substring(3,phoneNo.length()-4);
	}

	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}

	public String getPhoneNo3() {
		String phoneNo = getPhoneNo();
		return phoneNo.substring(phoneNo.length()-4, phoneNo.length());
	}

	public void setPhoneNo3(String phoneNo3) {
		this.phoneNo3 = phoneNo3;
	}

	public String getEmailChk() {
		return emailChk;
	}

	public void setEmailChk(String emailChk) {
		this.emailChk = emailChk;
	}

}