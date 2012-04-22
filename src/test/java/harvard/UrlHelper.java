package harvard;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/22/12 Time: 6:03 PM To change this template use File | Settings | File
 * Templates.
 */
public class UrlHelper {

    private String ROOT_URL = "http://localhost:8080/harvard";
    //private static final String ROOT_URL = "http://jsidlab-stage.cloudfoundry.com";
    private Views views = new Views();

    public String getServletUrl() {
        return ROOT_URL + "/iat";
    }

    public String getImagesUrl() {
        return getServletUrl() + ImagesController.IMAGES_MAPPING;
    }

    public String getDeleteImagesUrl() {
        return getServletUrl() + ImagesController.IMAGES_DELETE_MAPPING;
    }

    public String getImagesContentUrl() {
        return getServletUrl() + ImagesController.IMAGES_CONTENT_MAPPING;
    }

    public Views views() {
        return this.views;
    }

    public class Views {
        public String images() {
            return getImagesUrl();
        }
    }
}
