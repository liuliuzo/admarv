package com.admarv.saas.fb.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.model.SysUser;

/**
 * @author liuliu
 *
 */
@Service
public class UserLoginService {

    private static final Logger log = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    private SysUserMapper sysUserMapper;

    public SysUser login(String userNam, String pswrd) {
        log.info("username:{}, password:{}", userNam, pswrd);
        SysUser selectEntity = new SysUser();
        selectEntity.setUserName(userNam);
        selectEntity.setPassword(pswrd);
        SysUser sysUser = sysUserMapper.selectOneByEntity(selectEntity);
        log.info("sysUser:{}", sysUser);
        return sysUser;
    }
}
