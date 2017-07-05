package com.web;

import com.model.User;
import com.service.UserService;
import com.utils.UUIDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by yuanjie.fang on 2017/7/5.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    //用户注册
    @RequestMapping(value = "regist",method = RequestMethod.POST)
    public String regist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try{
            //处理中文乱码
            request.setCharacterEncoding("UTF-8");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");
            String email = request.getParameter("email");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setEmail(email);
            user.setState(0);//0:未激活  1：已经激活
            String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
            user.setCode(code);
            //保存注册用户
            userService.regist(user);

            return "index";
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //用户激活
    @RequestMapping(value = "active",method = RequestMethod.GET)
    public void active(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        try{
            //获取激活码
            String code = request.getParameter("code");
            //获取用户信息
            User user = userService.findUserByCode(code);
            if(user != null){
                //用户存在
                user.setState(1);
                //修改用户状态为激活状态
                userService.updateUser(user);
                request.setAttribute("msg","您激活码成功,请登录");
            }else{
                //用户不存在
                request.setAttribute("msg","激活码有误,请重新激活");
            }
            request.getRequestDispatcher("/active.jsp").forward(request,response);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }

}
