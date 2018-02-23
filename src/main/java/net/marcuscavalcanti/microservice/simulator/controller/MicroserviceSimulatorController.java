package net.marcuscavalcanti.microservice.simulator.controller;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MicroserviceSimulatorController.MOCK_RESOURCE)
public class MicroserviceSimulatorController {

	private static final Logger log = LoggerFactory.getLogger(MicroserviceSimulatorController.class);

	static final String MOCK_RESOURCE = "/mock/**";

	@Value("${microservice.latency:7, 10, 18, 25, 37, 67, 85, 100, 130, 200, 250, 300,500}")
	private int[] latencyRange;

	@Value("#{'${microservice.httpStatusCode:NO_CONTENT,OK,ACCEPTED,CREATED,INTERNAL_SERVER_ERROR,SERVICE_UNAVAILABLE,BAD_GATEWAY}'.split(',')}")
	private List<String> statusCode;

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity put() {
		return getResponseEntity();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity post() {
		return getResponseEntity();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity get() {
		return getResponseEntity();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity delete() {
		return getResponseEntity();
	}

	@SuppressWarnings("rawtypes")
	private ResponseEntity getResponseEntity() {
		try {
			Integer millis = latencyRange[ThreadLocalRandom.current().nextInt(latencyRange.length)];
			
			log.info("Latency: [ {} ] ms", millis);

			Thread.sleep(millis);

		} catch (InterruptedException e) {
			log.error("Error: [ {} ]", e);
		}
		
		String status = statusCode.get(ThreadLocalRandom.current().nextInt(statusCode.size())).trim();
		
		log.info("HTTP status: [ {} ]", status);

		return new ResponseEntity(HttpStatus.valueOf(status));
	}

}
