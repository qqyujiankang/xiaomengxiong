package com.example.et.util;//package com.example.administrator.huijianzhi.util;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.util.Log;
//
//
//import org.java_websocket.WebSocket;
//import org.java_websocket.WebSocketAdapter;
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.handshake.ServerHandshake;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import de.tavendo.autobahn.WebSocketConnection;
//import de.tavendo.autobahn.WebSocketException;
//import de.tavendo.autobahn.WebSocketHandler;
//
//
///**
// * Created by Administrator on 2016/12/28.
// */
//
//public class WebSocketServiceConnection extends
// {
//    private  Context mContext;
//    private static WebSocketServiceConnection singleton = null;
//
////限制产生多个对象
//
//    private WebSocketServiceConnection(){
//    }
//
////通过该方法获得实例对象
//
//    public static WebSocketServiceConnection getInstance(){
//
//        if(singleton == null){
//            singleton = new WebSocketServiceConnection();
//        }
//        return singleton;
//    }
//
//private WebSocketClient mWebSocketClient;
//    public void connect(final Context mContext) throws URISyntaxException {
//        this.mContext=mContext;
//        Log.i("exp","-mcontext2-------------"+mContext.getPackageName());
//
//        if(mWebSocketClient == null) {
//            // WebSocketServiceConnection.getInstance().connect("ws://101.200.82.193:8080/hzww_manage/web/websocket" ,
//            mWebSocketClient = new WebSocketClient(new URI("ws://myapp.vipgz1.idcfengye.com/websocket?SESSION_USERNAME=wo_dao_Le")) {
//
//
//                @Override
//                public void onOpen(ServerHandshake serverHandshake) {
//                    Log.i("exp","opened connection");
//
//
//                    mWebSocketClient.send("");
//
//                }
//
//                @Override
//                public void onMessage(String s) {
//                    //服务端消息来了
//                    Log.i("exp","====================received:" + s);
//                 /*   Message msg = Message.obtain();
//                    msg.what = 0x111;
//                    Bundle bundle = new Bundle();
//                    bundle.putString("news",s);
//                    msg.setData(bundle);
//                    handler.sendMessage(msg);*/
//                }
//
//                @Override
//                public void onClose(int i, String s, boolean remote) {
//                    //连接断开，remote判定是客户端断开还是服务端断开
//                    Log.i("exp","=======================Connection closed by " + ( remote ? "remote peer" : "us" ) + ", info=" + s);
//                    //
//                  /*  closeConnect();*/
//                }
//
//                @Override
//                public void onError(Exception e) {
//                    Log.i("LOG","error:" + e);
//                }
//            };
//        }
//      /*  try {
//            WebSocketContainer container = ContainerProvider.getWebSocketContainer(); // 获取WebSocket连接器，其中具体实现可以参照websocket-api.jar的源码,Class.forName("org.apache.tomcat.websocket.WsWebSocketContainer");
//            String uri = "ws://myapp.vipgz1.idcfengye.com/websocket";
//            Session session = container.connectToServer(Client.class, new URI(uri)); // 连接会话
//            session.getBasicRemote().sendText("123132132131"); // 发送文本消息
//            session.getBasicRemote().sendText("4564546");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//       // mWebSocketClient.connect();
//        try {
//
//            mWebSocketClient.connectBlocking();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//    public void connect1(final Context mContext) {
//        this.mContext=mContext;
//        Log.i("exp","-mcontext2-------------"+mContext.getPackageName());
//        if (WebSocketServiceConnection.getInstance().isConnected()){
//
//        }else {
//            try {
//
//                // WebSocketServiceConnection.getInstance().connect("ws://101.200.82.193:8080/hzww_manage/web/websocket?tokenId=" + mContext.getSharedPreferences("account", Context.MODE_PRIVATE).getString("phone", "phone").trim(),
//
//                WebSocketServiceConnection.getInstance().connect("ws://myapp.vipgz1.idcfengye.com/websocket",
//
//
//                        new WebSocketHandler() {
//
//                            private String payload;
//
//                            @Override
//                            public void onBinaryMessage(byte[] payload) {
//
//                                System.out.println("onBinaryMessage size="
//                                        + payload.length);
//
//                            }
//
//                            @Override
//                            public void onClose(int code, String reason) {
//
//
//                                switch (code) {
//                                    case 1:
//                                        break;
//                                    case 2:
//                                        break;
//                                    case 3://手动断开连接    调用webSocketConnection.disconnect();
//
//                                        break;
//                                    case 4:
//                                        break;
//
//                                    case 5://网络断开连接----( 服务器已断开)
//
//
//                                        Log.i("exp","==="+WebSocketServiceConnection.getInstance().isConnected());
//                                        // WebSocketServiceConnection.getInstance().disconnect();
//
//                                        break;
//                                }
//                            }
//
//                            @Override
//                            public void onOpen() {
//                                Log.i("exp", "tokenId=18295935896 connect-----3----");
//                                singleton.sendTextMessage("18295935896");
//
//                            }
//
//                            @Override
//                            public void onRawTextMessage(byte[] payload) {
//
//                                System.out.println("onRawTextMessage size="
//                                        + payload.length);
//                            }
//
//                            @Override
//                            public void onTextMessage(String payload) {
//                                Log.i("exp", "----onTextMessage-----" + payload);
//                                //webSocketConnection.disconnect();
//                                //setPopularityPopupWindow(BaseActivity.getrunningActivity());
//                                this.payload=payload;
//                                if(payload.equals("101")){
//                                    //webSocketConnection.
//                                    singleton.disconnect();
//                                    //webSocketConnection=null;
//                                }
//                            }
//
//
//                        });
//            } catch (WebSocketException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
