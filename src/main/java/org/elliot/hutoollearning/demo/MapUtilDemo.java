package org.elliot.hutoollearning.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import org.elliot.hutoollearning.EntityBase.Product;

import java.util.*;

/**
 * MapUtil
 * https://plus.hutool.cn/apidocs/cn/hutool/core/map/MapUtil.html
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class MapUtilDemo {
    public static void main(String[] args) {
        Product product = new Product("B_Prod", "0", "100", "100","300");
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
        //removeAny - 移除Map中的部分keys
        MapUtil.removeAny(attributeMap,  "maxPMUsageCount");
        System.out.println("removeAttributeMap = " + attributeMap);
        //removeNullValue - 移除Map中的null值entry
        attributeMap.put("maxPMUsageCount", null);
        System.out.println("attributeMap = " + attributeMap);
        MapUtil.removeNullValue(attributeMap);
        System.out.println("removeNullValue = " + attributeMap);
        //grouping - 根据key分组
        Set<Map.Entry<String, Object>> prodEntries = prodMap.entrySet();
        Map<String, List<Object>> grouping = MapUtil.grouping(prodEntries);
        System.out.println("grouping = " + grouping);
        //reverse - 键值互换 有相同值时会覆盖
        Map<Object, String> inverseMap = MapUtil.inverse(prodMap);
        System.out.println("inverseMap = " + inverseMap);
        //获取Map中的部分values
        Map<String, String> map = new HashMap<>();
        map.put("name", "Alice");
        map.put("age", "25");
        map.put("city", "New York");
        List<String> keys = Arrays.asList("name", "city");
        List<String> values = MapUtil.valuesOfKeys(map, keys.iterator());
        System.out.println("values = " + values);


    }
}
