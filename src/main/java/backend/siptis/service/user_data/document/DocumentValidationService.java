package backend.siptis.service.user_data.document;

import backend.siptis.commons.ServiceAnswer;
import backend.siptis.model.pjo.dto.document.LetterGenerationRequestDTO;

public interface DocumentValidationService {

    ServiceAnswer studentLetterValidation(LetterGenerationRequestDTO dto);
    ServiceAnswer tutorLetterValidation(LetterGenerationRequestDTO dto);
    ServiceAnswer supervisorLetterValidation(LetterGenerationRequestDTO dto);
    ServiceAnswer teacherLetterValidation(LetterGenerationRequestDTO dto);
    ServiceAnswer tribunalLetterValidation(LetterGenerationRequestDTO dto);
    ServiceAnswer defenseActValidation(Long ProjectId);
}
