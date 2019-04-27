package com.task.entity.zhuseroffer;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;
/*
 * 样品表 ta_Simple
 * */
import com.task.util.FieldMeta;

public class Sample implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name="图纸")
	private String status;//状态 
	@FieldMeta(name="样品")
	private String yangPin;//样品
	//图纸 
	@FieldMeta(name="图纸 ")
	private String pic;
	private File picF;
	private String  picFContentType;
	private String picFFileName;
	//检验报告
	@FieldMeta(name="检验报告")
	private String checkNote;
	private File checkNoteF;
	private String checkNoteFContentType;
	private String checkNoteFFileName;
	//材质证明
	@FieldMeta(name="图纸")
	private String caiZhi;
	private File caiZhiF;
	private String caiZhiFContentType;
	private String caiZhiFFileName;
	//环保报告
	@FieldMeta(name="环保报告")
	private String huanBao;
	private File huanBaoF;
	private String huanBaoFContentType;
	private String huanBaoFFileName;
	//材料性能
	@FieldMeta(name="材料性能")
	private String caiLiao;
	private File caiLiaoF;
	private String caiLiaoFContentType;
	private String caiLiaoFFileName;
	//盐雾检验
	@FieldMeta(name="盐雾检验")
	private String yanWu;
	private File yanWuF;
	private String yanWuFContentType;
	private String yanWuFFileName;
	//模具认证资料
	@FieldMeta(name="模具认证资料")
	private String moJuRenZhen;
	private File moJuRenZhenF;
	private String moJuRenZhenFContentType;
	private String moJuRenZhenFFileName;
	//工艺认证
	@FieldMeta(name="工艺认证")
	private String gongYi;
	private File gongYiF;
	private String gongYiFContentType;
	private String gongYiFFileName;
	private ZhuserOffer zhuserOffer;//报价表
	private Integer epId;//流程id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getYangPin() {
		return yangPin;
	}
	public void setYangPin(String yangPin) {
		this.yangPin = yangPin;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getCheckNote() {
		return checkNote;
	}
	public void setCheckNote(String checkNote) {
		this.checkNote = checkNote;
	}
	public String getCaiZhi() {
		return caiZhi;
	}
	public void setCaiZhi(String caiZhi) {
		this.caiZhi = caiZhi;
	}
	public String getHuanBao() {
		return huanBao;
	}
	public void setHuanBao(String huanBao) {
		this.huanBao = huanBao;
	}
	public String getCaiLiao() {
		return caiLiao;
	}
	public void setCaiLiao(String caiLiao) {
		this.caiLiao = caiLiao;
	}
	public String getYanWu() {
		return yanWu;
	}
	public void setYanWu(String yanWu) {
		this.yanWu = yanWu;
	}
	public String getMoJuRenZhen() {
		return moJuRenZhen;
	}
	public void setMoJuRenZhen(String moJuRenZhen) {
		this.moJuRenZhen = moJuRenZhen;
	}
	public String getGongYi() {
		return gongYi;
	}
	public void setGongYi(String gongYi) {
		this.gongYi = gongYi;
	}
	@JSONField(serialize = false)
	public ZhuserOffer getZhuserOffer() {
		return zhuserOffer;
	}
	public void setZhuserOffer(ZhuserOffer zhuserOffer) {
		this.zhuserOffer = zhuserOffer;
	}
	public File getCheckNoteF() {
		return checkNoteF;
	}
	public void setCheckNoteF(File checkNoteF) {
		this.checkNoteF = checkNoteF;
	}
	public File getCaiZhiF() {
		return caiZhiF;
	}
	public void setCaiZhiF(File caiZhiF) {
		this.caiZhiF = caiZhiF;
	}
	public String getCheckNoteFFileName() {
		return checkNoteFFileName;
	}
	public void setCheckNoteFFileName(String checkNoteFFileName) {
		this.checkNoteFFileName = checkNoteFFileName;
	}
	public File getPicF() {
		return picF;
	}
	public void setPicF(File picF) {
		this.picF = picF;
	}
	public String getPicFFileName() {
		return picFFileName;
	}
	public void setPicFFileName(String picFFileName) {
		this.picFFileName = picFFileName;
	}
	public String getCaiZhiFFileName() {
		return caiZhiFFileName;
	}
	public void setCaiZhiFFileName(String caiZhiFFileName) {
		this.caiZhiFFileName = caiZhiFFileName;
	}
	public File getHuanBaoF() {
		return huanBaoF;
	}
	public void setHuanBaoF(File huanBaoF) {
		this.huanBaoF = huanBaoF;
	}
	public String getHuanBaoFFileName() {
		return huanBaoFFileName;
	}
	public void setHuanBaoFFileName(String huanBaoFFileName) {
		this.huanBaoFFileName = huanBaoFFileName;
	}
	public File getCaiLiaoF() {
		return caiLiaoF;
	}
	public void setCaiLiaoF(File caiLiaoF) {
		this.caiLiaoF = caiLiaoF;
	}
	public String getCaiLiaoFFileName() {
		return caiLiaoFFileName;
	}
	public void setCaiLiaoFFileName(String caiLiaoFFileName) {
		this.caiLiaoFFileName = caiLiaoFFileName;
	}
	public File getYanWuF() {
		return yanWuF;
	}
	public void setYanWuF(File yanWuF) {
		this.yanWuF = yanWuF;
	}
	public String getYanWuFFileName() {
		return yanWuFFileName;
	}
	public void setYanWuFFileName(String yanWuFFileName) {
		this.yanWuFFileName = yanWuFFileName;
	}
	public File getMoJuRenZhenF() {
		return moJuRenZhenF;
	}
	public void setMoJuRenZhenF(File moJuRenZhenF) {
		this.moJuRenZhenF = moJuRenZhenF;
	}
	public String getMoJuRenZhenFFileName() {
		return moJuRenZhenFFileName;
	}
	public void setMoJuRenZhenFFileName(String moJuRenZhenFFileName) {
		this.moJuRenZhenFFileName = moJuRenZhenFFileName;
	}
	public File getGongYiF() {
		return gongYiF;
	}
	public void setGongYiF(File gongYiF) {
		this.gongYiF = gongYiF;
	}
	public String getGongYiFFileName() {
		return gongYiFFileName;
	}
	public void setGongYiFFileName(String gongYiFFileName) {
		this.gongYiFFileName = gongYiFFileName;
	}
	public String getCaiZhiFContentType() {
		return caiZhiFContentType;
	}
	public void setCaiZhiFContentType(String caiZhiFContentType) {
		this.caiZhiFContentType = caiZhiFContentType;
	}
	public String getPicFContentType() {
		return picFContentType;
	}
	public void setPicFContentType(String picFContentType) {
		this.picFContentType = picFContentType;
	}
	public String getCheckNoteFContentType() {
		return checkNoteFContentType;
	}
	public void setCheckNoteFContentType(String checkNoteFContentType) {
		this.checkNoteFContentType = checkNoteFContentType;
	}
	public String getHuanBaoFContentType() {
		return huanBaoFContentType;
	}
	public void setHuanBaoFContentType(String huanBaoFContentType) {
		this.huanBaoFContentType = huanBaoFContentType;
	}
	public String getCaiLiaoFContentType() {
		return caiLiaoFContentType;
	}
	public void setCaiLiaoFContentType(String caiLiaoFContentType) {
		this.caiLiaoFContentType = caiLiaoFContentType;
	}
	public String getYanWuFContentType() {
		return yanWuFContentType;
	}
	public void setYanWuFContentType(String yanWuFContentType) {
		this.yanWuFContentType = yanWuFContentType;
	}
	public String getMoJuRenZhenFContentType() {
		return moJuRenZhenFContentType;
	}
	public void setMoJuRenZhenFContentType(String moJuRenZhenFContentType) {
		this.moJuRenZhenFContentType = moJuRenZhenFContentType;
	}
	public String getGongYiFContentType() {
		return gongYiFContentType;
	}
	public void setGongYiFContentType(String gongYiFContentType) {
		this.gongYiFContentType = gongYiFContentType;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	
	
	
}
