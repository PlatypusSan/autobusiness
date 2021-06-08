package com.test.autobusiness.config;

import com.test.autobusiness.entities.Dealership;
import com.test.autobusiness.util.DealershipProcessor;
import com.test.autobusiness.util.DealershipReader;
import com.test.autobusiness.util.DealershipWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


//@Configuration
//@EnableBatchProcessing
public class DealershipCsvConfig {

    @Autowired//TODO: remove FI
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    public ItemReader<Dealership> itemReader() {
        return new DealershipReader();
    }

    @Bean
    public ItemProcessor<Dealership, Dealership> itemProcessor() {
        return new DealershipProcessor();
    }

    @Bean
    public ItemWriter<Dealership> itemWriter() {
        return new DealershipWriter();
    }

    @Bean
    protected Step processLines(ItemReader<Dealership> reader,
                                ItemProcessor<Dealership, Dealership> processor, ItemWriter<Dealership> writer) {
        return steps.get("processLines").<Dealership, Dealership>chunk(2)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job job() {
        return jobs
                .get("chunksJob")
                .start(processLines(itemReader(), itemProcessor(), itemWriter()))
                .build();
    }

    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils() {
        return new JobLauncherTestUtils();
    }
}
