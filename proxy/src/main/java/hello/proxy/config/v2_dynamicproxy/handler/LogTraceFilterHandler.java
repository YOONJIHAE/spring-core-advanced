package hello.proxy.config.v2_dynamicproxy.handler;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.springframework.util.PatternMatchUtils;

public class LogTraceFilterHandler implements InvocationHandler {

    private final Object object;
    private final LogTrace logTrace;
    private final String[] patterns;

    public LogTraceFilterHandler(Object object, LogTrace logTrace, String[] patterns) {
        this.object = object;
        this.logTrace = logTrace;
        this.patterns = patterns;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(patterns, methodName)) {
            return method.invoke(object, args);
        }

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
