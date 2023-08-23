package com.admarv.saas.user.domain;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.common.UserRoleConstant;
import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.mapper.SysUserRoleMapper;
import com.admarv.saas.mapper.UserAuthsMapper;
import com.admarv.saas.model.SysUser;
import com.admarv.saas.model.SysUserRole;
import com.admarv.saas.model.UserAuths;
import com.google.common.collect.Lists;

/**
 * 
 * @author liuliu
 *
 */
@Service
public class UserService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    
    @Autowired
    private UserAuthsMapper userAuthsMapper;
    
    /**
     * 获取跟单员角色用户
     */
    public List<SysUser> getLeadgenUsers() {
        List<SysUser> result = Lists.newArrayList();
        SysUserRole selectEntiy = new SysUserRole();
        selectEntiy.setRoleId(UserRoleConstant.ROLE_LEADER_MGR);
        List<SysUserRole> listSysUserRole = sysUserRoleMapper.selectByEntity(selectEntiy);
        log.info("listSysUserRole:{}", listSysUserRole);
        for (SysUserRole sysUserRole : listSysUserRole) {
            log.info("sysUserRole:{}", sysUserRole);
            String userId = sysUserRole.getUserId();
            SysUser selectSysUser = new SysUser();
            selectSysUser.setUserId(userId);
            SysUser sysUser = sysUserMapper.selectOneByEntity(selectSysUser);
            result.add(sysUser);
        }
        log.info("getUserList:{}", result);
        return result;
    }
    
    /**
     * 查询所有用户
     * 
     * @return
     */
    public List<SysUser> getUserList() {
        List<SysUser> listSysUser = sysUserMapper.selectAll();
        log.info("listSysUser:{}", listSysUser);
        return listSysUser;
    }
    
    /**
     * 添加跟单角色
     * @return
     */
    public int assignLeadRole(String userId) {
        SysUserRole selectEntiy = new SysUserRole();
        selectEntiy.setUserId(userId);
        SysUserRole updateSysUserRole = sysUserRoleMapper.selectOneByEntity(selectEntiy);
        //如果没有分配过角色
        if (updateSysUserRole == null) {
            SysUserRole insertSysUserRole = new SysUserRole();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            insertSysUserRole.setId(uuid);
            insertSysUserRole.setRoleId(UserRoleConstant.ROLE_LEADER_MGR);
            insertSysUserRole.setUserId(userId);
            int row = sysUserRoleMapper.insert(insertSysUserRole);
            log.info("success insert userId:{} lead role row:{}", userId, row);
            return row;
        //如果是分配过的角色
        } else {
            int row = sysUserRoleMapper.updateByPrimaryKey(updateSysUserRole);
            log.info("success update userId:{} lead role row:{}", userId, row);
            return row;
        }
    }
    
    /**
     * 删除询盘跟单角色
     * 
     * @return
     */
    public int deleteLeadRole(String userId) {
        SysUserRole selectEntiy = new SysUserRole();
        selectEntiy.setUserId(userId);
        SysUserRole updateSysUserRole = sysUserRoleMapper.selectOneByEntity(selectEntiy);
        String pk = updateSysUserRole.getId();
        int row = sysUserRoleMapper.deleteByPrimaryKey(pk);
        log.info("success assign lead role row:{}", row);
        return row;
    }
    
    /**
     * 检查用户是否授权过FB
     * 
     * @param userId
     * @return
     */
	public boolean getUserIsAuthedFB(String userId) {
		UserAuths selectEntity = new UserAuths();
		selectEntity.setUserId(userId);
		selectEntity.setPltfrm("FB");
		UserAuths userAuths = userAuthsMapper.selectOneByEntity(selectEntity);
		if (userAuths != null) {
			String pltfrm = userAuths.getPltfrm();
			if (StringUtils.isNotBlank(pltfrm)) {
				return true;
			}
		}
		return false;
	}
}
