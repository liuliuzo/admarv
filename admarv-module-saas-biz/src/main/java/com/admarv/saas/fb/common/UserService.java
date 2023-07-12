package com.admarv.saas.fb.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admarv.saas.mapper.SysUserMapper;
import com.admarv.saas.model.SysUser;

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

    public List<SysUser> getUserList() {
        List<SysUser> listSysUser = sysUserMapper.selectAll();
        log.info("listSysUser:{}", listSysUser);
        return listSysUser;
    }
}
