package lbtest;

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
        Assert.assertNotNull(template);
        String google = template.getForEntity("http://vmware.com/mobile", String.class).getBody();
        Assert.assertTrue(google.toLowerCase().contains("horizon"));
    }
}
