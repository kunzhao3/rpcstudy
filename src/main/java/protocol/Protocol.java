package protocol;

import framework.Invocation;
import framework.Url;

public interface Protocol {
    void start(Url url);
    String send(Url url, Invocation invocation);
}
