package framework;

import protocol.Protocol;
import protocol.ProtocolFactory;
import protocol.http.HttpClient;
import register.RomeRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static <T> T getProxy(final Class interfaceClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HttpClient httpClient=new HttpClient();
                Invocation invocation=new Invocation(interfaceClass.getName(),method.getName(),method.getParameterTypes(),args);
                Url url = RomeRegister.get(interfaceClass.getName());
                Protocol protocol= ProtocolFactory.getProtocol();
                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }
}
