package com.admarv.saas.fb.common.dto.resp;

/**
 * 
 * @author liuliu
 *
 */
public class RespLogin {

    private String userId;
    private String userName;
    private String token;
    private String roleCode;
    private String roleName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RespLogin [userId=" + userId + ", userName=" + userName + ", token=" + token + ", roleCode=" + roleCode
				+ ", roleName=" + roleName + "]";
	}

}
