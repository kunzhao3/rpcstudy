package protocol.dubbo;

import framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private  ChannelHandlerContext context;
    private Invocation invocation;
    private String result;

    /**
     * 功能：客户端连接服务器后被调用,发送信息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context=ctx;
    }

    /**
     * 功能：读取服务器返回的信息
     */
    @Override
    public  synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = (String) msg;
        notify();
    }

    @Override
    public synchronized  Object call() throws Exception {
        context.writeAndFlush(this.invocation);
        wait();
        return result;
    }
    public ChannelHandlerContext getContext() {
        return context;
    }

    public void setContext(ChannelHandlerContext context) {
        this.context = context;
    }

    public Invocation getInvocation() {
        return invocation;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}
