package com.discoveryLog.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import com.discoveryLog.service.Log;



public class LogClient { 
/** 
    * 调用 Log 服务
    * @param args 
    */ 
   public static void main(String[] args) { 
       try { 
           // 设置调用的服务地址为本地，端口为 10005  
           TTransport transport = new TFramedTransport(new TSocket("localhost", 10005)); 
           
           transport.open(); 
           
           // 设置传输协议为 TCompactProtocol 
           TCompactProtocol protocol = new TCompactProtocol(transport);
           Log.Client client = new Log.Client(protocol); 
           
           // 调用服务的 log 方法
           long currentTime = System.currentTimeMillis();
           client.log("/mcs/access/", currentTime + "|accesstest" ); 
           transport.close(); 
       } catch (TTransportException e) { 
           e.printStackTrace(); 
       } catch (TException e) { 
           e.printStackTrace(); 
       } 
   } 
} 
