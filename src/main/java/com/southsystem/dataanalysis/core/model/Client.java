package com.southsystem.dataanalysis.core.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Client {

    private String cnpj;
    private String name;
    private String bussinesArea;

}
