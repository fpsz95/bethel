package com.fernandes.bethel.controller;

import com.fernandes.bethel.payments.paytm.PaytmDetails;
import com.fernandes.bethel.payments.paytm.UserPaymentDetails;
import com.fernandes.bethel.user.User;
import com.fernandes.bethel.user.UserService;
import com.paytm.pg.merchant.PaytmChecksum;
import net.minidev.json.JSONObject;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.core.env.Environment;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@CrossOrigin("*")
@RestController
@RequestMapping("payment")
public class PaymentController {

    private final PaytmDetails paytmDetails;
    private Environment environment;
    private final UserService userService;

    @Autowired
    public PaymentController(PaytmDetails paytmDetails,
                             Environment environment,
                             UserService userService) {
        this.paytmDetails = paytmDetails;
        this.environment = environment;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/payment-details-redirect")
    public String getPaymentDetails(@RequestBody UserPaymentDetails userPaymentDetails) throws Exception {
        System.out.println("getPaymentDetails userPaymentDetails.getUserId()" + userPaymentDetails.getUserId());
        System.out.println(userPaymentDetails.getOrderId());
        System.out.println(userPaymentDetails.getTransactionAmount());
        System.out.println("getPaymentDetails");
        return userService.getPayDetailsService(userPaymentDetails);
    }

    @PostMapping("/payment-details-response")
    public String paymentDetailsResponse(HttpServletRequest request) throws Exception {

        System.out.println("Inside --  paymentDetailsResponse");
        String paytmChecksum = "CHECKSUM_VALUE";
        String result;

        /* Create a TreeMap from the parameters received in POST */
        TreeMap<String, String> paytmParams = new TreeMap<String, String>();
        for (Map.Entry<String, String[]> requestParamsEntry : request.getParameterMap().entrySet()) {
            if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())) {
                paytmChecksum = requestParamsEntry.getValue()[0];
            } else {
                paytmParams.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
            }
        }

        boolean isVerifySignature = PaytmChecksum.verifySignature(paytmParams, paytmDetails.getMerchantKey(), paytmChecksum);
        if (isVerifySignature) {
            //System.out.append("Checksum Matched");
            result = "Checksum Matched - Payment Successful";
        } else {
            //System.out.append("Checksum Mismatched");
            result = "Checksum Mismatched - Payment Unsuccessful";
        }

        return result;
    }

    @GetMapping("invoice/{userId}")
    public User getInvoice(@PathVariable Long userId) {
        return userService.getInvoice(userId);
    }
}