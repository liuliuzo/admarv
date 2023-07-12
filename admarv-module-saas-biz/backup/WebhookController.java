package org.jeecg.admarv.saas.fb.ads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author liuliu
 *
 */
public class WebhookController {
    
    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    
    @RequestMapping(value = "/admarv/webhook", method = RequestMethod.GET)
    public String getWebhook(@RequestParam("hub.mode") String mode, 
                             @RequestParam("hub.verify_token") String token,
                             @RequestParam("hub.challenge") String challenge) {
        if (mode != null && token != null) {
            if (mode.equals("subscribe") && token.equals(VERIFY_TOKEN)) {
                log.info("WEBHOOK_VERIFIED");
            } else {
            }
        }
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> postWebhook(@RequestBody String payload) {
        try {
            webhookService.processMessage(PAGE_ACCESS_TOKEN, payload);
            return ResponseEntity.status(HttpStatus.OK).body("EVENT_RECEIVED");
        } catch (Exception e) {
            System.out.println("Error processing message: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}