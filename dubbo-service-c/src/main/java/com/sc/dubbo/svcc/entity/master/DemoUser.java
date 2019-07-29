package com.sc.dubbo.svcc.entity.master;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DemoUser {
    private Integer oidIndex;

    private String userId;

    private String password;

    private String userName;

    private String email;

    private String fkRole;

    private String userStatus;

    private Integer loginFailedCnt;

    private Date latestActiveTime;

    private Boolean paswdReset;

    private Date lastPaswdChanged;

    private Short versionId;

    private Date lastUpdBy;

    public Integer getOidIndex() {
        return oidIndex;
    }

    public void setOidIndex(Integer oidIndex) {
        this.oidIndex = oidIndex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getFkRole() {
        return fkRole;
    }

    public void setFkRole(String fkRole) {
        this.fkRole = fkRole == null ? null : fkRole.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public Integer getLoginFailedCnt() {
        return loginFailedCnt;
    }

    public void setLoginFailedCnt(Integer loginFailedCnt) {
        this.loginFailedCnt = loginFailedCnt;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getLatestActiveTime() {
        return latestActiveTime;
    }

    public void setLatestActiveTime(Date latestActiveTime) {
        this.latestActiveTime = latestActiveTime;
    }

    public Boolean getPaswdReset() {
        return paswdReset;
    }

    public void setPaswdReset(Boolean paswdReset) {
        this.paswdReset = paswdReset;
    }

    public Date getLastPaswdChanged() {
        return lastPaswdChanged;
    }

    public void setLastPaswdChanged(Date lastPaswdChanged) {
        this.lastPaswdChanged = lastPaswdChanged;
    }

    public Short getVersionId() {
        return versionId;
    }

    public void setVersionId(Short versionId) {
        this.versionId = versionId;
    }

    public Date getLastUpdBy() {
        return lastUpdBy;
    }

    public void setLastUpdBy(Date lastUpdBy) {
        this.lastUpdBy = lastUpdBy;
    }

	@Override
	public String toString() {
		return "DemoUser [oidIndex=" + oidIndex + ", userId=" + userId + ", password=" + password + ", userName="
				+ userName + ", email=" + email + ", fkRole=" + fkRole + ", userStatus=" + userStatus
				+ ", loginFailedCnt=" + loginFailedCnt + ", latestActiveTime=" + latestActiveTime + ", paswdReset="
				+ paswdReset + ", lastPaswdChanged=" + lastPaswdChanged + ", versionId=" + versionId + ", lastUpdBy="
				+ lastUpdBy + "]";
	}
    
    
}