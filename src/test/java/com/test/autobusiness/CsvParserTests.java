package com.test.autobusiness;

import com.test.autobusiness.controllers.DealershipController;
import com.test.autobusiness.services.DealershipService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CsvParserTests {

    @Autowired
    private DealershipService dealershipService;

    @Autowired
    private DealershipController dealershipController;

    @Test
    void fileIdFromProcessState() {


    }
}
