package org.elliot.hutoollearning.EntityBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品实体类
 * @author Elliot Lee
 * @since 2025/1/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    /**
     * 产品名称
     */
    private String prodName;
    /**
     * 产品版本
     */
    private String prodVersion;
    private String maxUsageCount;
    private String maxRecycleCount;
    private String maxPMUsageCount;
}
