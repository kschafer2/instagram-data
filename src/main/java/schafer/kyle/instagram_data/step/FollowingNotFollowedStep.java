package schafer.kyle.instagram_data.step;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.file.MultiResourceItemReader;
import org.springframework.batch.infrastructure.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.infrastructure.item.json.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import schafer.kyle.instagram_data.model.ResourceAwareInstagramObject;
import schafer.kyle.instagram_data.model.UserProfile;

import java.util.HashSet;

@EnableBatchProcessing
@Configuration
public class FollowingNotFollowedStep {

    private final JobRepository jobRepository;

    public FollowingNotFollowedStep(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Step followingNotFollowed() {
            return new StepBuilder("followingNotFollowed",jobRepository)
                    .<ResourceAwareInstagramObject, HashSet<UserProfile>>chunk(Integer.MAX_VALUE)
                    .reader(jsonItemReader())
                    .processor(new FollowingNotFollowedProcessor())
                    .writer(jsonItemWriter())
                    .build();
    }

    private MultiResourceItemReader<ResourceAwareInstagramObject> jsonItemReader() {
        return new MultiResourceItemReaderBuilder<ResourceAwareInstagramObject>()
                .name("jsonItemReader")
                .resources(new Resource[]{new ClassPathResource("followers.json"), new ClassPathResource("following.json")})
                .delegate(new FollowingNotFollowedReader(new ClassPathResource("followers.json"), new JacksonJsonObjectReader<>(ResourceAwareInstagramObject.class)))
                .build();
    }

    private JsonFileItemWriter<HashSet<UserProfile>> jsonItemWriter() {
        return new FollowingNotFollowedWriter(new FileSystemResource("followingNotFollowed.json"), new JacksonJsonObjectMarshaller<>());
    }
}
