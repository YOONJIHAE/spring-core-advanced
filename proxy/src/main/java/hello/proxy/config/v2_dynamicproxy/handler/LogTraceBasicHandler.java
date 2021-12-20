package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogTraceBasicHandler implements InvocationHandler {

    private final Object object;
    private final LogTrace logTrace;

    public LogTraceBasicHandler(Object object, LogTrace logTrace) {
        this.object = object;
        this.logTrace = logTrace;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TraceStatus traceStatus = null;

        try {
            String msg = method.getDeclaringClass().getSimpleName() + "." + method.getName() + "()";
            traceStatus = logTrace.begin(msg);

            Object reuslt = method.invoke(object, args);
            logTrace.end(traceStatus);
            return reuslt;

        } catch (Exception e) {
            logTrace.exception(traceStatus, e);
            throw e;
        }
    }
}
