package hello.proxy.jdkdynamic.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object object;

    public TimeInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TImeProxy 실행");

        long startTime = System.currentTimeMillis();

        Object result = method.invoke(object, args);

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;

        log.info("TimeProxy 종료 resultTime = {}", resultTIme);
        return result;
    }
}
