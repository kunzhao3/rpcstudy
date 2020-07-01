package protocol.http;

import framework.Invocation;
import protocol.Protocol;
import framework.Url;

public class HttpProtocol implements Protocol {


    public void start(Url url) {
        HttpService httpService=new HttpService();
        httpService.start(url.getHostName(),url.getPost());
    }

    public String send(Url url, Invocation invocation) {
        HttpClient httpClient=new HttpClient();
        return httpClient.send(url.getHostName(),url.getPost(),invocation);
    }
}
