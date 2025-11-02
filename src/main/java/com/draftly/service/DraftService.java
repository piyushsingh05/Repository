package com.draftly.service;

import com.draftly.dto.DraftRequest;
import com.draftly.model.Draft;
import com.draftly.model.DraftStatus;
import com.draftly.repository.DraftRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DraftService {
    private final DraftRepository draftRepository;

    public DraftService(DraftRepository draftRepository) {
        this.draftRepository = draftRepository;
    }

    public Draft createDraft(Long userId, DraftRequest request, String aiText) {
        Draft d = new Draft();
        d.setUserId(userId);
        d.setEmailId(request.getEmailId());
        d.setAiText(aiText);
        d.setFinalText(aiText);
        d.setTone(request.getTone());
        d.setStatus(DraftStatus.GENERATED);
        d.setCreatedAt(Instant.now());
        d.setUpdatedAt(Instant.now());
        return draftRepository.save(d);
    }

    public List<Draft> listForUser(Long userId) {
        return draftRepository.findByUserId(userId);
    }

    public Optional<Draft> findById(Long id) {
        return draftRepository.findById(id);
    }

    public Draft updateFinalText(Draft draft, String newText, DraftStatus status) {
        draft.setFinalText(newText);
        draft.setStatus(status);
        draft.setUpdatedAt(Instant.now());
        return draftRepository.save(draft);
    }
}
