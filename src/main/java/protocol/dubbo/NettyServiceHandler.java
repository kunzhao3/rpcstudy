package protocol.dubbo;

import framework.Invocation;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import provider.LocalRegister;

import java.lang.reflect.Method;

public class NettyServiceHandler extends ChannelInboundHandlerAdapter {
    /**
     * 功能：读取客服端发送过来的信息
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取数据
        Invocation invocation= (Invocation)msg;
        System.out.println("接收客户端数据");
        System.out.println(invocation.toString());
        //向客户端写数据
        System.out.println("server向client发送数据");
        Class implClass = LocalRegister.get(invocation.getInterfaceName());
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        String result = (String) method.invoke(implClass.newInstance(), invocation.getParams());
        System.out.println(result);
        ctx.writeAndFlush("Netty"+result);
    }
}
