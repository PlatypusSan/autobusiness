package com.test.autobusiness.util;

import com.test.autobusiness.entities.Dealership;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

public class DealershipProcessor implements ItemProcessor<Dealership, Dealership>, StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    @Override
    public Dealership process(Dealership dealership) throws Exception {
        return dealership;
    }
}
