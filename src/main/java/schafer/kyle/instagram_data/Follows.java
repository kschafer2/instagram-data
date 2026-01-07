package schafer.kyle.instagram_data;

import java.util.List;

public class Follows {
    private List<UserProfile> followers;
    private List<UserProfile> following;

    public List<UserProfile> getFollowers() {
        return followers;
    }

    public List<UserProfile> getFollowing() {
        return following;
    }

    public void setFollowers(List<UserProfile> followers) {
        this.followers = followers;
    }

    public void setFollowing(List<UserProfile> following) {
        this.following = following;
    }
}
