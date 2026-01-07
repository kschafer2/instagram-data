package schafer.kyle.instagram_data;


import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class InstagramDataJob {

    private final JobRepository jobRepository;
    private final Step followingNotFollowedStep;

    public InstagramDataJob(JobRepository jobRepository,
                            Step followingNotFollowedStep) {
        this.jobRepository = jobRepository;
        this.followingNotFollowedStep = followingNotFollowedStep;
    }

    Job instagramDataJob() {
        return new JobBuilder(jobRepository)
                .flow(followingNotFollowedStep)
                .end()
                .build();
    }
}
