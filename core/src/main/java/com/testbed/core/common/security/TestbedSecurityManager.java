package com.testbed.core.common.security;


import org.springframework.stereotype.Component;

@Component
public class TestbedSecurityManager extends NftSecurityHandler{

        @Override
        public String randomSalt(){
            return getRandomPassword(10);
        }
}
