package com.goshine.ptadmui.performance.annotation;


import com.goshine.ptadmui.security.shiro.config.ShiroConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 自定义注解获取对象属性
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String value() default "";
}
//strong>package com.bjhy.platform.criminal.search.core.tool;
//import java.lang.reflect.Field;import java.util.ArrayList;
//import java.util.LinkedHashMap;import java.util.List;
//import com.bjhy.platform.criminal.search.core.annotation.ColumnConfig;
///*** * 获取属性与对应的中文名工具类 * @author wangbowen * @date 2015-12-14 * @version 1.0 * */
//public class FieldHelper {   /**    * 根据实体类名获取字段名称和中文名称    * @param entityName 实体类名    * @return List<Map<String,Object>>     */ public static List<LinkedHashMap<String,Object>>  
//    initAnnoFieldDic(@SuppressWarnings("rawtypes") Class clzz)
//    {      //用于存储字段和中文值的集合   List<LinkedHashMap<String,Object>> fieldList = new ArrayList<>();   //用于存储实体类字段(key)和中文名(value)   
//        // LinkedHashMap<String,Object> valueMap = new LinkedHashMap<>();           //获取对象中所有的Field            Field[] fields = clzz.getDeclaredFields();          
//        //   //循环实体类字段集合,获取标注@ColumnConfig的字段         for (Field field : fields) {          if(field.isAnnotationPresent(ColumnConfig.class)){         
//        //   //获取字段名           String fieldNames = clzz.getSimpleName()+"."+field.getName();    
//        //         //获取字段注解            ColumnConfig columnConfig = field.getAnnotation(ColumnConfig.class);       
//        //    //判断是否已经获取过该code的字典数据 避免重复获取            if(valueMap.get(columnConfig.description())==null){             valueMap.put(fieldNames, columnConfig.description());            }         }         }     
//        //     fieldList.add(valueMap);//将LinkedHashMap放入List集合中   return fieldList; } }</strong>
//
//        void userFieldAnnotationTest() {
//        UserVo userVo = new UserVo();
//        //获取class对象
//        Field[] fields = userVo.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getName());
//            //获取属性上的注解
//            Annotation[] annotations = field.getDeclaredAnnotations();
//            for (Annotation annotation:annotations) {
//                if(annotation instanceof UserFieldAnnotation ){
//                    System.out.println(((UserFieldAnnotation) annotation).value());
//                }
//            }
//        }
//    }
