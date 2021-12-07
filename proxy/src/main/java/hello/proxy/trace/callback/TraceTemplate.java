package hello.proxy.trace.callback;

import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceTemplate {

    private final LogTrace logTrace;

    public <T> T execute(String message, TraceCallback<T> callback) {

        TraceStatus status = null;

        try {
            status = logTrace.begin(message);

            T result = callback.call();
            logTrace.end(status);

            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }

}
