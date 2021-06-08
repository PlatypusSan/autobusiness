package com.test.autobusiness.util;

import com.test.autobusiness.entities.Dealership;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;

public class DealershipReader implements ItemReader<Dealership>, StepExecutionListener {

    @Override
    public Dealership read() {
        return Dealership.builder().city("sdfsadf").name("gooose").build();
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }
}
