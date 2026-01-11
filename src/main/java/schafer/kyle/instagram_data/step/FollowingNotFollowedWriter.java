package schafer.kyle.instagram_data.step;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.json.JsonFileItemWriter;
import org.springframework.batch.infrastructure.item.json.JsonObjectMarshaller;
import org.springframework.core.io.WritableResource;
import schafer.kyle.instagram_data.model.UserProfile;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public class FollowingNotFollowedWriter extends JsonFileItemWriter<HashSet<UserProfile>> {
    /**
     * Create a new {@link JsonFileItemWriter} instance.
     *
     * @param resource             to write json data to
     * @param jsonObjectMarshaller used to marshal object into json representation
     */
    public FollowingNotFollowedWriter(WritableResource resource, JsonObjectMarshaller<HashSet<UserProfile>> jsonObjectMarshaller) {
        super(resource, jsonObjectMarshaller);
    }

    @Override
    public String doWrite(Chunk<? extends HashSet<UserProfile>> items) {
        Optional<? extends HashSet<UserProfile>> max = items.getItems()
                .stream()
                .max(Comparator.comparing(HashSet::size));
        if(max.isPresent()) {
            return super.doWrite(new Chunk<>(max.get()));
        } else {
            throw new RuntimeException();
        }
    }
}
