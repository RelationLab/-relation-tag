package com.relation.tag.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Assets {
    private BigDecimal balance;
    private String name;
    private BigDecimal sort_balance;
}
