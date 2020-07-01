package consumer;

import framework.Invocation;
import framework.ProxyFactory;
import protocol.http.HttpClient;
import provider.api.HelloService;

public class Consumer {
    public static void main(String[] args) {
      /*  HttpClient httpClient=new HttpClient();
        Invocation invocation=new Invocation(HelloService.class.getName(),"sayhello",new Class[]{String.class},new Object[]{"zk"});
        String result = httpClient.send("localhost", 8080, invocation);
        System.out.println(result);*/

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        System.out.println(helloService.sayhello("1344"));
    }
}
