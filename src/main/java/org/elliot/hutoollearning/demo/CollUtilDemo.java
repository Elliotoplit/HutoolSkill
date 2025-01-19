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
