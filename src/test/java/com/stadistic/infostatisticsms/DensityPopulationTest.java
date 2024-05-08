package com.stadistic.infostatisticsms;

import com.stadistic.infostatisticsms.adapter.DensityPopulationController;
import com.stadistic.infostatisticsms.usecase.interfaz.GetDensityPopulation;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(DensityPopulationController.class)
public class DensityPopulationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetDensityPopulation getDensityPopulation;

    @Test
    public void testLowTraffic() throws Exception {
        simulateTraffic(10); // Simula 10 solicitudes por segundo
    }

    @Test
    public void testHighTraffic() throws Exception {
        simulateTraffic(500); // Simula 500 solicitudes por segundo
    }

    private void simulateTraffic(int requestsPerSecond) throws Exception {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 1000; // Ejecuta la simulación durante 1 segundo
        int count = 0;

        while (System.currentTimeMillis() < endTime) {
            mockMvc.perform(get("http://localhost:8080/stats"))
                    .andExpect(status().isOk());
            count++;
            Thread.sleep(1000 / requestsPerSecond);
        }

        System.out.println("Requests sent: " + count + " per second: " + count);
    }
}
