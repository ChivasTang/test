package com.dyg.test;

import com.dyg.test.service.ApiGW;
import com.dyg.test.service.ApiGWImpl;

public class Test {
    public static void main(String[] args){
        ApiGW apiGW=new ApiGWImpl();
        apiGW.sendApiGW();
    }
}
