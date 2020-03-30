package edu.nju.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ：lycheeshell
 * @Date ：Created in 19:17 2020/2/11
 */
@Configuration
public class SSLConfig {

    @Bean
    public ServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
        return tomcat;
    }

    /**
     * 初始化HTTP连接器
     *
     * 让应用支持HTTP但是需要重定向到HTTPS，
     * 因为不能同时在application.properties中同时配置两个connector两个端口，
     * 所以要以编程的方式配置HTTP connector，然后重定向到HTTPS connector
     * @return Connector
     */

    private Connector initiateHttpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8010); // 监听http8010端口号 (http默认为80端口)
        connector.setSecure(false);
        connector.setRedirectPort(443); // 监听到80端口之后重定向到443端口(https默认为443)
        return connector;
    }

}
