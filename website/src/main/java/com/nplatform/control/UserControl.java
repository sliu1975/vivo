package com.nplatform.control;

import com.auth0.jwt.interfaces.Claim;
import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.UserRepository;
import com.nplatform.entity.User;
import com.nplatform.interceptor.HttpRequestHelper;
import com.nplatform.redis.RedisCacheConfig;
import com.nplatform.redis.RedisUtils;
import com.nplatform.utils.JwtToken;
import com.nplatform.utils.MD5;
import com.nplatform.utils.MessageSourceUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserControl {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RedisCacheConfig redisCacheConfig;

    @Autowired
    private MessageSourceUtils messageSourceUtils;

    @RequestMapping("/index")
    public String pageIndex(HttpServletRequest request){
        Map<String,Object> results=new HashMap<String,Object>();
        String pageindex=request.getParameter("pi");
        String pagetotal=request.getParameter("ps");

        int page=0;
        int pagecount=10;

        if(pageindex==null || pageindex.equals("0"))
            page=0;
        else
            page=Integer.parseInt(pageindex) - 1;

        if(pagetotal!=null)
            pagecount=Integer.parseInt(pagetotal);

        if(page==0) {
            String count = userRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<User> result = userRepository.findUser(pageRequest);
        results.put("results",result.getContent());

        request.setAttribute("data",results);

        return "/jsp/user/index";
    }

    @RequestMapping("/toadd")
    public String toadd(HttpServletRequest request){

        return "/jsp/user/add";
    }

    @RequestMapping("/addUser")
    public String addActivity(HttpServletRequest request){

        try {
            if (StringUtils.isBlank(request.getParameter("name")) ||
                    StringUtils.isBlank(request.getParameter("displayname"))
                    )
            {
                request.setAttribute("message","请输入必要的内容!");
                return "/jsp/user/add";
            }

            User user=new User();

            user.setUserName(request.getParameter("name"));
            user.setDisplayname(request.getParameter("displayname"));
            user.setCategory(request.getParameter("category"));

            if(request.getParameter("department")!=null)
            {
                user.setDepartment(request.getParameter("department"));
            }
            user.setPassword(MD5.getResult("123456"));

            //user.setDepartment("");

            user.setId(null);

            userRepository.save(user);
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message","请输入必要的内容!");
            return "/jsp/user/add";
        }

        return "redirect:/user/index";
    }

    @RequestMapping("/editUser")
    public String editActivity(HttpServletRequest request){

        String id=request.getParameter(("id"));

        User user = userRepository.findById(Integer.parseInt(id));

        try {

            if (StringUtils.isBlank(request.getParameter("name")) ||
                    StringUtils.isBlank(request.getParameter("displayname"))
                    )
            {
                request.setAttribute("user",user);
                request.setAttribute("message","请输入必要的内容!");
                return "/jsp/user/edit";
            }

            user.setUserName(request.getParameter("name"));
            user.setDisplayname(request.getParameter("displayname"));
            user.setCategory(request.getParameter("category"));

            if(request.getParameter("department")!=null)
            {
                user.setDepartment(request.getParameter("department"));
            }

            //user.setPassword(MD5.getResult("123456"));

            //user.setDepartment("");

            userRepository.save(user);
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("user",user);
            request.setAttribute("message","请输入必要的内容!");
            return "/jsp/user/edit";
        }

        return "redirect:/user/index";
    }

    @RequestMapping("/toedit")
    public String toedit(HttpServletRequest request){
        String id=request.getParameter(("id"));

        User user = userRepository.findById(Integer.parseInt(id));

        request.setAttribute("user",user);

        return "/jsp/user/edit";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public ApiResult findAll(){
        List<User> result = userRepository.findAll();
        return ApiResult.success(result);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ApiResult add(User user) {
        try {
            userRepository.save(user);
        } catch(Exception e) {
            e.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR, "添加失败");
        }

        return ApiResult.success();
    }

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult ListUser(HttpServletRequest request){
        Map<String,Object> results=new HashMap<String,Object>();
        String pageindex=request.getParameter("pi");
        String pagetotal=request.getParameter("ps");

        int page=0;
        int pagecount=10;

        if(pageindex==null)
            page=0;
        else
            page=Integer.parseInt(pageindex) - 1;

        if(pagetotal!=null)
            pagecount=Integer.parseInt(pagetotal);

        if(page==0) {
            String count = userRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<User> result = userRepository.findUser(pageRequest);
        results.put("results",result.getContent());

        return ApiResult.success(results);
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public ApiResult GetUser(HttpServletRequest request){
        String id=request.getParameter("id");
        User user=userRepository.findById(Integer.valueOf(id));
        return ApiResult.success(user);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ApiResult Login(HttpServletRequest request) throws Exception {
        ApiResult result;
        String userName=request.getParameter("userName");
        String password=request.getParameter("password");

        User user = userRepository.findByUserName(userName);

        if (user!=null) {
            if (user.getPassword().equals(password)) {
                String token = JwtToken.createToken(Long.valueOf(user.getId()));
                result = ApiResult.success(token);

                redisCacheConfig.getRedisTemplate().opsForValue()
                        .set(getRedisLoginKey(token), user.getId().toString(), 3600 * 24 * JwtToken.calendarInterval, TimeUnit.SECONDS);
            } else {
                result = ApiResult.error(ErrorCode.BUSINESS_ERROR, "Password is not correct");
            }
        } else {
            result = ApiResult.error(ErrorCode.BUSINESS_ERROR, messageSourceUtils.getMSg("login.error.username"));
        }

        return result;
    }

    @RequestMapping("/islogin")
    @ResponseBody
    public ApiResult isLogin(HttpServletRequest request){
        String token = HttpRequestHelper.getToken(request);

        Map<String, Claim> map = JwtToken.verifyToken(token);
        if (map == null || System.currentTimeMillis() > map.get("exp").asDate().getTime()) {
            return ApiResult.error(ErrorCode.NOT_LOGIN);
        }

        Integer uid = Integer.valueOf(map.get("user_id").asString());
        if (uid == null) {
            return ApiResult.error(ErrorCode.NOT_LOGIN);
        }

        String tokenRedis = redisCacheConfig.getRedisTemplate().opsForValue().get(getRedisLoginKey(token));

        if (!uid.toString().equals(tokenRedis))
            return ApiResult.error(ErrorCode.NOT_LOGIN);

        return ApiResult.success();
    }

    @RequestMapping("/loginOut")
    @ResponseBody
    public ApiResult loginOut(HttpServletRequest request){
        String token = HttpRequestHelper.getToken(request);

        Map<String, Claim> map = JwtToken.verifyToken(token);
        if (map != null || System.currentTimeMillis() <= map.get("exp").asDate().getTime()) {
            Integer uid = Integer.valueOf(map.get("user_id").asString());
            if (uid != null) {
                String tokenRedis = redisCacheConfig.getRedisTemplate().opsForValue().get(getRedisLoginKey(token));
                if (StringUtils.isBlank(tokenRedis)) {
                    return ApiResult.error(ErrorCode.BUSINESS_ERROR, "token已经登出");
                }

                redisCacheConfig.getRedisTemplate().delete(getRedisLoginKey(token));
                return ApiResult.success();
            }
        }

        return ApiResult.error(ErrorCode.INTERNAL_ERROR);
    }

    @RequestMapping("/message")
    @ResponseBody
    public ApiResult message(HttpServletRequest request){
        String locale = request.getParameter("locale");
        messageSourceUtils.setLocale(locale);
        return ApiResult.success(locale);
    }

    private String getRedisLoginKey(String token) {
        return RedisUtils.keyBuilder("user", "login", token);
    }

}
