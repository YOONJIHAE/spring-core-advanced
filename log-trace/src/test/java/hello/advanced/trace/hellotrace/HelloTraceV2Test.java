package hello.advanced.trace.hellotrace;

import static org.junit.jupiter.api.Assertions.*;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {

    @Test
    void begin_end_level2() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus status0 = helloTraceV2.begin("hello1");
        TraceStatus status1 = helloTraceV2.beginSync(status0.getTraceId(), "hello2");

        helloTraceV2.end(status1);
        helloTraceV2.end(status0);
    }

    @Test
    void begin_exception_level2() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus status0 = helloTraceV2.begin("hello1");
        TraceStatus status1 = helloTraceV2.beginSync(status0.getTraceId(), "hello2");

        helloTraceV2.exception(status1, new IllegalStateException());
        helloTraceV2.exception(status0, new IllegalStateException());
    }


}