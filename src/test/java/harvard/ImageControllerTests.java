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

import java.io.IOException;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ImageControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private RestTemplate template;

    @Test
    void canUploadImage() throws IOException {
        Images images = getAllImages();
        Assert.assertNotNull(images);
        Assert.assertEquals(images.getImages().size(), 0);

        Image uploadedImage = uploadImage();
        Assert.assertNotNull(uploadedImage);

        images = getAllImages();
        Assert.assertEquals(images.getImages().size(), 1);
    }

    private Image uploadImage() throws IOException {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<String, Object>();
        requestParams.add("file", new ClassPathResource("test-image.jpg"));

        return template.postForEntity(getImagesUrl(), requestParams, Image.class).getBody();
    }

    public String getImagesUrl() {
        return "http://localhost:8080/harvard/iat" + ImagesController.IMAGES_MAPPING;
    }

    public Images getAllImages() {
        return template.getForEntity(getImagesUrl(), Images.class).getBody();
    }
}
