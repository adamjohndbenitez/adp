package com.changer.billcoin;

import com.changer.billcoin.model.RequestChange;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BillCoinIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void corsWithAnnotation() throws Exception {
        ResponseEntity<RequestChange> entity = this.restTemplate.exchange(
                RequestEntity.get(uri("/change?bill=1")).header(HttpHeaders.ORIGIN, "http://localhost:8080").build(),
                RequestChange.class);

        assertEquals(HttpStatus.OK, entity.getStatusCode());

        assertEquals("http://localhost:8080", entity.getHeaders().getAccessControlAllowOrigin());
    }

    private URI uri(String path) {
        return restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
    }
}
