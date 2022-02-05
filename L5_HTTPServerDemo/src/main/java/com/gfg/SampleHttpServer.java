package com.gfg;

import com.sun.net.httpserver.HttpServer;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class SampleHttpServer {

  private static Logger logger = LoggerFactory.getLogger(SampleHttpServer.class);


  public static void main(String[] args) {
    try {
      HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
      server.createContext("/app", new  MyHttpHandler());
      server.setExecutor(Executors.newFixedThreadPool(10));
      server.start();
      logger.error(" Server started on port 8001");
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

}
