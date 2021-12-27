package hello.proxy.jdkdynamic;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello hello = new Hello();

        log.info("start");
        String result1 = hello.callA();
        log.info("result={}", result1);

        log.info("start");
        String result2 = hello.callB();
        log.info("result={}", result2);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }

    @Test
    void reflection1() throws Exception {

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello hello = new Hello();

        Method method = classHello.getMethod("callA");
        Object result1 = method.invoke(hello);
        log.info("result = {}", result1);

        Method method1 = classHello.getMethod("callB");
        Object result2= method.invoke(hello);
        log.info("reuslt = {}", result2);

    }

    @Test
    void reflection2() throws Exception {

        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello hello = new Hello();

        Method method = classHello.getMethod("callA");
        dynamicCall(method, hello);

        Method method1 = classHello.getMethod("callB");
        dynamicCall(method1, hello);

    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("res ult= {}", result);
    }
}
