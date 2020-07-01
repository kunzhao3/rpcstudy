package protocol.dubbo;

import framework.Invocation;
import protocol.Protocol;
import framework.Url;

public class DubboProtocol implements Protocol {
    public void start(Url url) {
        NettyService nettyService=new NettyService();
        nettyService.start(url.getHostName(),url.getPost());
    }

    public String send(Url url, Invocation invocation) {
        NettyClient nettyClient=new NettyClient();
        return nettyClient.send(url.getHostName(),url.getPost(),invocation);
    }
}
