package harvard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(value = "classpath:test-context.xml")
public abstract class AbstractTests extends AbstractTestNGSpringContextTests {
    @Autowired
    protected UrlHelper urls;
}
