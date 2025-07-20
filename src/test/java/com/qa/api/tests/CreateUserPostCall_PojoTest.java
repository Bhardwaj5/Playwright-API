package com.qa.api.tests;
import com.api.data.User;
import com.api.data.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateUserPostCall_PojoTest extends BaseTest {

    @Test
    public void createReq_POJO() throws JsonProcessingException {
        
///  Using Lombok
        Users users = Users.builder()
                .name("rb")
                .email("r"+System.currentTimeMillis()+"@gmail.com")
                .gender("male")
                .status("active").build();

///  Using Generic POJO skeleton
//        User user = new User("rb","rb1995@gmail.com","male","active");

        APIResponse apiPostResp = apiReqContext.post("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setHeader("Content_Type", "application/json")
                        .setHeader("Authorization", "Bearer dc41ae187a2dd2d9f25eedea6ae6405494c17db61dc23fa790ead04a1bd45590")
                        .setData(users));

        System.out.println(apiPostResp.status());
        System.out.println(apiPostResp.text());
        Assert.assertEquals(apiPostResp.status(),201);

        /// Deserialisation (Converting JSON to POJO)
        ObjectMapper objDeserializer = new ObjectMapper();
        Users actUser = objDeserializer.readValue(apiPostResp.text(),Users.class);
        System.out.println("#-----------------------------------#");
        System.out.println(actUser.getId());
        System.out.println(actUser.getEmail());

    }


}
