package com.qa.api.tests;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PostAPICall extends BaseTest {

    @Test
    public void getPostResponse()
    {

        /// Data
        Map<String,Object> data = new HashMap<>();
        data.put("name","PlayW");
        data.put("email","playw"+System.currentTimeMillis()+"@gmail.com");
        data.put("gender","male");
        data.put("status","active");

        APIResponse apiPostResp =  apiReqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content_Type","application/json")
                        .setHeader("Authorization", "Bearer dc41ae187a2dd2d9f25eedea6ae6405494c17db61dc23fa790ead04a1bd45590")
                        .setData(data));

        System.out.println(apiPostResp.status());
        Assert.assertEquals(apiPostResp.status(),201);

        System.out.println("--------- Post Response -----------");
        System.out.println(apiPostResp.text());

        JSONObject js = new JSONObject(apiPostResp.text());
        String id  = String.valueOf(js.getInt("id"));
        System.out.println("ID: "+id);

        APIResponse apiGetResp =  apiReqContext.get("https://gorest.co.in/public/v2/users/"+id,
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer dc41ae187a2dd2d9f25eedea6ae6405494c17db61dc23fa790ead04a1bd45590"));

        System.out.println("--------- Get Response -----------");
        System.out.println(apiGetResp.text());
    }


}
