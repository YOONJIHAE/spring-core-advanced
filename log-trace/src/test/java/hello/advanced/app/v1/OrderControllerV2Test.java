package hello.advanced.app.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.util.NestedServletException;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void succ_request() throws Exception {

        String itemId = "hello world";

        ResultActions resultActions = mockMvc.perform(get("/v2/request")
            .param("itemId", itemId)
        ).andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(result -> result.equals("ok"));
    }

    @Test
    void exception_request() {

        String itemId = "ex";

        Assertions.assertThrows(NestedServletException.class, () -> {
            mockMvc.perform(get("/v2/request")
                .param("itemId", itemId)
            ).andDo(print());
        });
    }
}