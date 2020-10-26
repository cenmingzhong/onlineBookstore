package com.cenmingzhong.utils;

import com.cenmingzhong.prjo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author cenmingzhong
 * @create 2020-09-17-下午 17:47
 */
public class WebUtils {
    public static <T>T copyParamToBean(Map value, T bean){

        try {


            System.out.println("注入之前："+bean);
            /**
             * 把所有请求的参数都注入到user对象中
             * **/
            BeanUtils.populate(bean,value);
            System.out.println("注入之后："+bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }


    /**
     * 将字符串转换成int类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue){
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }


}