package ru.goryachev.orderservice.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.goryachev.orderservice.app.WebAppInit;
import ru.goryachev.orderservice.service.OrderService;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * CRUD API-layer testing using mocks.
 * Each method in different object (Lifecycle.PER_METHOD)
 * @author Lev Goryachev
 * @version 1.0
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = WebAppInit.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @MockBean
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        /*mockMvc = MockMvcBuilders
                .standaloneSetup(orderController)
                //.addFilters(smallCORSFilter)
                //.addFilters(new CorsFilter())
                .build();*/
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void createAddressTest() throws Exception{
        ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
        order.setId(1L);
        order.setCustomerName("testValueAbc");
        order.setAddress("testValueXyz");
        order.setSumm(BigDecimal.valueOf(12345,67));

        String jsonOrder = asJsonString(order);

        when(orderService.save(order)).thenReturn(order);

        ResultActions mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOrder))
                //.andExpect(MockMvcResultMatchers.model().attributeExists("lineOne"))
                .andExpect(MockMvcResultMatchers.content().json(jsonOrder));
                //.andExpect(status().isCreated());

        String s = mvcResult.andReturn().getResponse().getContentAsString();
    }

    @Test
    @Order(2)
    public void getAddressTest() throws Exception {
        ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
        order.setId(2L);
        order.setCustomerName("testValueAbc");
        order.setAddress("testValueXyz");
        order.setSumm(BigDecimal.valueOf(12345,67));

        String jsonOrder = asJsonString(order);

        when(orderService.getById(2L)).thenReturn(order);

        ResultActions mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/orders/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOrder))
                .andExpect(MockMvcResultMatchers.content().json(jsonOrder));
    }

    @Test
    @Order(3)
    public void getAllAddressesTest() throws Exception {
        List<ru.goryachev.orderservice.model.Order> orders = new ArrayList<>();
        for (Integer i = 1; i < 5; i++){
            ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
            order.setId(i.longValue());
            order.setCustomerName("testValueAbc" + i);
            order.setAddress("testValueXyz" + i);
            order.setSumm(BigDecimal.valueOf(12345,67));
            orders.add(order);
        }

        String jsonOrderes = asJsonString(orders);

        when(orderService.getAll()).thenReturn(orders);

        ResultActions mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOrderes))
                .andExpect(MockMvcResultMatchers.content().json(jsonOrderes));
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateAddressTest() throws Exception{
        ru.goryachev.orderservice.model.Order order = new ru.goryachev.orderservice.model.Order();
        order.setId(1L);
        order.setCustomerName("testValueAbc");
        order.setAddress("testValueXyz");
        order.setSumm(BigDecimal.valueOf(12345,67));

        String jsonOrder = asJsonString(order);

        when(orderService.save(order)).thenReturn(order);

        ResultActions mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonOrder))
                .andExpect(MockMvcResultMatchers.content().json(jsonOrder));
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteAddressTest() throws Exception {
        Long testId = 2L;

        ResultActions mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/orders/2"));
        verify(orderService).delete(testId);
    }
}