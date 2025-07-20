package com.qa.api.tests;

import com.api.data.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import com.qa.api.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PutAPICall extends BaseTest {

     @Test
     public void usePutCall() throws JsonProcessingException {
         ///  Using Lombok
         Users users = Users.builder()
                 .name("rb")
                 .email("r"+System.currentTimeMillis()+"@gmail.com")
                 .gender("male")
                 .status("active").build();


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
         System.out.println("#----------------- Post ------------------#");
         System.out.println(actUser.getId());
         System.out.println(actUser.getEmail());
         System.out.println(actUser.getStatus());

         users.setStatus("inactive");

         APIResponse apiPutResp = apiReqContext.put("https://gorest.co.in/public/v2/users/"+ actUser.getId(),
                 RequestOptions.create()
                         .setHeader("Content_Type", "application/json")
                         .setHeader("Authorization", "Bearer dc41ae187a2dd2d9f25eedea6ae6405494c17db61dc23fa790ead04a1bd45590")
                         .setData(users));

         Users actUser1 = objDeserializer.readValue(apiPutResp.text(),Users.class);
         System.out.println("#----------------- Put ------------------#");
         System.out.println(actUser1.getId());
         System.out.println(actUser1.getStatus());
     }
}
