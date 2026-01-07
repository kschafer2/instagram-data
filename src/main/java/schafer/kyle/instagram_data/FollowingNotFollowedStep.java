package schafer.kyle.instagram_data;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.json.*;
import org.springframework.batch.infrastructure.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.infrastructure.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class FollowingNotFollowedStep {

    private final JobRepository jobRepository;

    public FollowingNotFollowedStep(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    private Step followingNotFollowedStep() {
        return new StepBuilder(jobRepository)
                .<Follows, FollowingNotFollowed>chunk(1)
                .reader(jsonItemReader())
                .processor(followingNotFollowedProcessor())
                .writer(jsonItemWriter())
                .build();
    }

    private JsonItemReader<Follows> jsonItemReader() {
        return new JsonItemReaderBuilder<Follows>()
                .jsonObjectReader(new JacksonJsonObjectReader<>(Follows.class))
                .resource(new ClassPathResource("followers.json"))
                .build();
    }

    private FollowingNotFollowedProcessor followingNotFollowedProcessor() {
        return new FollowingNotFollowedProcessor();
    }

    private JsonFileItemWriter<FollowingNotFollowed> jsonItemWriter() {
        return new JsonFileItemWriterBuilder<FollowingNotFollowed>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("followingNotFollowed.json"))
                .build();
    }
}
