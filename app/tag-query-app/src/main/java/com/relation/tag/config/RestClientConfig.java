package com.relation.tag.config;

import lombok.extern.slf4j.Slf4j;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpensearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RestClientConfig extends AbstractOpensearchConfiguration {

    //    @Value("${spring.datasource.ssh.forward.from-host}")
    private String host = "localhost";
    //    @Value("${spring.datasource.ssh.forward.from-port}")
    private String port = "2443";
    private static final String USER_NAME = "";
    private static final String USER_PASS = "";

    @Override
    @Bean
    public RestHighLevelClient opensearchClient() {
        final ClientConfiguration clientConfiguration =
                ClientConfiguration.builder()
                        .connectedTo(host + ":" + port)
                        .usingSsl()  // use the SSLContext with the client cert
                        .withConnectTimeout(5000000)
                        .withSocketTimeout(5000000)
                        .build();
        RestHighLevelClient highLevelClient = RestClients.create(clientConfiguration).rest();
        return highLevelClient;
    }
}
