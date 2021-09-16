package com.resttechsolutions.contactapi6.resource.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Response {

    public Map<String, Object> response;

    public Response(){
        this.response = new HashMap<>();

        this.response.put("date", LocalDateTime.now());
        this.response.put("error", false);
        this.response.put("transaction", Math.random() * 100);
        this.response.put("data", null);
        this.response.put("message", "");
    }
}
