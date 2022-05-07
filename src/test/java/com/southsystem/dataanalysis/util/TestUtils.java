package com.southsystem.dataanalysis.util;

import com.southsystem.dataanalysis.core.model.Client;
import com.southsystem.dataanalysis.core.model.FileData;
import com.southsystem.dataanalysis.core.model.Report;
import com.southsystem.dataanalysis.core.model.SaleItem;
import com.southsystem.dataanalysis.core.model.Sales;
import com.southsystem.dataanalysis.core.model.SalesMan;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Sales buildSales(String saleId, List<SaleItem> saleItemList, String salesManName) {
        return Sales.builder()
                .saleId(saleId)
                .salesManName(salesManName)
                .saleItems(saleItemList)
                .build();
    }

    public static SaleItem buildSaleItem(String itemId, int quantity, BigDecimal price) {
        return SaleItem.builder()
                .itemId(itemId)
                .quantity(quantity)
                .price(price).build();
    }

    public static SalesMan buildSalesMan(String cpf, String name, BigDecimal salary) {
        return SalesMan.builder()
                .name(name)
                .cpf(cpf)
                .salary(salary)
                .build();
    }

    public static Client buildClient(String cnpj, String name, String bussinesArea) {
        return Client.builder()
                .name(name)
                .cnpj(cnpj)
                .bussinesArea(bussinesArea)
                .build();
    }

    public static Report createReport(int numberOfClients, int numberOfSalesMan, String higherSale, String worstSalesMan) {
        return Report.builder()
                .numberOfClients(numberOfClients)
                .numberOfSalesMan(numberOfSalesMan)
                .idHigherSale(higherSale)
                .worstSalesMan(worstSalesMan).build();
    }

    public static String getTestFile(String fileName) throws IOException {

        Path testFile = Paths.get("src","test","resources", fileName);

        var bytes = Files.readAllBytes(testFile);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static String getDoneTestFile(String fileName) throws IOException {

        Path testFile = Paths.get("src","test","resources", "data", "out", fileName);

        var bytes = Files.readAllBytes(testFile);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static FileData createEmptyFileData() {
        return FileData.builder()
                .sales(new ArrayList<>())
                .salesMan(new ArrayList<>())
                .clients(new ArrayList<>()).build();
    }
}
