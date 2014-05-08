package com.hyq.lpg.entity;


public class Tanktransfer implements java.io.Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;
	 
	 private String tenantcode;
	 
	 private String officecode;
	 
	 private String officename;
	 
	 private String gpbm;   //钢瓶编码
	 
	 private String xpbm; //RFID芯片编码

	 private int ggxh; //规格型号，12kg,15kg

 	 private int czjz;  //充装介质
 	 
 	 private String jwd;  //经纬度
 	 
 	 private String dz;   //经纬读对应地址;

 	 private java.util.Date lzsj;  //流转时间
 	 
 	 private String czrcode;    //操作人编号
 	 		
 	 private String czrname;	//操作人名称
 	 
 	 private int lzzt;  // 1：门店重瓶入库,2:门店重瓶出库,3:送达,11:门店空瓶入库,12:门店空瓶出库,
 	 
 	 private String remark;   //备注信息
 	 
 	 private int del_flag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTenantcode() {
		return tenantcode;
	}

	public void setTenantcode(String tenantcode) {
		this.tenantcode = tenantcode;
	}

	public String getOfficecode() {
		return officecode;
	}

	public void setOfficecode(String officecode) {
		this.officecode = officecode;
	}

	public String getOfficename() {
		return officename;
	}

	public void setOfficename(String officename) {
		this.officename = officename;
	}

	public String getGpbm() {
		return gpbm;
	}

	public void setGpbm(String gpbm) {
		this.gpbm = gpbm;
	}

	public String getXpbm() {
		return xpbm;
	}

	public void setXpbm(String xpbm) {
		this.xpbm = xpbm;
	}

	public int getGgxh() {
		return ggxh;
	}

	public void setGgxh(int ggxh) {
		this.ggxh = ggxh;
	}

	public int getCzjz() {
		return czjz;
	}

	public void setCzjz(int czjz) {
		this.czjz = czjz;
	}

	public String getJwd() {
		return jwd;
	}

	public void setJwd(String jwd) {
		this.jwd = jwd;
	}

	public String getDz() {
		return dz;
	}

	public void setDz(String dz) {
		this.dz = dz;
	}

	public java.util.Date getLzsj() {
		return lzsj;
	}

	public void setLzsj(java.util.Date lzsj) {
		this.lzsj = lzsj;
	}

	public String getCzrcode() {
		return czrcode;
	}

	public void setCzrcode(String czrcode) {
		this.czrcode = czrcode;
	}

	public String getCzrname() {
		return czrname;
	}

	public void setCzrname(String czrname) {
		this.czrname = czrname;
	}

	public int getLzzt() {
		return lzzt;
	}

	public void setLzzt(int lzzt) {
		this.lzzt = lzzt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}

 	 
 	 
 	 
 	 
 	 
 	 
}
