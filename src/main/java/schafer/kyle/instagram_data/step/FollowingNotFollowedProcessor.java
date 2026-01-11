package schafer.kyle.instagram_data.step;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import schafer.kyle.instagram_data.exception.InvalidFileNameException;
import schafer.kyle.instagram_data.model.ResourceAwareInstagramObject;
import schafer.kyle.instagram_data.model.UserProfile;

import java.util.*;

public class FollowingNotFollowedProcessor implements ItemProcessor<ResourceAwareInstagramObject, HashSet<UserProfile>> {

    private final HashSet<UserProfile> following = new HashSet<>();
    private final HashSet<UserProfile> followers = new HashSet<>();
    private final HashSet<UserProfile> followingNotFollowed = new HashSet<>();

    @Override
    public @Nullable HashSet<UserProfile> process(ResourceAwareInstagramObject user) {
        if (user.getResource() == null) {
            throw new MissingResourceException("No resource defined", user.getClass().getName(), "");
        }
        UserProfile userProfile = new UserProfile();

        if (Objects.equals(user.getResource().getFilename(), "following.json")) {
            userProfile.setName(user.getTitle());
            following.add(userProfile);
        } else if (Objects.equals(user.getResource().getFilename(), "followers.json")) {
            userProfile.setName(user.getString_list_data().get(0).getValue());
            followers.add(userProfile);
        } else {
            throw new InvalidFileNameException("Invalid file name.");
        }
        followingNotFollowed.addAll(following);
        followingNotFollowed.removeAll(followers);
        return followingNotFollowed;
    }
}
