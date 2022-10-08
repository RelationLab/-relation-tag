package com.relation.tag.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LabelInfo {
    private String name;

    private String content;

    private String source;
}
