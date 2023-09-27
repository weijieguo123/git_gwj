package com.dfh.CloudAlibaba;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CloudAlibabaApplicationTests {

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private int userAge;

    @Value("${user.pass}")
    private int userPass;

    @Test
    void contextLoads() {
        System.out.println(userName);
        System.out.println(userAge);
        System.out.println(userPass);
    }

}
