package org.jobsl.cgames.cchess.net;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.Channel;
import org.apache.commons.lang3.StringUtils;
import org.jobsl.cgames.net.Handler;
import org.jobsl.cgames.net.msg.Message;
import org.jobsl.cgames.net.msg.MessageHandler;

/**
 * @author JobsLee
 */
public class ChessHandler extends Handler implements MessageHandler {
    @Override
    public void send(Channel channel, Message msg) {
        channel.writeAndFlush(msg);
    }

    @Override
    public void rec(Message msg) {
        String msgJson = msg.getMsg();
        if (StringUtils.isNotBlank(msgJson)) {
            ChessMessage chessMsg = JSONObject.parseObject(msgJson, ChessMessage.class);
            if (chessMsg != null) {
                // todo  check msg sign and recId, then accroding to the COMMAND to do moving operation or any other op...
            }
        }
    }
}
