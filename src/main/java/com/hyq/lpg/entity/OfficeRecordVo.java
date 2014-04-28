package com.hyq.lpg.entity;


public class OfficeRecordVo {

	private String officecode; 
	private String officename;
	private int filltotal;
	private int gastotl;

	public String getOfficecode() {
		return officecode;
	}

	public void setOfficecode(String officecode) {
		this.officecode = officecode;
		
		
		
	//	this.setOfficename((String) CasUtils.getCurrentPrincipal().getOffices().get(this.officecode));
		
		
		
	}

	

	public int getFilltotal() {
		return filltotal;
	}

	public void setFilltotal(int filltotal) {
		this.filltotal = filltotal;
	}

	public int getGastotl() {
		return gastotl;
	}

	public void setGastotl(int gastotl) {
		this.gastotl = gastotl;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}
	
	
}
