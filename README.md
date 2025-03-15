---
title: Hutool常用功能
date: 2025-01-19 19:17:04
tags:

- Hutool

---

Hutool API

[Overview (hutool 5.8.35 API)](https://plus.hutool.cn/apidocs/)

Hutool依赖导入

```xml

<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.26</version>
</dependency>
```

<!--more-->

## 1.BeanUtil

```java
package org.elliot.hutoollearning.demo;

import cn.hutool.core.bean.BeanDesc;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import org.elliot.hutoollearning.EntityBase.Product;
import org.elliot.hutoollearning.dto.AttributeItem;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BeanUtil
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/bean/BeanUtil.html">官方链接</a>
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class BeanUtilDemo {

    public static void main(String[] args) {
        //：BeanToMap动态新增属性解决方案
        List<String> attributeList = List.of("maxUsageCount", "maxRecycleCount","maxPMUsageCount");
        Product product = new Product("A_Prod ", " 0", "100", "200","");
        Map<String, Object> prodMap = BeanUtil.beanToMap(product);
        List<AttributeItem> prodAttrList = new ArrayList<>();
        for (String attributeName : attributeList) {
            AttributeItem attributeItem = new AttributeItem();
            attributeItem.setAttributeName(attributeName);
            attributeItem.setOldValue((String) prodMap.get(attributeName));
            prodAttrList.add(attributeItem);
        }
        prodAttrList.forEach(System.out::println);
        //GetFiledName
        String getProdName = BeanUtil.getFieldName("getProdName");
        System.out.println(getProdName);
        //HasNullField
        boolean hasNullField = BeanUtil.hasNullField(product);
        //TrimStrFields-去除字符串类型字段的前后空格 会修改原对象
        System.out.println("product = " + product);
        Product trimProduct = BeanUtil.trimStrFields(product);
        System.out.println("trimProduct = " + trimProduct);
        //isMatchName: 判断对象是否属于指定的类
        boolean isMatchName = BeanUtil.isMatchName(product, "Product",true);
        System.out.println("isMatchName = " + isMatchName);
        //copyToList: Bean集合类型转换
        List<Object> products = BeanUtil.copyToList(List.of(product), Object.class);
        System.out.println("products = " + products);
        //copyProperties: Bean属性拷贝
        List<Product> newProducts = new ArrayList<>();
        if (CollUtil.isNotEmpty(products)) {
            for (Object prod : products) {
                Product newProduct = new Product();
                BeanUtil.copyProperties(prod, newProduct);
                newProducts.add(newProduct);
            }
        }
        System.out.println("newProducts = " + newProducts);
        //fillBeanWithMap - 传递目的对象
        Product fillBeanWithMapProd = new Product();
        BeanUtil.fillBeanWithMap(prodMap,fillBeanWithMapProd,true);
        System.out.println("fillBeanWithMapProd = " + fillBeanWithMapProd);
        //mapToBean - 传递目的类
        Product mapToBeanProd = BeanUtil.mapToBean(prodMap, Product.class, true);
        System.out.println("mapToBeanProd = " + mapToBeanProd);
        //getBeanDesc - 获取Bean描述
        BeanDesc beanDesc = BeanUtil.getBeanDesc(Product.class);
        System.out.println("beanDesc = " + beanDesc);
        //hasPublicField - 判断是否有公共字段
        boolean hasPublicField = BeanUtil.hasPublicField(Product.class);
        System.out.println("hasPublicField = " + hasPublicField);
        //getProper
        PropertyDescriptor prodName = BeanUtil.getPropertyDescriptor(Product.class, "prodName");
        System.out.println("prodName = " + prodName);


    }
}
```

## 2.StrUtil

```java
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
        System.out.println("uuid = " + uuid);


    }
    
}
```

## 3.CollUtil

```java
package org.elliot.hutoollearning.demo;

import cn.hutool.core.collection.CollUtil;
import org.elliot.hutoollearning.EntityBase.Product;

import java.util.*;
import java.util.stream.Collectors;

/**
 * CollUtil
 * <a href="https://plus.hutool.cn/apidocs/cn/hutool/core/collection/CollUtil.html">Hutool-CollUtil</a>
 *
 * @author Elliot Lee
 * @since 2025/1/19
 */
public class CollUtilDemo {
    public static void main(String[] args) {
        List<String> nullList = null;
        //emptyIfNull - 如果List为null修改为空集合
        System.out.println("nullList = " + nullList);
        nullList = CollUtil.emptyIfNull(nullList);
        System.out.println("nullListAfterMethod = " + nullList);

        //union-并集(少的向多的里加，重复不加入)
        //unionDistinct-去重并集(两个集合直接合并后去重)
        //unionAll-并集包含重复元素(两个集合直接合并后不去重)
        List<String> listA = CollUtil.newArrayList("a", "b", "c", "c");
        List<String> listB = CollUtil.newArrayList("b", "c", "d");
        Collection<String> unionList = CollUtil.union(listA, listB);
        System.out.println("unionList = " + unionList);
        Collection<String> unionDistinctList = CollUtil.unionDistinct(listA, listB);
        System.out.println("unionDistinctList = " + unionDistinctList);
        List<String> unionAllList = CollUtil.unionAll(listA, listB);
        System.out.println("unionAllList = " + unionAllList);

        //交集 - intersection(两个集合的交集) intersectionDistinct(两个集合的交集去重)
        List<String> listC = CollUtil.newArrayList("a", "b", "c", "c");
        List<String> listD = CollUtil.newArrayList("b", "c", "c", "d");
        Collection<String> intersection = CollUtil.intersection(listC, listD);
        System.out.println("intersection = " + intersection);
        Collection<String> intersectionDistinct = CollUtil.intersectionDistinct(listC, listD);
        System.out.println("intersectionDistinct = " + intersectionDistinct);

        //差集 - disjunction(两个集合的对称差集 (A-B)∪(B-A))
        Collection<String> disjunction = CollUtil.disjunction(listC, listD);
        System.out.println("disjunction = " + disjunction);
        //单差集(A-B) subtract(两个集合的差集)
        Collection<String> subtract = CollUtil.subtract(listC, listD);

        List<String> subtractToList = CollUtil.subtractToList(listC, listD);
        System.out.println("subtract = " + subtract);
        System.out.println("subtractToList = " + subtractToList);

        //containAny - 判断集合是否包含任意一个元素
        //containAll - 判断集合是否全部包含
        //countMap - 统计集合中元素出现的次数
        boolean containsAny = CollUtil.containsAny(listC, listD);
        System.out.println("containsAny = " + containsAny);
        boolean containsAll = CollUtil.containsAll(listC, listD);
        System.out.println("containsAll = " + containsAll);
        Map<String, Integer> countMap = CollUtil.countMap(listC);
        System.out.println("countMap = " + countMap);

        //join 和StrUtil.join类似
        List<String> listE = Arrays.asList("a", null, "b");
        String collJoin = CollUtil.join(listE, ",");
        System.out.println("collJoin = " + collJoin);

        //anyMatch - 判断集合中是否有元素匹配指定条件
        //allMatch - 判断集合中是否所有元素匹配指定条件
        boolean anyMatch = CollUtil.anyMatch(listC, "a"::equals);
        System.out.println("anyMatch = " + anyMatch);
        boolean allMatch = CollUtil.allMatch(listC, str -> str.length() == 1);
        System.out.println("allMatch = " + allMatch);

        //distinct - 去重
        ArrayList<String> listF = CollUtil.newArrayList("a", "b", "c", "c");
        ArrayList<String> distinctListF = CollUtil.distinct(listF);
        System.out.println("distinctListF = " + distinctListF);

        //splitList - 按长度拆分List
        List<List<String>> splitList = CollUtil.split(listC, 2);
        System.out.println("splitList = " + splitList);

        //getFieldValues - 获取集合中元素的某个字段值
        List<Product> products = new ArrayList<>();
        Product product = new Product("A_Prod", "0", "100", "200", "");
        Product product2 = new Product("B_Prod", "0", "100", "200", "");
        Product product3 = new Product(null, "0", "100", "200", "");
        CollUtil.addAll(products, Arrays.asList(product, product2, product3));
        List<String> fieldValues = CollUtil.getFieldValues(products, "prodName", String.class);
        System.out.println("fieldValues = " + fieldValues);
        List<Object> fieldValuesWithoutNull = CollUtil.getFieldValues(products, "prodName", true);
        System.out.println("fieldValuesWithoutNull = " + fieldValuesWithoutNull);

        //fieldValueMap - 获取集合中元素的某个字段值为key = Collectors.groupingBy
        products.remove(product3);
        Map<String, Product> fieldValueMap = CollUtil.fieldValueMap(products, "prodName");
        System.out.println("fieldValueMap = " + fieldValueMap);
        Map<String, List<Product>> collect = products.stream()
                                                     .collect(Collectors.groupingBy(Product::getProdName));
        System.out.println("collect = " + collect);

        //fieldValueAsMap = Collectors.toMap
        Map<String, String> fieldValueAsMap = CollUtil.fieldValueAsMap(products, "prodName", "prodVersion");
        System.out.println("fieldValueAsMap = " + fieldValueAsMap);
        Map<String, String> collect1 = products.stream()
                                               .collect(Collectors.toMap(Product::getProdName, Product::getProdVersion));
        System.out.println("collect1 = " + collect1);

        //zip
        Map<String, String> zip = CollUtil.zip("b,a,c", "2,1,3", ",", false);
        System.out.println("zip = " + zip);

        //addIfAbsent - 如果集合中没有则添加
        List<String> listG = CollUtil.newArrayList("a", "b", "c");
        boolean addIfAbsent = CollUtil.addIfAbsent(listG, "a");
        System.out.println("addIfAbsent = " + addIfAbsent);

        //addAllIfNotContains - 如果集合中没有则添加
        List<String> listH = CollUtil.newArrayList("a", "b", "c");
        CollUtil.addAll(listH, Arrays.asList("a", "d"));
        System.out.println("listH = " + listH);
        CollUtil.addAllIfNotContains(listH, Arrays.asList("a", "d"));
        System.out.println("listH = " + listH);

        //sortPageAll - 分页排序
        List<String> listI = CollUtil.newArrayList("a", "b", "c", "d", "e", "f", "g");
        List<String> sortPageAll = CollUtil.sortPageAll(1, 2, Comparator.naturalOrder(), listI);
        System.out.println("sortPageAll = " + sortPageAll);

        //page
        List<String> page = CollUtil.page(1, 2, listI);
        System.out.println("page = " + page);



    }
}
```

## 4.MapUtil

```java
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
```

## 5.ValidatorUtil

```java
package org.elliot.hutoollearning.demo;

import cn.hutool.core.lang.Validator;

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
    }
}
```
