package com.personal.kakaopj.websocket;

//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableWebSocket
//public class WebSockConfig implements WebSocketConfigurer {
//    private final WebSocketHandler webSocketHandler;
//
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        // ws/chat 경로를 통해 들어오는 웹 소켓 통신 요청에 대한 처리를 위한 Handler 추가
//        registry.addHandler(webSocketHandler, "ws/chat").setAllowedOrigins("*");
//    }
//}

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Component
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        /*
            2022.10.26[프뚜]:
                Spring에서 Bean은 싱글톤으로 관리되지만,
                @ServerEndpoint 클래스는 WebSocket이 생성될 때마다 인스턴스가 생성되고
                JWA에 의해 관리되기 때문에 Spring의 @Autowired가 설정된 멤버들이 초기화 되지 않습니다.
                연결해주고 초기화해주는 클래스가 필요합니다.

                @ServerEndpoint를 사용하기 위해 bean등록
         */
        return new ServerEndpointExporter();
    }

}