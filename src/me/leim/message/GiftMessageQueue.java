package me.leim.message;

import com.netease.pomelo.PomeloClient;
import me.leim.util.ChatUtil;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by leimingtang on 2016/11/14.
 */
public class GiftMessageQueue extends MessageQueue {
    @Override
    public void processMessage(PomeloClient client, String roomId, JSONObject jsonObject) {
        try {
            JSONObject body = jsonObject.getJSONObject("body");
            if (body != null && !body.isNull("rName")){
                String giftName = body.isNull("giftName") || "".equals(body.getString("giftName")) ?
                        "千帆星" : body.getString("giftName");
                String content = "感谢 " + body.getString("rName") + " 送的" + giftName + "*" + body.getString("amount") + "！";
                ChatUtil.sendChatMsg(client,roomId,content,"100");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
