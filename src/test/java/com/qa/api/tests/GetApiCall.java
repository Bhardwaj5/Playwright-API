package com.qa.api.tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetApiCall extends BaseTest {

    @Test
    public void getSpecificResponse() throws IOException {
        APIResponse apiResponse = apiReqContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create().setQueryParam("gender","female").
                        setQueryParam("status","active"));
        System.out.println(apiResponse.status());

        byte[] respBody = apiResponse.body();
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode getJsonBody =  objMapper.readTree(respBody);
        String getjsonResponse = getJsonBody.toPrettyString();
        System.out.println(getjsonResponse);

    }

    @Test
    public void getResponse() throws IOException {
        APIResponse apiResponse = apiReqContext.get("https://gorest.co.in/public/v2/users");
        System.out.println(apiResponse.status());

        byte[] respBody = apiResponse.body();
        ObjectMapper objMapper = new ObjectMapper();
        JsonNode getJsonBody =  objMapper.readTree(respBody);
        String getjsonResponse = getJsonBody.toPrettyString();
//        System.out.println(getjsonResponse);
        System.out.println(getJsonBody.asText("id"));
//        getJsonBody.get("id");

    }
}
