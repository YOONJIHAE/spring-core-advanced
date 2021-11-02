package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

    private final OrderServiceV2 orderServiceV2;
    private final HelloTraceV2 helloTraceV1;

    @GetMapping("/v2/request")
    public String request(String itemId) {

        TraceStatus status = null;

        try {
            status = helloTraceV1.begin("OrderControllerV2.request()");
            orderServiceV2.orderItem(status.getTraceId(), itemId);
            helloTraceV1.end(status);

            return "ok";

        } catch (Exception e) {
            helloTraceV1.exception(status, e);
            throw e;
        }

    }
}
