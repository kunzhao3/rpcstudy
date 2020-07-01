package provider;

import protocol.Protocol;
import protocol.ProtocolFactory;
import framework.Url;
import provider.api.HelloService;
import provider.impl.HelloServiceImpl;
import register.RomeRegister;

public class Provider {
    public static void main(String[] args) {
        // 1.本地注册
        // 服务名：实现类
        LocalRegister.register(HelloService.class.getName(), HelloServiceImpl.class);
        // 2.远程注册
        // 服务名：list<Url>
        Url url=new Url("localhost",8080);
        RomeRegister.register(HelloService.class.getName(),url);
        // 3.启动Tomcat
        Protocol protocol= ProtocolFactory.getProtocol();
        protocol.start(url);
    }
}
