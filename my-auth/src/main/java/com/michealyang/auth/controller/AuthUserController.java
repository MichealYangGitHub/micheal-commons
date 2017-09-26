package com.michealyang.auth.controller;

import com.michealyang.auth.domain.User;
import com.michealyang.auth.service.UserService;
import com.michealyang.auth.service.UserServiceHelper;
import com.michealyang.auth.util.UserUtil;
import com.michealyang.commons.dto.Constants;
import com.michealyang.commons.utils.JsonResponseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/auth/user")
public class AuthUserController {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserController.class);

    //固定盐值，就是随便按的字母
    private static final String FIXED_SALT = "ertyujmvfdftyuiknbfyuiknbvfyu";

    @Resource
    private UserService userService;

    @Resource
    private UserServiceHelper userServiceHelper;

    @RequestMapping("/r/signup")
    public String signup(Model model) {
        return "/auth/signup";
    }

    @RequestMapping("/r/login")
    public String login(HttpServletResponse response, Model model, String origin) throws IOException {
        logger.info("[login] origin=#{}", origin);
        User user = UserUtil.getUser();
        logger.info("[login] user=#{}", user);
        if(user != null && StringUtils.isBlank(origin)){
            response.sendRedirect("/");
        }

        return "/auth/login";
    }

    @RequestMapping("/r/logout")
    public String logout(Model model,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         String origin) throws IOException {
        logger.info("[logout] origin=#{}", origin);
        User user = UserUtil.getUser();
        if(user != null) {
            UserUtil.unbindUser();
        }
        HttpSession session = request.getSession(false);
        if(session != null)
            session.invalidate();
//        String loginUrl = "/sso/user/r/login";
//        if(StringUtils.isNotBlank(origin)) {
//            loginUrl += "?origin=" + HttpUtil.formatUrl(origin, "");
//        }
//
//        model.addAttribute("loginUrl", loginUrl);

        return "/auth/logout";
    }

    /**
     * 根据userId获得盐值
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/r/getSalt")
    public Object getSalt(String userName){
        logger.info("[getSalt] userName=#{}", userName);
        if(StringUtils.isBlank(userName)) {
            return JsonResponseUtil.failureResp("请填写用户名", null);
        }
        String salt = userService.getSaltByUserName(userName);
        if(StringUtils.isBlank(salt)) {
            return JsonResponseUtil.failureResp(Constants.SSO_COMMON_ERR, null);
        }
        return JsonResponseUtil.successResp(Constants.SUCCESS, salt);
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
