package com.nplatform;

import com.nplatform.utils.HttpRequestUtils;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    // curl --header "Content-Type: application/json" \
    //>   --request POST \
    //>   --data '{"device":"N695JJ3WR3","command":"on"}' \
    //>      http://54.152.1.79:1880/command
    public static void main(String[] args) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String response = HttpRequestUtils.post("http://54.152.1.79:1880/command",
                "{\"device\":\"N695JJ3WR3\",\"command\":\"on\"}", headers);
        System.out.println(response);
    }

}
