package com.michealyang.auth.controller;

import com.michealyang.auth.domain.User;
import com.michealyang.auth.service.UserService;
import com.michealyang.auth.util.UserUtil;
import com.michealyang.commons.dto.Constants;
import com.michealyang.commons.dto.ResultDto;
import com.michealyang.commons.utils.JsonResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by michealyang on 17/5/3.
 */
@Controller
@RequestMapping("/auth/api/user")
public class AuthUserApiController {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping("/r/auth")
    public Object auth(){
        User user = UserUtil.getUser();
        logger.info("[auth] user=#{}", user);
        return JsonResponseUtil.successResp(user);
    }

    @ResponseBody
    @RequestMapping("/w/signup")
    public Object doSignup(String userName,
                           String passwd,
                           String repeatPasswd,
                           String telephone,
                           String email) {
        logger.info("[doSignup] userName=#{}, passwd=#{}, repeatPasswd=#{}, telephone=#{}, email=#{}",
                userName, passwd, repeatPasswd, telephone, email);
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)) {
            return JsonResponseUtil.failureResp("请填写用户名和密码", null);
        }
        ResultDto resultDto = userService.doSignup(
                userName,
                passwd,
                repeatPasswd,
                telephone,
                email);

        if(!resultDto.isSuccess()) {
            return JsonResponseUtil.failureResp(resultDto.getCode(), resultDto.getMsg());
        }
        return JsonResponseUtil.successResp();
    }

    @ResponseBody
    @RequestMapping("/r/doLogin")
    public Object doLogin(HttpServletRequest request,
                          HttpServletResponse response,
                          String userName,
                          String passwd,
                          String origin) throws IOException {
        logger.info("[doLogin] userName=#{}, passwd=#{}, origin=#{}", userName, passwd, origin);
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(passwd)) {
            return JsonResponseUtil.failureResp("请填写用户名和密码", null);
        }
        ResultDto resultDto = userService.doLogin(userName, passwd);
        if(!resultDto.isSuccess()) {
            return JsonResponseUtil.failureResp(resultDto.getMsg(), null);
        }
        User user = (User)resultDto.getData();
        HttpSession session = request.getSession(true);
        session.setAttribute("user", user);

        return JsonResponseUtil.successResp();
    }


    @ResponseBody
    @RequestMapping("/r/checkDuplicate")
    public Object checkDuplicate(String userName){
        logger.info("[checkDuplicate] userName=#{}", userName);
        if(StringUtils.isBlank(userName)) {
            return JsonResponseUtil.successResp("用户名为空，不理会", null);
        }
        if(userService.checkUserName(userName)) {
            return JsonResponseUtil.failureResp(Constants.SSO_DUPLICATE_USERNAME_ERR, null);
        }else{
            return JsonResponseUtil.successResp(Constants.SUCCESS, null);
        }
    }
}
