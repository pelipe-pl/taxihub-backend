package com.herokuapp.backend.driver;

import com.herokuapp.backend.auth.DriverConfirm;
import com.herokuapp.backend.auth.FirebaseRegistrationService;
import com.herokuapp.backend.email.EmailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DriverServiceFacade {

    private final DriverRepository driverRepository;
    private final FirebaseRegistrationService firebaseRegistrationService;
    private final EmailService emailService;

    public DriverServiceFacade(DriverRepository driverRepository, FirebaseRegistrationService firebaseRegistrationService, EmailService emailService) {
        this.driverRepository = driverRepository;
        this.firebaseRegistrationService = firebaseRegistrationService;
        this.emailService = emailService;
    }

    public DriverEntity getById(Long id) {
        return driverRepository.getById(id);
    }

    public Boolean existByEmail(String email) {
        return driverRepository.existsByEmail(email);
    }

    public Boolean existsById(Long id) {
        return driverRepository.existsById(id);
    }

    public Long getIdByEmail(String email) {
        if (existByEmail(email)) {
            return driverRepository.findByEmail(email).getId();
        } else throw new IllegalArgumentException("There is no Driver with this e-mail");
    }

    public List<DriverDto> findByCorporation(Long corporationId) {
        return driverRepository.findAllByCorporation_Id(corporationId)
                .stream()
                .map(DriverDto::new)
                .collect(Collectors.toList());
    }

    public void save(DriverEntity driverEntity) {
        driverRepository.save(driverEntity);
    }

    public Boolean confirmAndSetPass(DriverConfirm driverConfirm) throws ExecutionException, InterruptedException {
        if (driverRepository.existsByToken(driverConfirm.getToken())) {
            DriverEntity driverEntity = driverRepository.getByToken(driverConfirm.getToken());
            if (driverEntity.getPasswordSet())
                throw new IllegalArgumentException("You have already set your password.");
            else {
                firebaseRegistrationService.register(driverEntity.getEmail(), driverConfirm.getPassword());
                driverEntity.setPasswordSet(true);
                driverRepository.save(driverEntity);
                emailService.sendWelcomeEmail(driverEntity);
                return true;
            }
        } else return false;
    }

    public void setPasswordResetToken(String email, String token) {
        DriverEntity entity = driverRepository.findByEmail(email);
        if (entity.getPasswordSet()) {
            entity.setPasswordResetToken(token);
            entity.setPasswordResetTokenActive(true);
            entity.setPasswordResetTokenValidity(LocalDateTime.now().plusHours(24));
            driverRepository.save(entity);
        } else
            throw new IllegalArgumentException("This user is not active yet");
    }

    public String getEmailByPasswordResetToken(String token) {
        DriverEntity entity = driverRepository.getByPasswordResetToken(token);
        if (entity != null) {
            return entity.getEmail();
        } else return null;
    }

    public void deactivatePasswordResetToken(String token) {
        DriverEntity entity = driverRepository.getByPasswordResetToken(token);
        if (entity != null) {
            entity.setPasswordResetTokenActive(false);
            driverRepository.save(entity);
        }
    }

    public Boolean passwordResetTokenActive(String token) {
        DriverEntity entity = driverRepository.getByPasswordResetToken(token);
        if (entity == null
                || entity.getPasswordResetToken() == null
                || entity.getPasswordResetTokenValidity() == null) return false;
        else return entity.getPasswordResetTokenActive()
                && LocalDateTime.now().isBefore(entity.getPasswordResetTokenValidity());
    }
}