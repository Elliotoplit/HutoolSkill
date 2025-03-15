package org.elliot.hutoollearning.demo;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.NumberUtil;

/**
 * Validator
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/lang/Validator.html">Hutool-Validator</a>
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class ValidatorDemo {
    public static void main(String[] args) {
        //hasChinese - 判断字符串中是否包含中文
        boolean hasChinese = Validator.hasChinese("Hello, 你好");
        System.out.println("hasChinese = " + hasChinese);

        //isNumber - 验证数字
        boolean isNumber = Validator.isNumber("123.45");
        System.out.println("isNumber = " + isNumber);

        //isBetween - 验证数字是否在指定范围内
        boolean isBetween = Validator.isBetween(100, 1, 100);
        System.out.println("isBetween = " + isBetween);

        //isMatchRegex - 验证是否匹配正则表达式
        boolean isMatchRegex = Validator.isMatchRegex("123456", "\\d{6}");
        System.out.println("isMatchRegex = " + isMatchRegex);


        //isGeneral - 验证字符串长度范围
        boolean isGeneral = Validator.isGeneral("123456",1,6);
        System.out.println("isGeneral = " + isGeneral);

        //isInteger - 验证整数
        boolean isInteger = NumberUtil.isInteger("123456.0");
        System.out.println("isInteger = " + isInteger);
    }
}