package protocol.http;

import framework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public String send(String hostName, Integer post, Invocation invocation){
        ObjectOutputStream oos=null;
        try {
            URL url =new URL("http",hostName,post,"/");
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            oos=new ObjectOutputStream(outputStream);
            oos.writeObject(invocation);


            InputStream inputStream = httpURLConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return  result;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           if(oos!=null){
               try {
                   oos.flush();
                   oos.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        }
        return null;
    }
}
