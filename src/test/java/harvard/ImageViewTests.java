package harvard;

import harvard.pages.ImagesPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class ImageViewTests extends AbstractViewTests {

    @Autowired
    private ImagesPage imagesPage;

    @Test
    void canNavigateToImagesPage() {
        imagesPage.go();
        assertEquals(imagesPage.getTitle(), "Images");
    }
}
