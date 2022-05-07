package com.southsystem.dataanalysis.core.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SalesMan {

    private String cpf;
    private String name;
    private BigDecimal salary;
}
