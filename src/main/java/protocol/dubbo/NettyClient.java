package protocol.dubbo;

import framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import provider.api.HelloService;
import util.DecoderUtil;
import util.EncoderUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NettyClient {
    public  NettyClientHandler handler=null;
    private static ExecutorService executorService = Executors.newCachedThreadPool();
    public void start(String hostname,Integer post){
        NioEventLoopGroup work=new NioEventLoopGroup();
        try {
            handler=new NettyClientHandler();
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(work);
            bootstrap.handler(new ChannelInitializer<Channel>(){
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new DecoderUtil());
                    channel.pipeline().addLast(new EncoderUtil());
                    channel.pipeline().addLast(handler);
                }
            });
            ChannelFuture future = bootstrap.connect(hostname, post).sync();
            //future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
          /*  try {
                work.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
    public String send(String hostname,Integer post, Invocation invocation){
        if(handler ==null){
            start(hostname,post);
        }
        handler.setInvocation(invocation);
        try {
            Future<String> future = executorService.submit(handler);
            return  future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
        return null;
    }

    public static void main(String[] args) {
        NettyClient nettyClient=new NettyClient();
        Invocation invocation=new Invocation(HelloService.class.getName(),"sayhello",
                new Class[]{String.class},new Object[]{"zk"});
        nettyClient.send("localhost",8080,invocation);
    }
}
