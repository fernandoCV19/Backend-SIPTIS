package backend.siptis.model.repository.userData;

import backend.siptis.model.entity.userData.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
