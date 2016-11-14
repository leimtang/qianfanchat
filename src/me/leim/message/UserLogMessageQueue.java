package me.leim.message;

import com.netease.pomelo.PomeloClient;
import me.leim.util.ChatUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leimingtang on 2016/11/14.
 */
public class UserLogMessageQueue extends MessageQueue {

    @Override
    public void processMessage(PomeloClient client, String roomId,JSONObject jsonObject) {
        try {
            JSONObject body = jsonObject.getJSONObject("body");
            if (body != null && !body.isNull("userName")){
                String content = "欢迎 " + body.getString("userName") + " 进入房间！";
                ChatUtil.sendChatMsg(client,roomId,content,"100");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
