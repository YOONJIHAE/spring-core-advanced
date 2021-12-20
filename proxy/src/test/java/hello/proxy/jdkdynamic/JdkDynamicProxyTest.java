package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvocationHandler;
import java.lang.reflect.Proxy;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA() {
        AInterface aInterface = new AImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(aInterface);
        AInterface aInterface1 = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
            new Class[]{AInterface.class},
            timeInvocationHandler);

        aInterface.call();
        log.info("targetClass = {}", aInterface.getClass());
        log.info("proxyClass = {}", aInterface1.getClass());
    }

    @Test
    void dynamicB() {
        BInterface bInterface = new BImpl();
        TimeInvocationHandler timeInvocationHandler = new TimeInvocationHandler(bInterface);
        BInterface bInterface1 = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(),
            new Class[]{BInterface.class},
            timeInvocationHandler);

        bInterface.call();
        log.info("targetClass = {}", bInterface.getClass());
        log.info("proxyClass = {}", bInterface1.getClass());
    }
}
