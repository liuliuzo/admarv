package com.admarv.saas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 询盘单实体类
 * 
 * @author liuliu
 *
 */
public class LeadGen implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -8187470259614523213L;

    /**
     * 询盘单ID自增主键
     */
    private Integer id;
    
    /**
     * 广告线索询盘Id
     */
    private String leadId;
    
    /**
     * 姓名
     */
    private String name;

    /**
     * 邮件
     */
    private String email;

    /**
     * 联系方式
     */
    private String cntct;

    /**
     * 创建时间
     */
    private String crteTm;

    /**
     * 阅读状态：01-已读，02-未读
     */
    private String leadStat;

    /**
     * 国家
     */
    private String regn;

    /**
     * 跟踪状态：01-待跟进，02-已沟通，03-已报价，04-已成交
     */
    private String flwpStat;

    /**
     * 来源
     */
    private String rsrc;

    /**
     * 表单编号
     */
    private String formId;

    private String owner;

    private String userName;

    private Boolean delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId == null ? null : leadId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCntct() {
        return cntct;
    }

    public void setCntct(String cntct) {
        this.cntct = cntct == null ? null : cntct.trim();
    }

    public String getCrteTm() {
        return crteTm;
    }

    public void setCrteTm(String crteTm) {
        this.crteTm = crteTm == null ? null : crteTm.trim();
    }

    public String getLeadStat() {
        return leadStat;
    }

    public void setLeadStat(String leadStat) {
        this.leadStat = leadStat == null ? null : leadStat.trim();
    }

    public String getRegn() {
        return regn;
    }

    public void setRegn(String regn) {
        this.regn = regn == null ? null : regn.trim();
    }

    public String getFlwpStat() {
        return flwpStat;
    }

    public void setFlwpStat(String flwpStat) {
        this.flwpStat = flwpStat == null ? null : flwpStat.trim();
    }

    public String getRsrc() {
        return rsrc;
    }

    public void setRsrc(String rsrc) {
        this.rsrc = rsrc == null ? null : rsrc.trim();
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId == null ? null : formId.trim();
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", leadId=").append(leadId);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", cntct=").append(cntct);
        sb.append(", crteTm=").append(crteTm);
        sb.append(", leadStat=").append(leadStat);
        sb.append(", regn=").append(regn);
        sb.append(", flwpStat=").append(flwpStat);
        sb.append(", rsrc=").append(rsrc);
        sb.append(", formId=").append(formId);
        sb.append(", owner=").append(owner);
        sb.append(", userName=").append(userName);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}