import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;

// Spring framework imports
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestClient getRestClient()
    {
        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        return restClient;
    }

    @Bean
    public ElasticsearchTransport getElasticSearchTransport()
    {
        RestClientTransport restClientTransport = new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
        return restClientTransport;
    }
    @Bean
    public ElasticsearchClient getElasticSearchClient()
    {
        ElasticsearchClient esClient = new ElasticsearchClient(getElasticSearchTransport());
        return esClient;
    }
}
