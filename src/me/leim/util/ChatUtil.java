package me.leim.util;

import com.netease.pomelo.DataCallBack;
import com.netease.pomelo.PomeloClient;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tangleiming on 2016/11/10.
 */
public class ChatUtil {

    /*服务器地址*/
    private static final String HOST = "http://sio.connector.chat.qf.56.com";
    /*端口*/
    private static final int PORT = 4021;

    /*ROUTE*/
    /*登录*/
    public static final String ROUTE_ENTER = "connector-sio.entryHandler.enter";
    /*公开聊天*/
    public static final String ROUTE_CHAT = "chat.chatHandler.send";
    /*私聊*/
    public static final String ROUTE_PCHAT = "chat.chatHandler.privateSend";
    /*匿名聊天*/
    public static final String ROUTE_NOLOGINCHAT = "chat.chatHandler.noLoginSend";

    /*登录消息标志*/
    public static final String ON_ROUTE_USERLOG = "onUserLog";
    /*礼物消息标志*/
    public static final String ON_ROUTE_GIFT = "onGift";

    /**
     * 连接服务器并初始化
     * @return
     */
    public static PomeloClient getPomeloClient(){
        PomeloClient client = new PomeloClient(HOST,PORT);
        client.init();
        return client;
    }

    /**
     * 登录房间
     * @param client
     * @param roomId
     * @param userId
     * @param token
     */
    public static void sendEnterMsg(PomeloClient client, String roomId, String userId, String token){
        JSONObject msg = new JSONObject();
        try {
            msg.put("userId",userId);
            msg.put("roomId",roomId);
            msg.put("token",token);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.request(ROUTE_ENTER, msg, new DataCallBack(){
            public void responseData(JSONObject msg){
                System.out.println(msg);
            }
        });
    }

    /**
     * 发送公开消息
     * @param client
     * @param roomId
     * @param content
     * @param action
     */
    public static void sendChatMsg(PomeloClient client, String roomId, String content, String action){
        JSONObject msg = new JSONObject();
        try {
            msg.put("roomId",roomId);
            msg.put("msg",content);
            msg.put("action",action);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.request(ROUTE_CHAT, msg, new DataCallBack(){
            public void responseData(JSONObject msg){
                System.out.println(msg);
            }
        });
    }




}
