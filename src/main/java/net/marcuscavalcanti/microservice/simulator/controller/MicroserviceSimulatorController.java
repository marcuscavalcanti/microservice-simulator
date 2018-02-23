package net.marcuscavalcanti.microservice.simulator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(MicroserviceSimulatorController.MOCK_RESOURCE)
public class MicroserviceSimulatorController {

    private static final Logger log = LoggerFactory.getLogger(MicroserviceSimulatorController.class);

    static final String MOCK_RESOURCE = "/mock/**";
    
    @Value("${microservice.latency:7, 10, 18, 25, 37, 67, 85, 100, 130, 200, 250, 300,500}")
    private int[] latencyRange;
    
    @Value("#{'${microservice.httpStatusCode:NO_CONTENT,OK,ACCEPTED,CREATED,INTERNAL_SERVER_ERROR,SERVICE_UNAVAILABLE,BAD_GATEWAY}'.split(',')}") 
    private List<String> statusCode;
	
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity put() {
        return getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity post() {
        return getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity get() {
        return getResponseEntity();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity delete() {
        return getResponseEntity();
    }

    private ResponseEntity getResponseEntity() {		
		try {
            Integer millis = latencyRange[new Random().nextInt(latencyRange.length)];

            log.info("Simulating latency: ["+millis+"] milliseconds");

            Thread.sleep(millis);
            
        } catch (InterruptedException e) {
        		log.error("Simulating error", e);
        }
		
		String status = statusCode.get( new Random().nextInt(statusCode.size()) ).trim();
		
		log.info("HTTP Status: ["+status+"]");

        return new ResponseEntity(HttpStatus.valueOf(status));
    }
    
    
}

