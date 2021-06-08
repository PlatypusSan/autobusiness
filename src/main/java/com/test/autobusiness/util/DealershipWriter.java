package com.test.autobusiness.util;

import com.test.autobusiness.entities.Dealership;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class DealershipWriter implements ItemWriter<Dealership>, StepExecutionListener {

    private FileUtils fileUtils;

    @Override
    public void write(List<? extends Dealership> list) throws Exception {

        list.forEach(dealership -> {
            fileUtils.writeLine(dealership);
            log.debug("Wrote line " + dealership.toString());
        });
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        fileUtils = new FileUtils("output.csv");
        log.debug("Line Writer initialized.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        fileUtils.closeWriter();
        log.debug("Line Writer ended.");
        return ExitStatus.COMPLETED;
    }
}
