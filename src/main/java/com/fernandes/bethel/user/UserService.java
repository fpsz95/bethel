package com.fernandes.bethel.user;

import com.fernandes.bethel.maintenance.MaintenanceCharges;
import com.fernandes.bethel.payments.paytm.PaytmDetails;
import com.fernandes.bethel.payments.paytm.UserPaymentDetails;
import com.fernandes.bethel.society.Society;
import com.fernandes.bethel.society.SocietyRepository;
import com.paytm.pg.merchant.PaytmChecksum;
import net.minidev.json.JSONObject;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final SocietyRepository societyRepository;
    private final PasswordEncoder passwordEncoder;
    private final Society society;
    private final PaytmDetails paytmDetails;

    @Autowired
    public UserService(UserRepository userRepository,
                       SocietyRepository societyRepository,
                       PasswordEncoder passwordEncoder,
                       Society society,
                       PaytmDetails paytmDetails) {
        this.userRepository = userRepository;
        this.societyRepository = societyRepository;
        this.passwordEncoder = passwordEncoder;
        this.society = society;
        this.paytmDetails = paytmDetails;
    }

    public String addUserAdmin(UserRegistrationRequest userRegistrationRequest){
        System.out.println("*******************************************++++++++++++++++++++++++++++++++" + userRegistrationRequest);
        System.out.println(userRegistrationRequest.getSociety());
        System.out.println(userRegistrationRequest.getSociety().getSocietyId());
        if(societyRepository.existsById(userRegistrationRequest.getSociety().getSocietyId())) {
//            User user = new User(
//                    userRegistrationRequest.getFirstName(),
//                    userRegistrationRequest.getLastName(),
//                    userRegistrationRequest.getUsername(),
//                    passwordEncoder.encode(userRegistrationRequest.getPassword()),
//                    userRegistrationRequest.getRoles()
//                   // userRegistrationRequest.getSociety().getId()
//            );
            Optional<Society> society = societyRepository.findById(userRegistrationRequest.getSociety().getSocietyId());
            User newUser = new User();
            newUser.setFirstName(userRegistrationRequest.getFirstName());
            newUser.setLastName(userRegistrationRequest.getLastName());
            newUser.setUsername(userRegistrationRequest.getUsername());
            newUser.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
            newUser.setRoles("ADMIN");
            newUser.setSociety(society.get());
            userRepository.save(newUser);
//            society.setUser(user);
//            societyRepository.save(society);
        }
        else{
            System.out.println("Society not Found");
        }
       //user.setSociety(userRegistrationRequest.getSociety()); //works but enters wrong id
        //user.setSociety(userRegistrationRequest.getUserSocietyBethelRegistrationId());
        return "User MIGHT be registered if societyId exists";
       // return userRepository.`
    }

    public String addUserMember(UserRegistrationRequest userRegistrationRequest){
        System.out.println("*******************************************++++++++++++++++++++++++++++++++" + userRegistrationRequest);
        System.out.println(userRegistrationRequest.getSociety());
        System.out.println(userRegistrationRequest.getSociety().getSocietyId());
        if(societyRepository.existsById(userRegistrationRequest.getSociety().getSocietyId())) {
//            User user = new User(
//                    userRegistrationRequest.getFirstName(),
//                    userRegistrationRequest.getLastName(),
//                    userRegistrationRequest.getUsername(),
//                    passwordEncoder.encode(userRegistrationRequest.getPassword()),
//                    userRegistrationRequest.getRoles()
//                   // userRegistrationRequest.getSociety().getId()
//            );
            Optional<Society> society = societyRepository.findById(userRegistrationRequest.getSociety().getSocietyId());
            User user = new User();
            user.setFirstName(userRegistrationRequest.getFirstName());
            user.setLastName(userRegistrationRequest.getLastName());
            user.setUsername(userRegistrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
            user.setRoles("MEMBER");
            user.setSociety(society.get());
            userRepository.save(user);
//            society.setUser(user);
//            societyRepository.save(society);
        }
        else{
            System.out.println("Society not Found");
        }
        //user.setSociety(userRegistrationRequest.getSociety()); //works but enters wrong id
        //user.setSociety(userRegistrationRequest.getUserSocietyBethelRegistrationId());
        return "User MIGHT be registered if societyId exists";
        // return userRepository.`
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

//    public List<User> getAllSocietyMembersList() {
//        return userRepository.findAllById(Collections.singleton(2));
//    }

    public List<User> getAllSocietyMembersList(Long secretarySocietyId) {
        return userRepository.findAllBySocietyId(secretarySocietyId);
    }

    public String setMaintenanceCharges(Long profileId,
                                        MaintenanceCharges maintenanceCharges) {

        Optional<User> optional = userRepository.findById(profileId);
        User user = optional.get();
        user.setOrderId(maintenanceCharges.getOrderId());
        user.setServiceCharges(maintenanceCharges.getServiceCharges());
        user.setWaterCharges(maintenanceCharges.getWaterCharges());
        user.setTotalCharges(maintenanceCharges.getTotalMaintenanceCharges());
        userRepository.save(user);
        return "setMaintenanceCharges";
    }

    public User getInvoice(Long userId) {
        Optional<User> optional = userRepository.findById(userId);
        User user = optional.get();
        user.getOrderId();
        user.getServiceCharges();
        user.getWaterCharges();
        user.getTotalCharges();
        return user;
    }

    public String getPayDetailsService(UserPaymentDetails userPaymentDetails) throws Exception {

        System.out.println("Inside getPayDetailsService");
        Optional<User> optional = userRepository.findById(userPaymentDetails.getUserId());
        User user = optional.get();

        JSONObject paytmParams = new JSONObject();

        Long orderId = user.getOrderId();
        System.out.println("OrderId is " + orderId);

        JSONObject body = new JSONObject();
        body.put("requestType", "Payment");
        body.put("mid", "CGdjWI85481997714720");
        body.put("websiteName", "WEBSTAGING");
        //body.put("orderId",userPaymentDetails.getOrderId());
        body.put("orderId", orderId);
        body.put("callbackUrl", "http://localhost:8080/payment/payment-details-response");

        JSONObject txnAmount = new JSONObject();
//        txnAmount.put("value", userPaymentDetails.getTransactionAmount());
        txnAmount.put("value", user.getTotalCharges());
        txnAmount.put("currency", "INR");

        JSONObject userInfo = new JSONObject();
//        userInfo.put("custId", userPaymentDetails.getUserId());
        userInfo.put("custId", user.getId());
        body.put("txnAmount", txnAmount);
        body.put("userInfo", userInfo);

        System.out.println("Paytm Merchant Key" + paytmDetails.getMerchantKey());

        String checksum = PaytmChecksum.generateSignature(body.toString(), paytmDetails.getMerchantKey());
        System.out.println("generateSignature Returns: " + checksum);

        JSONObject head = new JSONObject();
        head.put("signature", checksum);

        paytmParams.put("body", body);
        paytmParams.put("head", head);

        String postData = paytmParams.toString();
        System.out.println(postData);

        //******************WEBCLIENT IS GIVING RESPONSE ONLY SOMETIMES, PLEASE RECTIFY*****************************

        WebClient client = WebClient.builder()
                .baseUrl("https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();

        WebClient.RequestBodySpec bodySpec = uriSpec.uri("?mid=" + "CGdjWI85481997714720"
                + "&orderId=" + orderId
        );

        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue(postData);

        WebClient.ResponseSpec responseSpec = headersSpec.header(
                HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();

        System.out.println(responseSpec);

        Mono<String> result = responseSpec
                .bodyToMono(String.class);

        String response = result.block();


        System.out.println("getPaymentDetails method finished" + response + "rs is =" + response);

//        return webClient
//                .post()
//                .uri("https://securegw-stage.paytm.in/order/process" + "?mid=" + "CGdjWI85481997714720" + "&orderId=" + "ORDER_ID_698122")
//                .syncBody(postData).retrieve().bodyToMono(String.class);

//        WebClient webClient = WebClient.create();
//        JsonPaytmResponse response = webClient.post()
//                .uri("https://securegw-stage.paytm.in/order/process" + "?mid=" + "CGdjWI85481997714720" + "&orderId=" + "ORDER_ID_698122")
//                .body(postData)
//                .retrieve()
//                .bodyToMono(JsonPaytmResponse.class)
//                .block();
        return response;
    }

//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return userRepository.findAll();
//    }

//    @Override
//    public Page<User> findPaginated(int pageNo, int pageSize) {
//        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//        return this.userRepository.findAll(pageable);
//    }


}
