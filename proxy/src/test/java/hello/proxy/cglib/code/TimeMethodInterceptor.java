package hello.proxy.cglib.code;


import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

@Slf4j
public class TimeMethodInterceptor implements MethodInterceptor {

    private final Object object;

    public TimeMethodInterceptor(Object object) {
        this.object = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = method.invoke(object, objects);

        long endTime = System.currentTimeMillis();
        long resultTIme = endTime - startTime;

        log.info("TimeProxy 종료 resultTime = {}", resultTIme);
        return result;
    }
}
