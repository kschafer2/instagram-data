package schafer.kyle.instagram_data.step;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.json.JsonItemReader;
import org.springframework.batch.infrastructure.item.json.JsonObjectReader;
import org.springframework.core.io.Resource;
import schafer.kyle.instagram_data.model.ResourceAwareInstagramObject;

public class FollowingNotFollowedReader extends JsonItemReader<ResourceAwareInstagramObject> {

    private final Resource currentResource;

    /**
     * Create a new {@link JsonItemReader} instance.
     *
     * @param resource         the input json resource
     * @param jsonObjectReader the json object reader to use
     */
    public FollowingNotFollowedReader(Resource resource, JsonObjectReader<ResourceAwareInstagramObject> jsonObjectReader) {
        super(resource, jsonObjectReader);
        this.currentResource = resource;
    }

    @Override
    protected @Nullable ResourceAwareInstagramObject doRead() throws Exception {
        ResourceAwareInstagramObject user = super.doRead();
        if (user != null) {
            user.setResource(this.currentResource);
        }
        return user;
    }
}
