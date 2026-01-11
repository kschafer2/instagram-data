package schafer.kyle.instagram_data.instagram;

import java.util.List;
import java.util.Objects;

/*

{
    "title": "",
    "media_list_data": [

    ],
    "string_list_data": [
      {
        "href": "https://www.instagram.com/asdf",
        "value": "asdf",
        "timestamp": 1767040098
      }
    ]
  }

 */
public class InstagramObject {
    String title;
    List<MediaData> media_list_data;
    List<StringData> string_list_data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MediaData> getMedia_list_data() {
        return media_list_data;
    }

    public void setMedia_list_data(List<MediaData> media_list_data) {
        this.media_list_data = media_list_data;
    }

    public List<StringData> getString_list_data() {
        return string_list_data;
    }

    public void setString_list_data(List<StringData> string_list_data) {
        this.string_list_data = string_list_data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstagramObject that = (InstagramObject) o;
        if(string_list_data.isEmpty() && that.string_list_data.isEmpty()) {
            return true;
        }
        return Objects.equals(string_list_data.get(0), that.string_list_data.get(0));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string_list_data);
    }
}


