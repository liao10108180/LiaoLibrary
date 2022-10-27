package com.liao.librarydemo.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.liao.common.util.LogUtils;
import com.liao.library.base.activity.BaseVMActivity;
import com.liao.librarydemo.R;
import com.liao.librarydemo.databinding.ActivityWebsocketBinding;
import com.liao.librarydemo.viewmodel.WebSocketViewModel;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

/**
 * @Author: LiaoZhenHui
 * @CreateDate: 2022-08-15
 * @Description: description class
 */
public class WebSocketActivity extends BaseVMActivity<WebSocketViewModel, ActivityWebsocketBinding> {

    private OkHttpClient mOkHttpClient;

    private WebSocket mWebSocket;

    private Request mRequest;

    @Override
    protected WebSocketViewModel createViewModel() {
        return new WebSocketViewModel();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_websocket;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mOkHttpClient = new OkHttpClient.Builder()
                .pingInterval(10, TimeUnit.SECONDS) // 设置 PING 帧发送间隔---包活
                .build();

        mRequest = new Request.Builder()
                .url("ws://192.168.99.2:8010/test")
                .build();

        mBinding.btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();

            }
        });

        mBinding.btnDisconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog confirmDialog = new AlertDialog.Builder(WebSocketActivity.this)
                        .setMessage("确认断开WebSocket?")
                        .setPositiveButton("确定", (dialog, which) -> disconnect())
                        .setNegativeButton("取消", (dialog, which) -> {

                        }).create();

                confirmDialog.show();

            }
        });

        mBinding.btnSend.setOnClickListener(v -> {
            if (mWebSocket != null) {
                boolean isSend = mWebSocket.send(mBinding.et.getText().toString().trim());

                if (isSend) {
                    showToast("消息发送成功");
                    mBinding.et.setText("");
                } else {
                    showToast("消息发送失败");
                }
            }
        });
    }

    private void connect() {
        mOkHttpClient.newWebSocket(mRequest, new WebSocketListener() {

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                super.onOpen(webSocket, response);
                // WebSocket 连接建立
                LogUtils.iTag(TAG, "WebSocket建立连接");
                mWebSocket = webSocket;

            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                super.onMessage(webSocket, text);
                // 收到服务端发送来的 String 类型消息
                LogUtils.iTag(TAG, "WebSocket接收消息:" + text);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                super.onMessage(webSocket, bytes);
            }

            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosed(webSocket, code, reason);
                //WebSocket 连接关闭
                LogUtils.iTag(TAG, "WebSocket服务端关闭连接");
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                super.onClosing(webSocket, code, reason);
                //客户端主动关闭时回调
                LogUtils.iTag(TAG, "WebSocket客户端关闭连接");
            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                super.onFailure(webSocket, t, response);
                // 出错了
                t.printStackTrace();

                if (t instanceof SocketTimeoutException) {
                    LogUtils.eTag(TAG, "WebSocket连接超时!");

                } else if (t instanceof SocketException) {
                    LogUtils.eTag(TAG, "WebSocket连接异常!");

                }

                LogUtils.eTag(TAG, t.getMessage(), t);
            }

        });
    }

    private void disconnect() {
        if (mWebSocket == null) {
            return;
        }

        mWebSocket.close(1010, "关闭WebSocket连接");
    }
}
