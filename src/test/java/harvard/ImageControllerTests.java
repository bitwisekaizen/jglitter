package harvard;

import harvard.marshallable.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ImageControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private RestTemplate template;

    @Test
    void canUploadImage() {
        Images images = template.getForEntity("http://localhost:8080/harvard/iat/images", Images.class).getBody();
        Assert.assertNotNull(images);
        Assert.assertEquals(images.getImages().size(), 0);
    }
}
