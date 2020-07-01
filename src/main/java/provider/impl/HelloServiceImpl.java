package provider.impl;

import provider.api.HelloService;

public class HelloServiceImpl implements HelloService {
    public String sayhello(String name) {
        return "hello"+name;
    }
}
