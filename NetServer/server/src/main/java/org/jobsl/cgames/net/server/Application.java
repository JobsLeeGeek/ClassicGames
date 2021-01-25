package org.jobsl.cgames.net.server;

import lombok.extern.slf4j.Slf4j;
import org.jobsl.cgames.net.server.netty.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Value("${netty.port}")
    private int nettyPort;

    public static void main(String[] args) {
        try {
            SpringApplication.run(Application.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("running...");
        new Thread(() -> {new Server(nettyPort).run();}).start();
        log.info("running ok!");
    }
}
