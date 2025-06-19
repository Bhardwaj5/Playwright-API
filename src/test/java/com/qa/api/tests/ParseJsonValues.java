package com.qa.api.tests;

import org.json.JSONArray;
import org.json.JSONObject;
import com.microsoft.playwright.APIResponse;
import com.qa.api.base.BaseTest;
import org.testng.annotations.Test;

public class ParseJsonValues extends BaseTest {

    @Test
    public void parseNestedJSON()
    {
        APIResponse apiResp =  apiReqContext.get("https://dummyjson.com/carts/1");
        JSONObject js = new JSONObject(apiResp.text());

        JSONArray getNestedJSON_value = js.getJSONArray("products");
        String getTitle = getNestedJSON_value.getJSONObject(0).getString("title");
        System.out.println(getNestedJSON_value);
        int i=0;
        System.out.println("-------------- Fetch ALL the TITLE ---------------");
        while(i<getNestedJSON_value.length())
        {
          String getJSON_Title =  getNestedJSON_value.getJSONObject(i).getString("title");
            System.out.println(getJSON_Title);
            i++;
        }


    }
}
