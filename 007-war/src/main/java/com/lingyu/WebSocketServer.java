package com.lingyu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.yeauty.annotation.ServerEndpoint;

@Component
@Slf4j
@Service
@ServerEndpoint(port = 7272,path = "/")
public class WebSocketServer {

}
