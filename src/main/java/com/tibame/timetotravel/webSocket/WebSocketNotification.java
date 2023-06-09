package com.tibame.timetotravel.webSocket;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;
import java.util.concurrent.CopyOnWriteArraySet;
import java.io.IOException;

@ServerEndpoint(value = "/websocket/{userName}")
@Component

public class WebSocketNotification {
    //靜態變數，用來記錄當前上線人數。應該把它設計成執行緒安全的。
    private static int onlineCount = 0;

    //concurrent package的執行緒安全Set，用來存放每個客戶端對應的MyWebSocket對象。
    private static CopyOnWriteArraySet<WebSocketNotification> webSocketSet = new CopyOnWriteArraySet<WebSocketNotification>();

    //與某個客戶端的連接會話，需要通過它來給客戶端發送數據
    private Session session;

    private String userName; // 用户身份信息

    /**
     * 連接建立成功呼叫的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userName") String userName) {
        this.session = session;
        this.userName = userName;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在線人數加1
        System.out.println("有新連接加入！當前在線人數為" + getOnlineCount());
        try {
            System.out.println(userName);
            if(!("Admin").equals(userName)) {
                sendMessage("服務人員: 你好! 我有什麼可以為您服務的");
            }

//            sendMessage(String.valueOf(this.session.getQueryString()));
//            sendInfo(String.valueOf(this.session.getQueryString()));
        } catch (IOException e) {
            System.out.println("IO異常");
        }
    }

    /**
     * 連結關閉呼叫的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //從set中刪除
        subOnlineCount();           //在線人數減1
        System.out.println("有一連接關閉！當前在線人數為" + getOnlineCount());
    }

    /**
     * 收到客戶端訊息後呼叫的方法
     *
     * @param message 客戶端發送過來的訊息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("來自客戶端的訊息:" + message);

        //群體發送
        for (WebSocketNotification item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 發生錯誤時呼叫
     @OnError
      * */
    public void onError(Session session, Throwable error) {
        System.out.println("發生錯誤");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群體發送自訂義訊息
     * */
    public static void sendInfo(String message) throws IOException {
        for (WebSocketNotification item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketNotification.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketNotification.onlineCount--;
    }
}