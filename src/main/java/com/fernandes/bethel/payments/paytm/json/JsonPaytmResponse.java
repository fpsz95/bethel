package com.fernandes.bethel.payments.paytm.json;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class JsonPaytmResponse {

    private Head head;
    private Body body;

    /**
     * No args constructor for use in serialization
     *
     */
    public JsonPaytmResponse() {
    }

    /**
     *
     * @param head
     * @param body
     */
    public JsonPaytmResponse(Head head, Body body) {
        super();
        this.head = head;
        this.body = body;
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
