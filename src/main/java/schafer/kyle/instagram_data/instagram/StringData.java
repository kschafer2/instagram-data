package schafer.kyle.instagram_data.instagram;

import java.util.Objects;

/*

"href": "https://www.instagram.com/asdf",
        "value": "asdf",
        "timestamp": 1767040098

*/
public class StringData {
    String href;
    String value;
    Long timestamp;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringData that = (StringData) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
