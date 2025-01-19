package org.elliot.hutoollearning.EntityBase.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Elliot Lee
 * @since 2025/1/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String prodName;
    private String prodVersion;
    private String maxUsageCount;
    private String maxRecycleCount;
    private String maxPMUsageCount;
}
