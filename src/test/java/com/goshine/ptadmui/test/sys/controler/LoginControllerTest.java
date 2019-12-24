package com.goshine.ptadmui.test.sys.controler;

import com.alibaba.fastjson.JSON;
import com.goshine.ptadmui.Application;
import com.goshine.ptadmui.sys.controller.LoginController;
import com.goshine.ptadmui.sys.controller.UserController;
import com.goshine.ptadmui.sys.service.BlackListService;
import com.goshine.ptadmui.sys.service.MenuService;
import com.goshine.ptadmui.sys.service.OperationService;
import com.goshine.ptadmui.sys.service.UserService;
import com.goshine.ptadmui.sys.vo.user.LoginFormUser;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

/**
 * 用户登录层mock测试
 * @author litao
 * @Date 2019-11-28
 */
//基于MockitoJUnitRunner的运行器
//@RunWith(MockitoJUnitRunner.class)
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class LoginControllerTest {
    private  final Logger log= LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private MockHttpSession mockHttpSession;
    //自动将模拟对象或侦查域注入到被测试对象中。对被测类中@Autowired的对象，用@Mocks标注；对被测类自己，用@InjectMocks标注
    @Mock
    private BlackListService blackListService;
    private @Mock UserService userService;
    private @Mock  MenuService menuService;
    private @Mock OperationService operationService;
    @InjectMocks
    private LoginController loginController;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        /**
         * 独立安装
         */
       // this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        /**
         * 集成Web环境测试
         */
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mockHttpSession=new MockHttpSession();
    }
//@Test
//    public HttpSession testDefaultKaptcha(){
//        MvcResult mvcResult=null;
//        try {
//            mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/captcha")
//                    .accept(MediaType.APPLICATION_JSON_UTF8))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//           return mvcResult.getRequest().getSession();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
    @Test
    public void testLogin(){
        MvcResult mvcResult=null;
        try {
            //将验证码值放入session中
            mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/captcha")
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
        LoginFormUser loginUser=new LoginFormUser();
        loginUser.setPassword("123456");
        loginUser.setRememberMe(false);
        loginUser.setUserName("admin");
        //将验证码从session中取出
        loginUser.setVerifyCode((String) mvcResult.getRequest().getSession().getAttribute("verifyCode"));
        String jsonLoginUser=JSON.toJSONString(loginUser);
        //执行一个请求
//            Mockito.when(this.loginController.login(Matchers.))
            MvcResult mvcResult2= mockMvc.perform
                    //构造一个请求
                    (MockMvcRequestBuilders.post("/private/api/login")
                    //指定请求格式类型
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    //指定json消息体
                    .content(jsonLoginUser)
                   // .session((MockHttpSession)testDefaultKaptcha())
                    //指定可接受数据格式
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    //指定session获取路径
                    .session((MockHttpSession)mvcResult.getRequest().getSession()))
                    //指定验证规则
                    //验证期望跳转路径
                    //.andExpect(MockMvcResultMatchers.forwardedUrl(""))
                    //验证期望状态
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    //打印结果
                    .andDo(MockMvcResultHandlers.print())
                    //校验返回消息体信息
                    .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("0"))
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//    @Test
//    public  void testmenu(){
//        String s = JSON.toJSONString("15755366921637500097");
//        try {
//            mockMvc.perform(MockMvcRequestBuilders.get("/menu/15755366921637500097")
//                    .contentType(MediaType.APPLICATION_JSON_UTF8)
//                    .content(s)
//                    .accept(MediaType.APPLICATION_JSON_UTF8))
//                    .andExpect(MockMvcResultMatchers.status().isOk())
//                    .andDo(MockMvcResultHandlers.print())
//                    .andReturn();
//        }catch (Exception e){e.printStackTrace();}
//}
