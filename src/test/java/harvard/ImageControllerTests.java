package harvard;

import harvard.marshallable.Image;
import harvard.marshallable.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
public class ImageControllerTests extends AbstractTests {

    @Autowired
    private RestTemplate template;

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
        return template.getForEntity(urls.getImagesContentUrl() + "?uuid={uuid}", byte[].class, requestParams).getBody();
    }


    private void deleteImage(Image image) {
        Map<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("uuid", image.getUuid());
        template.postForEntity(urls.getDeleteImagesUrl() + "?uuid={uuid}", null, Image.class, requestParams);
    }

    private Image uploadImage(ClassPathResource image) throws IOException {
        MultiValueMap<String, Object> requestParams = new LinkedMultiValueMap<String, Object>();
        requestParams.add("file", image);

        return template.postForEntity(urls.getImagesUrl(), requestParams, Image.class).getBody();
    }


    public Images getAllImages() {
        return template.getForEntity(urls.getImagesUrl(), Images.class).getBody();
    }

}
