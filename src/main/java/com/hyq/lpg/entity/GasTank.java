package com.hyq.lpg.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hyq.lpg.common.Page;

public class GasTank<T> implements java.io.Serializable{
	private static final long serialVersionUID = -5579791647847368079L;
	protected Long id;
	private String tenantcode;	//所属租户编码
	private String officecode;  //所属机构编码
	private String gpbm;   //钢瓶编码
	private String xpbm; //RFID芯片编码
	private Date jdsj;//建档时间
	private String jdr;//建档人
	private Date updateDate;//修改时间
	private String gpid; //全局一维码
	private String barcode; //一维码
	private String zdbm;  //(站点编码)
	private String khbm;//(客户编码)
	@Column(name="scrq")
	@DateTimeFormat(iso=ISO.DATE)
	private java.util.Date scrq; //生产日期
	@Column(name="sccj",length=10)
	private String sccj;   //钢瓶生产厂家编码
	@Column(name="fpsj")
	private java.util.Date fpsj; //发瓶时间
	@Column(name="fpr",length=10)  
	private String fpr;  //发瓶人
	@Column(name="ggxh",length=5)
	private String ggxh; //规格型号，12kg,15kg
	@Column(name="medium",length=5)
	private String medium;  //充装介质
	@Column(name="gplx", columnDefinition="number(1,0) default 0")
	private Integer gplx; //钢瓶类型 (0:普通瓶1:智能瓶) 有rfid的为智能瓶
	@Column(name="pz")  
	private Double pz;		//皮重
	@Column(name="synx")
	private Integer synx;  //使用年限
	@Column(name="qjzq")
	private Integer qjzq;  //强检查周期
	@Column(name="first_czsj")
	private java.util.Date firstCzsj; //最后充装时间
	@Column(name="czsj")
	private java.util.Date czsj; //最后充装时间
	@Column(name="jdfs", columnDefinition="number(1,0) default 0")
	private Integer jdfs;   //建档方式 (0:PDA 建档)
	@Column(name="ywzt", columnDefinition="number(4,3,2,1,0) default 0")
	private Integer ywzt;   //业务流转平台状态
	@Column(name="gpzt", columnDefinition="number(4,3,2,1,0) default 0")
	private Integer gpzt;  //钢瓶状态
	@Column(name="zzcs", columnDefinition="default 0")
	private Integer zzcs;  //充装次数
	private Integer lsts;   //流失天数
	public Integer getLsts() {
		return lsts;
	}
	public void setLsts(Integer lsts) {
		this.lsts = lsts;
	}

	@Column(name="last_check_time")
	@DateTimeFormat(iso=ISO.DATE)
	private java.util.Date lastCheckTime;//上次检验日期
	@Column(name="next_check_time")
	@DateTimeFormat(iso=ISO.DATE)
	private java.util.Date nextCheckTime;//下次检验日期
	@Column(name="remarks")
	private String remarks;//备注
	@Column(name="del_flag")
	private Integer del_flag;// 是否删除
	
	protected Page<T> page;
	
	@JsonIgnore
	@XmlTransient
	@Transient
	public Page<T> getPage() {
		if (page == null){
			page = new Page<T>();
		}
		return page;
	}
	
	public Page<T> setPage(Page<T> page) {
		this.page = page;
		return page;
	}
	
	public GasTank() {
		super();
	}
	
	public GasTank(String tenantcode) {
		super();
		this.tenantcode = tenantcode;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public String getXpbm() {
		return xpbm;
	}

	public void setXpbm(String xpbm) {
		this.xpbm = xpbm;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+08:00")
	public Date getJdsj() {
		return jdsj;
	}

	public void setJdsj(Date jdsj) {
		this.jdsj = jdsj;
	}

	public String getJdr() {
		return jdr;
	}

	public void setJdr(String jdr) {
		this.jdr = jdr;
	}

	public Integer getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(Integer del_flag) {
		this.del_flag = del_flag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getGpid() {
		return gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getGpbm() {
		return gpbm;
	}

	public void setGpbm(String gpbm) {
		this.gpbm = gpbm;
	}

	public String getZdbm() {
		return zdbm;
	}

	public void setZdbm(String zdbm) {
		this.zdbm = zdbm;
	}

	public String getKhbm() {
		return khbm;
	}

	public void setKhbm(String khbm) {
		this.khbm = khbm;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+08:00")
	public java.util.Date getScrq() {
		return scrq;
	}

	public void setScrq(java.util.Date scrq) {
		this.scrq = scrq;
	}

	public String getSccj() {
		return sccj;
	}

	public void setSccj(String sccj) {
		this.sccj = sccj;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
	public java.util.Date getFpsj() {
		return fpsj;
	}

	public void setFpsj(java.util.Date fpsj) {
		this.fpsj = fpsj;
	}

	public String getFpr() {
		return fpr;
	}

	public void setFpr(String fpr) {
		this.fpr = fpr;
	}

	public String getGgxh() {
		return ggxh;
	}

	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public Integer getGplx() {
		return gplx;
	}

	public void setGplx(Integer gplx) {
		this.gplx = gplx;
	}

	public Double getPz() {
		return pz;
	}

	public void setPz(Double pz) {
		this.pz = pz;
	}

	public Integer getSynx() {
		return synx;
	}

	public void setSynx(Integer synx) {
		this.synx = synx;
	}

	public Integer getQjzq() {
		return qjzq;
	}

	public void setQjzq(Integer qjzq) {
		this.qjzq = qjzq;
	}
	
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
	public java.util.Date getFirstCzsj() {
		return firstCzsj;
	}

	public void setFirstCzsj(java.util.Date firstCzsj) {
		this.firstCzsj = firstCzsj;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+08:00")
	public java.util.Date getCzsj() {
		return czsj;
	}

	public void setCzsj(java.util.Date czsj) {
		this.czsj = czsj;
	}

	public Integer getJdfs() {
		return jdfs;
	}

	public void setJdfs(Integer jdfs) {
		this.jdfs = jdfs;
	}

	public Integer getYwzt() {
		return ywzt;
	}

	public void setYwzt(Integer ywzt) {
		this.ywzt = ywzt;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+08:00")
	public java.util.Date getLastCheckTime() {
		return lastCheckTime;
	}
	
	public void setLastCheckTime(java.util.Date lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+08:00")
	public java.util.Date getNextCheckTime() {
		return nextCheckTime;
	}

	public void setNextCheckTime(java.util.Date nextCheckTime) {
		this.nextCheckTime = nextCheckTime;
	}

	public Integer getGpzt() {
		return gpzt;
	}

	public void setGpzt(Integer gpzt) {
		this.gpzt = gpzt;
	}

	public Integer getZzcs() {
		return zzcs;
	}

	public void setZzcs(Integer zzcs) {
		this.zzcs = zzcs;
	}
	
}
