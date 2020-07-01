package framework;

import java.io.Serializable;

public class Url implements Serializable {
    private String hostName;
    private Integer post;

    public Url(String hostName, Integer post) {
        this.hostName = hostName;
        this.post = post;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPost() {
        return post;
    }

    public void setPost(Integer post) {
        this.post = post;
    }
}
