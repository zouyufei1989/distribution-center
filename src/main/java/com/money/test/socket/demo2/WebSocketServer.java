package com.money.test.socket.demo2;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket/{routeId}/{userId}")
@Component
public class WebSocketServer {

    public final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);


    private final static Map<String, Map<String, WebSocketServer>> connectionMap = new ConcurrentHashMap<>();

    private Session session;
    private String userId;
    private String routeId;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("routeId") String routeId, @PathParam("userId") String userId) throws IOException {

        if (!connectionMap.containsKey(routeId)) {
            connectionMap.put(routeId, new ConcurrentHashMap<>());
        }
        connectionMap.get(routeId).put(userId, this);
        this.session = session;
        this.userId = userId;
        this.routeId = routeId;

        logger.info("连接成功:routeId:{} userId:{}", routeId, userId);
        sendMessage(this.userId, "加入聊天", 0);

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() throws IOException {
        sendMessage(this.userId, "已退出聊天", 0);

        if (!connectionMap.containsKey(routeId)) {
            logger.info("线路{}聊天不存在.");
            return;
        }

        if (!connectionMap.get(routeId).containsKey(userId)) {
            logger.info("{}已退出线路{}聊天.", userId, routeId);
            return;
        }

        connectionMap.get(routeId).remove(userId);
        logger.info("关闭连接成功");

    }

    /**
     * 收到客户端消息后触发的方法
     */
    @OnMessage
    public void onMessage(String message) throws IOException {

        WebSocketMessage messageObj = JSON.parseObject(message, WebSocketMessage.class);
        sendMessage(this.userId, messageObj.getContent(), messageObj.getType());
    }

    private void sendMessage(String userId, String content, Integer type) throws IOException {
        Map<String, WebSocketServer> passengers = connectionMap.get(routeId);
        if (passengers.size() == 0) {
            return;
        }

        WebSocketMessage returnMsg = new WebSocketMessage();
        returnMsg.setUserId(userId);
        returnMsg.setContent(content);
        returnMsg.setType(type);
        String jsonStr = JSON.toJSONString(returnMsg);

        for (Map.Entry<String, WebSocketServer> entry : passengers.entrySet()) {
            entry.getValue().session.getBasicRemote().sendText(jsonStr);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

}
