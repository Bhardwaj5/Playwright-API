package com.qa.api.base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    protected Playwright playwright;
    protected APIRequest apiReq;
    protected APIRequestContext apiReqContext;

    @BeforeTest
    public void setup()
    {
         playwright = Playwright.create();
         apiReq =  playwright.request();
         apiReqContext = apiReq.newContext();
    }

    @AfterTest
    public void tearDown()
    {
        playwright.close();
    }
}
