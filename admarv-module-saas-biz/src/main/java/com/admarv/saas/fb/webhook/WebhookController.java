package com.admarv.saas.fb.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author liuliu
 *
 */
@RestController
public class WebhookController {
    
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    
	@Value("${facebook.verify.token}")
	private String verifyToken;
    
	/**
	 * webhook 验证
	 * 
	 * @param mode
	 * @param token
	 * @param challenge
	 * @return
	 */
	@GetMapping("/admarv/webhook/facebook")
	public ResponseEntity<String> verifyFacebookWebhook(
			@RequestParam("hub.mode") String mode,
			@RequestParam("hub.verify_token") String token,
			@RequestParam("hub.challenge") String challenge) {
		log.info("/admarv/webhook/facebook:{},{},{}", mode, token, challenge);
		if ("subscribe".equals(mode) && verifyToken.equals(token)) {
			return ResponseEntity.ok(challenge);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}

//    @PostMapping("/webhook")
//    public ResponseEntity<String> postWebhook(@RequestBody String payload) {
//        try {
//            webhookService.processMessage(PAGE_ACCESS_TOKEN, payload);
//            return ResponseEntity.status(HttpStatus.OK).body("EVENT_RECEIVED");
//        } catch (Exception e) {
//            System.out.println("Error processing message: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
}