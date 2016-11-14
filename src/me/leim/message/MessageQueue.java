package me.leim.message;

import com.netease.pomelo.PomeloClient;
import org.json.JSONObject;

import java.util.LinkedList;

/**
 * Created by leimingtang on 2016/11/14.
 */
public abstract class MessageQueue {

    protected LinkedList<JSONObject> queue = new LinkedList<>();

    public boolean offer(JSONObject msg){
        return queue.offerLast(msg);
    }

    public JSONObject poll(){
        return queue.pollFirst();
    }

    public abstract void processMessage(PomeloClient client, String roomId,JSONObject jsonObject);

    public void process(final PomeloClient client,final String roomId){
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true){
                    if (queue.size() > 0){
                        JSONObject jsonObject = poll();
                        processMessage(client,roomId,jsonObject);
                        try {
                            /*1秒的发言频率限制，此处可能会出现不同类型的消息冲突的情况*/
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        };
        thread.start();
    }

}
