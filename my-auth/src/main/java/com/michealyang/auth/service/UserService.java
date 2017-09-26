package com.michealyang.auth.service;

import com.michealyang.auth.dao.UserDao;
import com.michealyang.auth.domain.User;
import com.michealyang.commons.dto.Constants;
import com.michealyang.commons.dto.RespCodeEnum;
import com.michealyang.commons.dto.ResultDto;
import com.michealyang.commons.utils.EncryptUtil;
import com.michealyang.commons.utils.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by michealyang on 17/4/21.
 */
@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final int expire = 24 * 3600 * 3;

    @Resource
    private UserDao userDao;
    @Resource
    private UserServiceHelper userServiceHelper;

    public boolean checkUserName(String userName) {
        if(StringUtils.isBlank(userName)) {
            return false;
        }
        return userDao.getUserNameNum(userName) > 0;
    }

    public String getSaltByUserName(String userName) {
        //TODO: check param
        return userDao.getSaltByUserName(userName);
    }

    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    public ResultDto doSignup(String userName,
                              String passwd,
                              String repeatPasswd,
                              String telephone,
                              String email) {
        logger.info("[doSingup] userName=#{}, passwd=#{}, repeatPasswd=#{}, telephone=#{}, email=#{}",
                userName, passwd, repeatPasswd, telephone, email);
        ResultDto resultDto = userServiceHelper.checkSignup(userName,
                passwd, repeatPasswd);
        if(!resultDto.isSuccess()) return resultDto;
        String salt = userServiceHelper.genSalt(userName);

        User user = new User();
        user.setName(userName);
        user.setPasswd(EncryptUtil.MD5(passwd + salt));
        user.setTelephone(telephone);
        user.setEmail(email);
        user.setSalt(salt);

        try {
            if(userDao.insert(user) <= 0) {
                return new ResultDto(false, Constants.SYS_FAILURE);
            }
        }catch (DataAccessException e){
            logger.error("[doSiginup] e=#{}", e);
            return ResultUtil.genFailResult(RespCodeEnum.DUPLICATION, "用户名已被占用");
        }catch (Exception e){
            logger.error("[doSiginup] e=#{}", e);
            return ResultUtil.genFailResult(RespCodeEnum.SYS_ERROR);
        }

        return ResultUtil.genSuccessResult();
    }

    public ResultDto doLogin(String userName, String passwd) {
        logger.info("[doLogin] userName=#{}, passwd=#{}", userName, passwd);
        ResultDto resultDto = userServiceHelper.checkLogin(userName,
                passwd);
        if(!resultDto.isSuccess()) return resultDto;
        User user = getUserByUserName(userName);
        if(user == null) {
            logger.warn("[doLogin] 用户名不存在。userName=#{}", userName);
            return ResultUtil.genFailResult(RespCodeEnum.NOT_FOUND, "用户名或密码不正确");
        }

        if(!user.getPasswd().equals(EncryptUtil.MD5(passwd + user.getSalt()))){
            logger.warn("[doLogin] 密码不正确。userName=#{}", userName);
            return ResultUtil.genFailResult(RespCodeEnum.NOT_FOUND, "用户名或密码不正确");
        }

        return ResultUtil.genSuccessResult(user);
    }

}
