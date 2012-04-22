package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ImageControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private RestTemplate template;
    private static final String ROOT_URL = "http://localhost:8080/harvard";
    //private static final String ROOT_URL = "http://jsidlab-stage.cloudfoundry.com";

    @Test
    void canUploadImage() throws IOException {
        Images images = getAllImages();
        Assert.assertNotNull(images);
        int originalCount = images.getImages().size();

        ClassPathResource testResource = new ClassPathResource("test-image.jpg");
        File testImage = testResource.getFile();
        Image uploadedImage = uploadImage(testResource);
        Assert.assertNotNull(uploadedImage);

        images = getAllImages();
        Assert.assertEquals(images.getImages().size(), originalCount + 1);

        byte[] imageContent = getImageContent(uploadedImage.getUuid());
        Assert.assertEquals(imageContent.length, testImage.length());

        deleteImage(uploadedImage);
        images = getAllImages();

        Assert.assertEquals(images.getImages().size(), originalCount);
    }

    private byte[] getImageContent(String uuid) {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("uuid", uuid);
        return template.getForEntity(getImagesContentUrl() + "?uuid={uuid}", byte[].class, requestParams).getBody();
    }

    private String getImagesContentUrl() {
        return getServletUrl() + ImagesController.IMAGES_CONTENT_MAPPING;
    }

    private void deleteImage(Image image) {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("uuid", image.getUuid());
        template.delete(getImagesUrl() + "?uuid={uuid}", requestParams);
    }

    private Image uploadImage(ClassPathResource image) throws IOException {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<String, Object>();
        requestParams.add("file", image);

        return template.postForEntity(getImagesUrl(), requestParams, Image.class).getBody();
    }

    public String getImagesUrl() {
        return getServletUrl() + ImagesController.IMAGES_MAPPING;
    }

    public Images getAllImages() {
        return template.getForEntity(getImagesUrl(), Images.class).getBody();
    }

    public String getServletUrl() {
        return ROOT_URL + "/iat";
    }
}
