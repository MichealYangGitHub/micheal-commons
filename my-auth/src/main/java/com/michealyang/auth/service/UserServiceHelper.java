package com.michealyang.auth.service;

import com.google.common.base.Preconditions;
import com.michealyang.auth.domain.User;
import com.michealyang.commons.dto.Constants;
import com.michealyang.commons.dto.ResultDto;
import com.michealyang.commons.utils.DateUtil;
import com.michealyang.commons.utils.EncryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by michealyang on 17/4/21.
 */
@Service
public class UserServiceHelper {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceHelper.class);

    public ResultDto checkSignup(String name,
                                 String passwd,
                                 String repeatPasswd) {
        if(StringUtils.isBlank(name)){
            return new ResultDto(false, "用户名不能为空");
        }
        if(StringUtils.isBlank(passwd) || StringUtils.isBlank(repeatPasswd)){
            return new ResultDto(false, "密码和密码确认不能为空");
        }
        if(!passwd.equals(repeatPasswd)){
            return new ResultDto(false, "密码不一致");
        }

        return new ResultDto(true, Constants.SUCCESS);
    }

    public ResultDto checkLogin(String userName,
                                 String passwd) {
        if(StringUtils.isBlank(userName)){
            return new ResultDto(false, "用户名不能为空");
        }
        if(StringUtils.isBlank(passwd)){
            return new ResultDto(false, "密码不能为空");
        }

        return new ResultDto(true, Constants.SUCCESS);
    }

    public String genSalt(String userName) {
        //TODO: 修改Salt策略
        return userName + DateUtil.currentSecond().toString();
    }

    /**
     * 生成一个Token
     * <p>token生成规则：</p>
     * <p>userName+当前Unix timestamp，然后计算MD5值</p>
     * @param userName
     * @return
     */
    public String genToken(String userName){
        if(StringUtils.isBlank(userName)) return "";
        return EncryptUtil.MD5(userName + DateUtil.now());
    }

    /**
     * 生成用户缓存和传递给第三方的user信息
     * <p>只包含userName和id</p>
     * @param origin
     * @return
     */
    public User genCachedUser(User origin){
        Preconditions.checkArgument(origin != null);
        User user = new User();
        user.setId(origin.getId());
        user.setName(origin.getName());
        return user;
    }
}
