package com.goshine.ptadmui.security.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.goshine.ptadmui.core.model.Response;
import com.goshine.ptadmui.security.support.XssSqlHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * http request response 过滤XSS SQL 数据工具类
 * @author litao
 * @date 2019-11-14
 */
@Slf4j
public class RequestResponseUtils {
    private static final String STR_BODY = "body";

    /**
     * description 取request中的已经被防止XSS，SQL注入过滤过的key value数据封装到map 返回
     *
     * @param request 1
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String,String> getRequestParameters(ServletRequest request) {
        Map<String,String> dataMap = new HashMap<>(16);
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = (String)enums.nextElement();
            String paraValue = getRequest(request).getParameter(paraName);
            if(null!=paraValue && !"".equals(paraValue)) {
                dataMap.put(paraName,paraValue);
            }
        }
        return dataMap;
    }

    /**
     * description 获取request中的body json 数据转化为map
     *
     * @param request 1
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getRequestBodyMap(ServletRequest request) {
        Map<String ,String > dataMap = new HashMap<>(16);
        // 判断是否已经将 inputStream 流中的 body 数据读出放入 attribute
        if (request.getAttribute(STR_BODY) != null) {
            // 已经读出则返回attribute中的body
            return (Map<String,String>)request.getAttribute(STR_BODY);
        } else {
            try {
                Map<String,String > maps = JSON.parseObject(request.getInputStream(),Map.class);
                dataMap.putAll(maps);
                request.setAttribute(STR_BODY,dataMap);
            }catch (IOException e) {
                e.printStackTrace();
            }
            return dataMap;
        }
    }

    /**
     * description 读取request 已经被防止XSS，SQL注入过滤过的 请求参数key 对应的value
     *
     * @param request 1
     * @param key 2
     * @return java.lang.String
     */
    public static String getParameter(ServletRequest request, String key) {
        return getRequest(request).getParameter(key);
    }

    /**
     * description 读取request 已经被防止XSS，SQL注入过滤过的 请求头key 对应的value
     *
     * @param request 1
     * @param key 2
     * @return java.lang.String
     */
    public static String getHeader(ServletRequest request, String key) {
        return getRequest(request).getHeader(key);
    }

    /**
     * description 取request头中的已经被防止XSS，SQL注入过滤过的 key value数据封装到map 返回
     *
     * @param request 1
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String,String> getRequestHeaders(ServletRequest request) {
        Map<String,String> headerMap = new HashMap<>(16);
        Enumeration enums = getRequest(request).getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = (String) enums.nextElement();
            String value = getRequest(request).getHeader(name);
            if (null != value && !"".equals(value)) {
                headerMap.put(name,value);
            }
        }
        return headerMap;
    }

    public static HttpServletRequest getRequest(ServletRequest request) {
        return new XssSqlHttpServletRequestWrapper((HttpServletRequest) request);
    }

    /**
     * 封装response  统一json返回
     * @param response 响应对象
     * @param status 状态码
     * @param responseContext 响应消息体
     * @return
     */
    public static void responseWrite(HttpServletResponse response, int status, Response<T> responseContext) {
        try {
            response.setStatus(status);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter printWriter = response.getWriter();
            printWriter.write(JSON.toJSONString(responseContext, SerializerFeature.WriteMapNullValue));
            printWriter.flush();
        } catch (IOException e) {
            log.error("print response exception",e);
        }
    }
}
