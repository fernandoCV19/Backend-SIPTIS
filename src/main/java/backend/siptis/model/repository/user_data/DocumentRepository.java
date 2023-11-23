package backend.siptis.model.repository.user_data;

import backend.siptis.model.entity.user_data.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {

    Optional<Document> findDocumentByPath(String path);
}
