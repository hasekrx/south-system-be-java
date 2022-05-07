package com.southsystem.dataanalysis.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {

    private int numberOfClients;
    private int numberOfSalesMan;
    private String idHigherSale;
    private String worstSalesMan;
}
