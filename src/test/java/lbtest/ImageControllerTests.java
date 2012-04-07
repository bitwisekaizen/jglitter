package lbtest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public class ImageControllerTests extends AbstractTestNGSpringContextTests {
    @Test
    void canUploadImageToDatabase() {
        fail("fail");
    }
}
