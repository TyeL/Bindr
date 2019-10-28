package com.productivity.bytehalf.bindr;

import com.productivity.bytehalf.bindr.service.BindrService;
import com.productivity.bytehalf.bindr.service.NoteService;
import com.productivity.bytehalf.bindr.service.SnippetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableJpaAuditing
@EntityScan("com.productivity.bytehalf.bindr")
@PropertySource({"application.properties", "jaxb.properties", "META-INF/additional" +
        "-spring-configuration-metadata.json"})
@EnableAsync
public class BindrApplication {

    private Logger logger = LoggerFactory.getLogger(BindrApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BindrApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(BindrService bindrService,
                                 SnippetService snippetService, NoteService noteService) {
        return (String[] args) -> {

        };
    }

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("Bindr");
        executor.initialize();
        return executor;
    }
}
