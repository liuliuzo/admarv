package com.admarv.saas.user.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.admarv.saas.fb.common.Response;
import com.admarv.saas.fb.common.dto.req.ReqLogin;
import com.admarv.saas.fb.common.dto.req.ReqOauthDelete;
import com.admarv.saas.fb.common.dto.resp.RespLogin;
import com.admarv.saas.fb.common.dto.resp.RespOauthInfo;
import com.admarv.saas.fb.common.dto.resp.RespSaasInfo;
import com.admarv.saas.mapper.SysRoleMapper;
import com.admarv.saas.mapper.SysUserFbBindMapper;
import com.admarv.saas.mapper.SysUserRoleMapper;
import com.admarv.saas.mapper.UserAuthsMapper;
import com.admarv.saas.model.SysRole;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserFbBind;
import com.admarv.saas.model.SysUserRole;
import com.admarv.saas.model.UserAuths;
import com.admarv.saas.user.domain.UserLoginService;
import com.admarv.saas.utils.JacksonUtils;
import com.admarv.saas.utils.JwtUtils;
import com.google.api.client.util.Lists;

/**
 * 用户登录
 * 
 * @author liuliu
 *
 */
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserLoginService userLoginService;

    @Autowired
    private UserAuthsMapper userAuthsMapper;
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private SysUserFbBindMapper sysUserFbBindMapper;
    
    /**
     * 用户登录
     * 
     * @param user
     * @return
     */
	@PostMapping("/admarv/login")
	public String login(@RequestBody ReqLogin reqLogin) {
		log.info("/admarv/login user:{}", reqLogin);
		String userName = reqLogin.getUserName();
		String pwd = reqLogin.getPswrd();
		SysUser sysUser = userLoginService.login(userName, pwd);
		RespLogin respLogin = new RespLogin();
		//密码正确
		if (sysUser != null) {
			String userId = sysUser.getUserId();
			respLogin.setUserId(userId);
			respLogin.setUserName(sysUser.getUserName());	
			String token = JwtUtils.generateToken(userId);
			respLogin.setToken(token);
			SysUserRole selSysUserRole = new SysUserRole();
			selSysUserRole.setUserId(userId);
			SysUserRole sysUserRole = sysUserRoleMapper.selectOneByEntity(selSysUserRole);
			String roleId = sysUserRole.getRoleId();
			SysRole selSysRole = new SysRole();
			selSysRole.setId(roleId);
			SysRole sysRole = sysRoleMapper.selectOneByEntity(selSysRole);
			String roleCode = sysRole.getRoleCode();
			String roleName = sysRole.getRoleName();
			respLogin.setRoleCode(roleCode);
			respLogin.setRoleName(roleName);
			Response response = new Response();
			response.setCode("200");
			response.setResult(respLogin);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		} else { //密码错误
			Response response = new Response();
			response.setCode("1001");
			response.setMessage("账号密码错误");
			response.setSuccess(false);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
	}
    
    /**
     * 获取用户是否授权
     * 
     * @param user
     * @return
     */
	@GetMapping("/admarv/oauthInfo")
	public String oauthInfo(String userId) {
		log.info("/admarv/oauthInfo userId:{}", userId);
		UserAuths selectEntiy = new UserAuths();
		selectEntiy.setUserId(userId);
		List<UserAuths> userAuthsList = userAuthsMapper.selectByEntity(selectEntiy);
		log.info("userAuths:{}", userAuthsList);
		List<RespOauthInfo> result = Lists.newArrayList();
		for (UserAuths userAuths : userAuthsList) {
			RespOauthInfo respOauthInfo = new RespOauthInfo();
			respOauthInfo.setUserId(userAuths.getUserId());
			respOauthInfo.setPltfrm(userAuths.getPltfrm());
			result.add(respOauthInfo);
		}
		Response response = new Response();
		response.setCode("200");
		response.setResult(result);
		response.setSuccess(true);
		String srtResponse = JacksonUtils.toJson(response);
		log.info(srtResponse);
		return srtResponse;
	}

    /**
     * 取消授权用户
     * 
     * @param user
     * @return
     */
    @PostMapping("/admarv/fbOauthDelete")
    public String fbOauthDelete(@RequestBody ReqOauthDelete reqOauthDelete) {
        log.info("/admarv/fbOauthDelete reqOauthDelete:{}", reqOauthDelete);
        UserAuths selectEntiy = new UserAuths();
        selectEntiy.setUserId(reqOauthDelete.getUserId());
        selectEntiy.setPltfrm(reqOauthDelete.getPltfrm());
        UserAuths userAuths = userAuthsMapper.selectOneByEntity(selectEntiy);
        log.info("userAuths:{}", userAuths);
        int id = userAuths.getId();
        int row = userAuthsMapper.deleteByPrimaryKey(id);
        String desc = String.format("delete userAuths rows %d", row);
        //删除绑定信息
        SysUserFbBind selectEntity = new SysUserFbBind();
        selectEntity.setUserId(reqOauthDelete.getUserId());
        SysUserFbBind sysUserFbBind = sysUserFbBindMapper.selectOneByEntity(selectEntity);
		if (sysUserFbBind != null) {
			int fbBindId = sysUserFbBind.getId();
			int row2 = sysUserFbBindMapper.deleteByPrimaryKey(fbBindId);
			String desc2 = String.format("delete userAuths rows %d", row2);
			Response response = new Response();
			response.setCode("200");
			response.setResult(desc + " and " + desc2);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		} else {
			Response response = new Response();
			response.setCode("200");
			response.setResult(desc);
			response.setSuccess(true);
			String srtResponse = JacksonUtils.toJson(response);
			log.info(srtResponse);
			return srtResponse;
		}
    }

}
