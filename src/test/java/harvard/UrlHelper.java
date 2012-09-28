package harvard;

/**
 * Created by IntelliJ IDEA. User: gavin Date: 4/22/12 Time: 6:03 PM To change this template use File | Settings | File
 * Templates.
 */
public class UrlHelper {

    private String ROOT_URL = "http://localhost:8080/jglitter";
    private Views views = new Views();

    public String getServletUrl() {
        return getRootUrl() + "/iat";
    }

    public String wsRoot() {
        return getRootUrl() + "/ws";
    }

    public String getRootUrl() {
        return ROOT_URL;
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

    public String getExperimentsUrl() {
        return getServletUrl() + ExperimentsController.EXPERIMENTS_MAPPING;
    }

    public String getDeleteExperimentsUrl() {
        return getServletUrl() + ExperimentsController.EXPERIMENTS_DELETE_MAPPING;
    }

    public class Views {
        public String images() {
            return getRootUrl() + "/images.html";
        }

        public String experiments() {
            return getRootUrl() + "/experiments.html";
        }
    }
}
