package harvard.marshallable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/7/12 Time: 10:27 PM To change this template use File | Settings | File
 * Templates.
 */
@XmlRootElement
public class ImageContent {

    private byte[] content;

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
