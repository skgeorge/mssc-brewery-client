package guru.springframework.msscbreweryclient.web.config;

import guru.springframework.msscbreweryclient.web.client.RestTemplateCustomizer;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.conn.NHttpClientConnectionManager;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class NIORestTemplateCustomizer implements RestTemplateCustomizer {
    public ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
        final DefaultConnectingIOReactor ioreactor = new DefaultConnectingIOReactor(IOReactorConfig.custom().
                setConnectTimeout(3000).
                setIoThreadCount(4).
                setSoTimeout(3000).
                build());
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager((HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection>) ioreactor);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setMaxTotal(1000);

        CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
                .setConnectionManager((NHttpClientConnectionManager) connectionManager)
                .build();
        return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
    }
    @Override
    public void customize(RestTemplate restTemplate){
        try{
            restTemplate.setRequestFactory(clientHttpRequestFactory());
        } catch (IOReactorException e){
            e.printStackTrace();
        }
    }
}