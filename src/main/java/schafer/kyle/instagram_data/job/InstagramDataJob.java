package schafer.kyle.instagram_data.job;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class InstagramDataJob {

    private final JobRepository jobRepository;
    private final Step followingNotFollowed;

    public InstagramDataJob(JobRepository jobRepository, Step followingNotFollowed) {
        this.jobRepository = jobRepository;
        this.followingNotFollowed = followingNotFollowed;
    }

    @Bean
    public Job instagramData() {
        return new JobBuilder(jobRepository)
                .flow(followingNotFollowed)
                .end()
                .build();
    }
}
