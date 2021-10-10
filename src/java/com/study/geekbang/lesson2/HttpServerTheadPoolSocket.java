package com.study.geekbang.lesson2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author hong.zheng
 * @Date: 9/25/21 4:51 PM
 **/
public class HttpServerTheadPoolSocket {
    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 4);
    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8083);

        while (true){
            try{
                Socket socket = serverSocket.accept();
                threadPoolExecutor.execute(()->service(socket));

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket){
        try{
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello ，你好";

            printWriter.println("Content-Length:"+body.getBytes().length);

            printWriter.println();
            printWriter.write(body);
            printWriter.close();
            socket.close();

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}