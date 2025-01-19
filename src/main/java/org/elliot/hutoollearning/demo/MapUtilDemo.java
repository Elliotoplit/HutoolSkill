package org.elliot.hutoollearning.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import org.elliot.hutoollearning.EntityBase.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * MapUtil
 * https://plus.hutool.cn/apidocs/cn/hutool/core/map/MapUtil.html
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class StrUtilDemo {
    public static void main(String[] args) {
        Product product = new Product("B_Prod", "0", "100", "200","300");
        Product product2 = new Product("C_Prod ", " 1", "10", "20","30");
        Map<String, Object> prodMap = BeanUtil.beanToMap(product);

        //isEmpty - 判断Map是否为空 clear - 清空Map
        boolean isEmptyMap1 = MapUtil.isEmpty(prodMap);
        System.out.println("isEmptyMap1 = " + isEmptyMap1);
        //MapUtil.clear(prodMap);
        System.out.println("isEmptyMap2 = " + MapUtil.isEmpty(prodMap));

        //集合遍历 - 原始方法
        Set<Map.Entry<String, Object>> entries = prodMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println("key = " + key);
            System.out.println("value = " + value);
        }
        //getAny - 获取Map中的部分keys 组成新的map
        Map<String, Object> attributeMap = MapUtil.getAny(prodMap, "maxUsageCount", "maxRecycleCount", "maxPMUsageCount");
        System.out.println("attributeMap = " + attributeMap);
        //grouping - 根据key分组
        Map<String, List<Object>> grouping = MapUtil.grouping(prodMap.entrySet());
        System.out.println("grouping = " + grouping);

    }
}
