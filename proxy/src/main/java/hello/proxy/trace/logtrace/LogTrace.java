package hello.proxy.trace.logtrace;

import hello.proxy.trace.TraceStatus;

public interface LogTrace {
    TraceStatus begin(String message);

    void end(TraceStatus traceStatus);
    void exception(TraceStatus traceStatus, Exception e);

}