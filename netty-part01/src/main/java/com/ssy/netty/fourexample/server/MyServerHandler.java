package com.ssy.netty.fourexample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author xiaotian.huang
 * @date 2019-04-24
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;

            String evtType = null;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    evtType = "读空闲";
                    break;
                case WRITER_IDLE:
                    evtType = "写空闲";
                    break;
                case ALL_IDLE:
                    evtType = "读写空闲";
                    break;
            }

            System.out.println(ctx.channel().remoteAddress() + " 超时事件: " + evtType);
            ctx.channel().close();
        }
    }
}
