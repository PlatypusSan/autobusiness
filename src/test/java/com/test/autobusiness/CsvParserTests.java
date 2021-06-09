package com.test.autobusiness;

import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.entities.dto.dealership.DealershipResponse;
import com.test.autobusiness.entities.mappers.DealershipMapper;
import com.test.autobusiness.services.DealershipService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class CsvParserTests {

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private DealershipMapper dealershipMapper;

    @Test
    void saveToCsv() throws Exception {

        /*JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());*/
    }

    @Test
    void getAllDealerships() {

        List<Dealership> dealershipList = dealershipService.getAllDealerships();
    }

    @Test
    void testRelations() {

        Dealership dealership = dealershipService.getDealership(7);
        DealershipResponse dealershipResponse = dealershipMapper.dealershipToDealershipResponse(dealership);
    }
}
