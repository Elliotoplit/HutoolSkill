package org.elliot.hutoollearning.bean;

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
