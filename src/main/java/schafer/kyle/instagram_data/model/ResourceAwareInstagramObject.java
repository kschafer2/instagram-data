package schafer.kyle.instagram_data.model;

import org.springframework.batch.infrastructure.item.ResourceAware;
import org.springframework.core.io.Resource;
import schafer.kyle.instagram_data.instagram.InstagramObject;

public class ResourceAwareInstagramObject extends InstagramObject implements ResourceAware {

    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
