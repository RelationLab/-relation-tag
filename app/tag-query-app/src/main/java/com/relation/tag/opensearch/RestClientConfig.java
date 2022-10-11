package com.relation.tag.opensearch;

import org.opensearch.client.RestHighLevelClient;
import org.opensearch.data.client.orhlc.AbstractOpensearchConfiguration;
import org.opensearch.data.client.orhlc.ClientConfiguration;
import org.opensearch.data.client.orhlc.RestClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientConfig extends AbstractOpensearchConfiguration {

    @Value("${spring.datasource.ssh.forward.from-host}")
    private String host;
    @Value("${spring.datasource.ssh.forward.from-port}")
    private String port;
    @Override
    @Bean
    public RestHighLevelClient opensearchClient() {

        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(host+":"+port)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
