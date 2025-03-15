package org.elliot.hutoollearning.demo;

import cn.hutool.core.util.StrUtil;

/**
 * StrUtil 字符串工具类
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/util/StrUtil.html">Hutool StrUtil</a>
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class StrUtilDemo {

    public static void main(String[] args) {
        String attributeName = "maxUsageCount";
        String maxUsageCount = "100";

        // similar - 比较两个字符串的相似度
        Double similar = StrUtil.similar("hello111111", "hello1111111");// true
        String similarPercent = StrUtil.similar("hello111111", "hello1111111",2);// true
        System.out.println("similar = " + similar);
        System.out.println("similarPercent = " + similarPercent);

        //StrUtil.format - 格式化字符串
        String format = StrUtil.format("Hello, {} is {} ", attributeName, maxUsageCount);
        System.out.println("format = " + format);

        //uuid
        String uuid = StrUtil.uuid();
        System.out.println("uwuid = " + uuid);


    }

}
