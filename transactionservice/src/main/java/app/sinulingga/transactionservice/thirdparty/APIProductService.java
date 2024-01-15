package app.sinulingga.transactionservice.thirdparty;

import app.sinulingga.transactionservice.dto.DeductQtyRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
public class APIProductService {
    private static final Logger log = LoggerFactory.getLogger(APIProductService.class);

    @Value(value = "${endpoint.api.deduct.qty}")
    private String endpointAPIDeductQty = "";

    public void APIDeductQty(DeductQtyRequest payload) {
        try {
            log.info("Endpoint API Deduct Qty: " + endpointAPIDeductQty);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper objectMapper = new ObjectMapper();
            String payloadJson = objectMapper.writeValueAsString(payload);

            HttpEntity<String> httpRequest = new HttpEntity<>(payloadJson, httpHeaders);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Map<String, Object>> response =  restTemplate.
                    exchange(endpointAPIDeductQty, HttpMethod.PUT, httpRequest, new ParameterizedTypeReference<Map<String, Object>>(){});

            Map<String, Object> dataResponse = response.getBody();
            if (response.getStatusCode() != HttpStatus.OK) {
                log.info("Error when call APIDeductQty. Message: " + dataResponse.get("message"));
                throw new Exception("Call APIDeductQty Failed");
            }

        } catch (Exception e) {
            log.info("Error when call APIDeductQty: " + e.getMessage());
            e.getStackTrace();
        }
    }

}
