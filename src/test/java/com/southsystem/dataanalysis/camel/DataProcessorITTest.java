package com.southsystem.dataanalysis.camel;

import com.southsystem.dataanalysis.DataAnalysisApplication;
import com.southsystem.dataanalysis.config.AppConfiguration;
import com.southsystem.dataanalysis.core.model.Report;
import com.southsystem.dataanalysis.core.processor.impl.ClientProcessor;
import com.southsystem.dataanalysis.core.processor.impl.LineProcessFactory;
import com.southsystem.dataanalysis.core.processor.impl.SalesManProcessor;
import com.southsystem.dataanalysis.core.processor.impl.SalesProcessor;
import com.southsystem.dataanalysis.exception.InvalidLineCodeException;
import com.southsystem.dataanalysis.exception.SaleException;
import com.southsystem.dataanalysis.service.DoneFileService;
import com.southsystem.dataanalysis.service.EntryFileService;
import com.southsystem.dataanalysis.service.ReadFileService;
import com.southsystem.dataanalysis.service.WriteFileService;
import com.southsystem.dataanalysis.util.TestUtils;
import org.apache.camel.CamelContext;
import org.apache.camel.Configuration;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(classes = {DataAnalysisApplication.class})
@TestPropertySource(
        locations = "classpath:application.yml")
public class DataProcessorITTest {

    @Autowired
    private CamelContext camelContext;

    @Autowired
    private DataProcessor dataProcessor;

    @EndpointInject("mock:result")
    private MockEndpoint resultEndpoint;

    @Produce("direct:start")
    private ProducerTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start").process(dataProcessor).to("mock:result");
            }
        });
        camelContext.start();
    }

    @AfterEach
    public void after() throws IOException {
        FileUtils.cleanDirectory(new File("src/test/resources/data/out"));
        camelContext.stop();
    }

    @DirtiesContext
    @Test
    public void processData() throws Exception {
        resultEndpoint.expectedMessageCount(1);
        Map<String, Object> headers = new HashMap<>();
        Path path = Paths.get("src", "test", "resources", "sample-test.dat");
        headers.put("CamelFileAbsolutePath", path.toAbsolutePath().toString());

        template.sendBodyAndHeaders("direct:start", TestUtils.getTestFile("sample-test.dat"), headers);

        resultEndpoint.assertIsSatisfied();

        String resultContent = TestUtils.getDoneTestFile("sample-test.done.dat");

        Report expectedReport = TestUtils.createReport(2,2,"10", "Paulo");

        Assertions.assertEquals(expectedReport.toString(), resultContent);

    }
}
