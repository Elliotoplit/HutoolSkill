package org.elliot.hutoollearning.dto;

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
public class AttributeItem {
    private String attributeName;
    private String oldValue;
    private String newValue;
}
