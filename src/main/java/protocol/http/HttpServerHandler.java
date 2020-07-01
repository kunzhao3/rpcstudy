package protocol.http;

import framework.Invocation;
import provider.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.commons.io.IOUtils;

public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        try {
            InputStream inputStream = req.getInputStream();
            ObjectInputStream ois=new ObjectInputStream(inputStream);
            Invocation invocation = (Invocation) ois.readObject();
            Class implClass = LocalRegister.get(invocation.getInterfaceName());
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
