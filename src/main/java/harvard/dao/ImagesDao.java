package harvard.dao;

import harvard.persistable.Image;
import harvard.persistable.ImageContent;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static com.thegrayfiles.hqlbuilder.HQLBuilder.hql;
import static com.thegrayfiles.hqlbuilder.HQLConditionFactory.isEqual;

/**
 *
 */
@Component
public class ImagesDao {

    @Autowired
    private SessionFactory factory;

    public List<Image> getAllImages() {
        return hql(factory.getCurrentSession()).from("Image").list(Image.class);
    }

    public Image addImage(byte[] bytes) {
        Session session = factory.getCurrentSession();
        ImageContent content = new ImageContent();
        Image image = new Image();
        // associate relationship first...
        image.setImageContent(content);
        content.setImage(image);
        // ...then set other properties.
        image.setUuid(UUID.randomUUID().toString());
        content.setContent(Hibernate.createBlob(bytes, session));
        session.save(image);
        return image;
    }

    public void deleteImage(String uuid) {
        Session session = factory.getCurrentSession();
        Image image = getImageByUuid(session, uuid);
        session.delete(image);
    }

    public byte[] getImageContent(String uuid) {
        Session session = factory.getCurrentSession();
        Blob blob = getImageByUuid(session, uuid).getImageContent().getContent();
        try {
            return blob.getBytes(1, (int) blob.length());
        } catch (SQLException e) {
            throw new RuntimeException("Unable to read image content from database.", e);
        }
    }

    private Image getImageByUuid(Session session, String uuid) {
        return hql(session).from("Image").where(isEqual(Image.UUID_COLUMN_NAME, uuid)).uniqueResult(Image.class);
    }
}
