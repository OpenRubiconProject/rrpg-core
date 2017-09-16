package com.openrubicon.core.connector;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.bukkit.Bukkit;

public class TestServerHandler extends ChannelInboundHandlerAdapter{



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //((ByteBuf) msg).release(3);
        ByteBuf in = (ByteBuf) msg;
        Bukkit.getLogger().info(in.toString(io.netty.util.CharsetUtil.US_ASCII));
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
