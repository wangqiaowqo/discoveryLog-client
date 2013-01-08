package com.discoveryLog.client;

import java.io.IOException;

import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import com.discoveryLog.client.callback.LogCallback;
import com.discoveryLog.service.Log;

public class LogAsynClient { 
    /** 
     * 异步调用 Log 服务
     * @param args 
     */ 
    public static void main(String[] args) throws Exception { 
        try { 
            TAsyncClientManager clientManager = new TAsyncClientManager(); 
            TNonblockingTransport transport = new TNonblockingSocket("localhost", 10005); 
            TCompactProtocol protocol = new TCompactProtocol(transport);
            TProtocolFactory protocolFactory =new TCompactProtocol.Factory();
            Log.AsyncClient asyncClient = new Log.AsyncClient(protocolFactory, clientManager, transport); 
            System.out.println("Client calls .....   "); 
            LogCallback callBack = new LogCallback();
            long currentTime = System.currentTimeMillis();
            asyncClient.log("/mcs/access/", currentTime + "|mcsaccesstestasyn", callBack);
           
            Object res = callBack.getResult(); 
            while (res == null) { 
                res = callBack.getResult();
                System.out.println("result="+res);
            } 
            System.out.println( currentTime + " result="+((Log.AsyncClient.log_call) res).getResult());

            
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
  } 
 } 