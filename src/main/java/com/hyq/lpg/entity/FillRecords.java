package com.hyq.lpg.entity;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hyq.lpg.common.Page;
/**
 * 充装记录映射实体
 * @author 王庆华
 * @param <T>
 */ 
@Entity
@Table(name = "fill_records")
public class FillRecords<T> implements java.io.Serializable {
	private static final long serialVersionUID = -1855633224204242444L;
	protected Long id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	@Column(name = "tenant_code", length = 20)
	private String tenantcode;	//所属租户编码
	@Column(name = "office_code", length = 20)
	private String officecode;  //所属机构编码
	@Column(name = "rfid_code", length = 20)
	private String rfidcode;	// RPID芯片编码
	@Column(name = "BARCODE", length = 30)
	private String barcode;		// 一维码
	@Column(name = "recid", length = 20)
	private Long recid;		    // 采集器序号
	@Column(name = "cjqbm", length = 20)
	private Integer cjqbm;	    // 采集器编码
	@Column(name = "gpbm", length = 15)
	private String gpbm;	    // 钢瓶编码
	@Column(name = "zdbm", length = 15)
	private String zdbm;	// 站点编码
	@Column(name = "kfbm", length = 15)
	private String kfbm;	// 客户编码
	@Column(name = "czkssj")
	private java.util.Date czkssj;// 充装开始时间
	@Column(name = "czyxsj")
	private Integer czyxsj;// 充装延续时间
	@Column(name = "cszl")
	private Double cszl;   // 初始重量
	@Column(name = "czjz")
	private Double czjz;   // 充装净重
	@Column(name = "mbzl")
	private Double mbzl;    // 目标重量
	@Column(name = "CZZT")
	private Integer czzt;    // 充装状态0 有效数据 ,1：充装失败数据,2：其他
	@Column(name = "resouce")
	private Integer resouce; // 充装数据来源充装记录来源,1:MQ,2:常龙电子称
	@Column(name="ggxh",length=5)
	private String ggxh;     //规格型号，12kg,15kg
	@Column(name="del_flag")
	private int del_flag;// 是否删除     0 :启用   1：已经删除

	
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
	
	public FillRecords(){};
	public FillRecords(String tenantcode){
		this.tenantcode = tenantcode;
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





	public String getRfidcode() {
		return rfidcode;
	}





	public void setRfidcode(String rfidcode) {
		this.rfidcode = rfidcode;
	}





	public String getBarcode() {
		return barcode;
	}





	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}





	public Long getRecid() {
		return recid;
	}





	public void setRecid(Long recid) {
		this.recid = recid;
	}





	




	public Integer getCjqbm() {
		return cjqbm;
	}





	public void setCjqbm(Integer cjqbm) {
		this.cjqbm = cjqbm;
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





	public String getKfbm() {
		return kfbm;
	}





	public void setKfbm(String kfbm) {
		this.kfbm = kfbm;
	}





	public Integer getCzyxsj() {
		return czyxsj;
	}





	public void setCzyxsj(Integer czyxsj) {
		this.czyxsj = czyxsj;
	}





	public Double getCszl() {
		return cszl;
	}





	public void setCszl(Double cszl) {
		this.cszl = cszl;
	}





	public Double getCzjz() {
		return czjz;
	}





	public void setCzjz(Double czjz) {
		this.czjz = czjz;
	}





	public Double getMbzl() {
		return mbzl;
	}





	public void setMbzl(Double mbzl) {
		this.mbzl = mbzl;
	}





	public Integer getCzzt() {
		return czzt;
	}





	public void setCzzt(Integer czzt) {
		this.czzt = czzt;
	}





	public Integer getResouce() {
		return resouce;
	}





	public void setResouce(Integer resouce) {
		this.resouce = resouce;
	}





	public String getGgxh() {
		return ggxh;
	}





	public void setGgxh(String ggxh) {
		this.ggxh = ggxh;
	}





	public int getDel_flag() {
		return del_flag;
	}





	public void setDel_flag(int del_flag) {
		this.del_flag = del_flag;
	}





	public void setId(Long id) {
		this.id = id;
	}





	public void setCzkssj(java.util.Date czkssj) {
		this.czkssj = czkssj;
	}





	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public java.util.Date getCzkssj() {
		return czkssj;
	}
	
}