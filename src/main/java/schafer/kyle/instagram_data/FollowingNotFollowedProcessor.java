package schafer.kyle.instagram_data;

import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FollowingNotFollowedProcessor implements ItemProcessor<Follows, FollowingNotFollowed> {


    @Override
    public @Nullable FollowingNotFollowed process(Follows follows) {
        if(follows == null) {
            return null;
        }
        FollowingNotFollowed followingNotFollowed = new FollowingNotFollowed();
        List<UserProfile> followers = follows.getFollowers();
        List<UserProfile> following = follows.getFollowing();

        for(UserProfile follower : followers) {
            following.remove(follower);
        }
        followingNotFollowed.getFollowingNotFollowedUsers().addAll(following);
        return followingNotFollowed;
    }
}
