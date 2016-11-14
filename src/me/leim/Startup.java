package me.leim;

import com.netease.pomelo.DataEvent;
import com.netease.pomelo.DataListener;
import com.netease.pomelo.PomeloClient;
import me.leim.message.GiftMessageQueue;
import me.leim.message.UserLogMessageQueue;
import me.leim.util.ChatUtil;

import static me.leim.util.ChatUtil.sendEnterMsg;

/**
 * Created by leimingtang on 2016/11/14.
 */
public class Startup {

    public static void main(String[] args) {

        String roomId = "room id";
        String userId = "your userid";
        /*token 在登录状态下可访问http://qf.56.com/audience/chat/getDomain.android接口获取*/
        String token = "your token";
        PomeloClient client = ChatUtil.getPomeloClient();
        sendEnterMsg(client,roomId,userId,token);

        /*自动欢迎*/
        final UserLogMessageQueue userLogMessageQueue = new UserLogMessageQueue();
        client.on(ChatUtil.ON_ROUTE_USERLOG, new DataListener() {
            @Override
            public void receiveData(DataEvent dataEvent) {
                userLogMessageQueue.offer(dataEvent.getMessage());
            }
        });
        userLogMessageQueue.process(client,roomId);

        /*自动感谢礼物*/
        final GiftMessageQueue giftMessageQueue = new GiftMessageQueue();
        client.on(ChatUtil.ON_ROUTE_GIFT, new DataListener() {
            @Override
            public void receiveData(DataEvent dataEvent) {
                giftMessageQueue.offer(dataEvent.getMessage());
            }
        });
        giftMessageQueue.process(client,roomId);
    }
}
