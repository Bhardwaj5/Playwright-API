package com.qa.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserPostCall_PojoTest extends BaseTest {

    @Test
    public void createReq_POJO() throws JsonProcessingException {
        User user = new User("rb","rb95@gmail.com","male","active");
        APIResponse apiPostResp = apiReqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content_Type", "application/json")
                        .setHeader("Authorization", "Bearer dc41ae187a2dd2d9f25eedea6ae6405494c17db61dc23fa790ead04a1bd45590")
                        .setData(user));

        System.out.println(apiPostResp.status());
        System.out.println(apiPostResp.text());
        Assert.assertEquals(apiPostResp.status(),201);

        /// Deserialisation (Converting JSON to POJO)
        ObjectMapper objDeserializer = new ObjectMapper();
        User actUser = objDeserializer.readValue(apiPostResp.text(),User.class);
        System.out.println("#-----------------------------------#");
        System.out.println(actUser.getId());
        System.out.println(actUser.getEmail());

    }


}
