package org.elliot.hutoollearning.demo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import org.elliot.hutoollearning.EntityBase.Product;

import java.util.*;

/**
 * MapUtil
 * https://plus.hutool.cn/apidocs/cn/hutool/core/map/MapUtil.html
 *
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class MapUtilDemo {
    public static void main(String[] args) {
        Product product = new Product("B_Prod", "0", "100", "100", "300");
        Product product2 = new Product("C_Prod ", "1", "10", "20", "30");
        Product product3 = new Product("C_Prod ", "2", "10", "20", "30");
        Product product4 = new Product("C_Prod ", "2", "10", "20", "30");
        Product product5 = new Product("D_Prod ",  "1", "10", "20", "30");
        List<Product> dataList = List.of(product, product2, product3, product4, product5);

        // DEBUG TEST
        String str = "HELLO";
        Map<String, String> testMap = new HashMap<>(6);
        {
            testMap.put("11","北京");
            testMap.put("12","天津");
            testMap.put("13","河北");
            testMap.put("14","山西");
            testMap.put("15","内蒙古");
            testMap.put("21","辽宁");
            testMap.put("22","吉林");
            testMap.put("23","黑龙江");
            testMap.put("31","上海");
            testMap.put("32","江苏");
            testMap.put("33","浙江");
            testMap.put("34","安徽");
            testMap.put("35","福建");
            testMap.put("36","江西");
            testMap.put("37","山东");
            testMap.put("41","河南");
            testMap.put("42","湖北");
            testMap.put("43","湖南");
            testMap.put("44","广东");
            testMap.put("45","广西");
            testMap.put("51","四川");
            testMap.put("52","贵州");
            testMap.put("53","云南");
            testMap.put("54","西藏");

        }
        Map<String, List<Product>> listMap = new HashMap<>();
        //Java.Map.putIfAbsent - 按照prodKey分组
        for (Product rowData : dataList) {
            String prodKey = prodKey(rowData.getProdName(), rowData.getProdVersion());
            //方法1:
            //listMap.putIfAbsent(prodKey, new ArrayList<>());
            //listMap.get(prodKey).add(rowData);
            //方法2:
            listMap.compute(prodKey, (key, valueList) -> {
                if (Objects.isNull(valueList)) {
                    valueList = new ArrayList<>();
                }
                valueList.add(rowData);
                return valueList;
            });
        }


        //Java.Map.compute - 按照prodName分组
        Map<String, Integer> countMap = new HashMap<>();
        Map<String, Integer> countMapForIfAbsent = new HashMap<>();
        for (Product rowData : dataList) {
            String prodKey = prodKey(rowData.getProdName(), rowData.getProdVersion());
            countMap.compute(prodKey, (key, count) -> {
                System.out.println("prodKey = " + prodKey);
                System.out.println("key = " + key);
                return Objects.isNull(count) ? 1 : count + 1;
            });
            countMapForIfAbsent.computeIfAbsent(prodKey, key -> {
                System.out.println("prodKey = " + prodKey);
                System.out.println("key = " + key);
                return 1;
            });
        }


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
        MapUtil.removeAny(attributeMap, "maxPMUsageCount");
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

    private static String prodKey(String prodName, String prodVersion) {
        String prodKey = prodName + "." + prodVersion;
        System.out.println("Other Operations1....");
        System.out.println("Other Operations2....");
        return prodKey;
    }
}
