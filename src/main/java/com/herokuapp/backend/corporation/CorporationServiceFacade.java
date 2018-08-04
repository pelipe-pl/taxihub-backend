package com.herokuapp.backend.corporation;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CorporationServiceFacade {
    private final CorporationRepository corpRepository;

    public CorporationServiceFacade(CorporationRepository corpRepository) {
        this.corpRepository = corpRepository;
    }

    public CorporationEntity getById(Long id) {
        return corpRepository.getById(id);
    }

    public Long getByEmail(String email) {
        if (corpRepository.existsByEmail(email)) return corpRepository.findByEmail(email).getId();
        else throw new IllegalArgumentException("There is no Corporation with this e-mail.");
    }

    public Boolean existsByEmail(String email) {
        return corpRepository.existsByEmail(email);
    }

    public void setPasswordResetToken(String email, String token) {
        CorporationEntity entity = corpRepository.findByEmail(email);
        entity.setPasswordResetToken(token);
        entity.setPasswordResetTokenActive(true);
        entity.setPasswordResetTokenValidity(LocalDateTime.now().plusHours(24));
        corpRepository.save(entity);
    }

    public String getEmailByPasswordResetToken(String token) {
        CorporationEntity entity = corpRepository.getByPasswordResetToken(token);
        if (entity != null) {
            return entity.getEmail();
        } else return null;
    }

    public void deactivatePasswordResetToken(String token) {
        CorporationEntity entity = corpRepository.getByPasswordResetToken(token);
        if (entity != null) {
            entity.setPasswordResetTokenActive(false);
            corpRepository.save(entity);
        }
    }

    public Boolean passwordResetTokenActive(String token) {
        CorporationEntity entity = corpRepository.getByPasswordResetToken(token);
        if (entity == null
                || entity.getPasswordResetToken() == null
                || entity.getPasswordResetTokenValidity() == null) return false;
        else return entity.getPasswordResetTokenActive()
                && LocalDateTime.now().isBefore(entity.getPasswordResetTokenValidity());
    }
}
