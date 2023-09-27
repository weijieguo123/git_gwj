/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dfh.CloudAlibaba.demos.nacosconfig;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.listener.AbstractListener;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Configuration
@EnableConfigurationProperties(com.dfh.CloudAlibaba.demos.nacosconfig.User.class)
public class NacosConfigDemoConfiguration {

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Autowired
    private com.dfh.CloudAlibaba.demos.nacosconfig.User user;

    @Value("${user.name}")
    private String userName;

    @Value("${user.age}")
    private int userAge;

    @Value("${user.pass}")
    private int userPass;

    @Bean
    public ApplicationRunner runner() {
        return args -> {
            String dataId = "nacos-config-sample.properties";
            String group = "DEFAULT_GROUP";
            nacosConfigManager.getConfigService().addListener(dataId, group, new AbstractListener() {
                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("[Listener] " + configInfo);
                    System.out.println("[Before User] " + user);

                    Properties properties = new Properties();
                    try {
                        properties.load(new StringReader(configInfo));
                        String name = properties.getProperty("name");
                        int age = Integer.parseInt(properties.getProperty("age"));
                        user.setName(name);
                        user.setAge(age);
                        System.out.println("value读取配置："+userName+userAge);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[After User] " + user);
                    System.out.println("[pass] " + userPass);
                }
            });
        };
    }


    @PostConstruct
    public void init() {
      System.out.printf("[init] user name : %s , age : %d%n", userName, userAge);
     // System.out.printf("1111111111111111111111111--------------"+userName);
    }

    @PreDestroy
    public void destroy() {
        System.out.printf("[destroy] user name : %s , age : %d%n", userName, userAge);
        System.out.printf("pass:", userPass);
      //System.out.printf("1111111111111111111111111"+userName);
    }
}
