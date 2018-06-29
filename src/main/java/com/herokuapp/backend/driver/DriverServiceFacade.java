package com.herokuapp.backend.driver;

import com.herokuapp.backend.auth.DriverConfirm;
import com.herokuapp.backend.auth.FirebaseRegistrationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class DriverServiceFacade {

    private final DriverRepository driverRepository;
    private final FirebaseRegistrationService firebaseRegistrationService;

    public DriverServiceFacade(DriverRepository driverRepository, FirebaseRegistrationService firebaseRegistrationService) {
        this.driverRepository = driverRepository;
        this.firebaseRegistrationService = firebaseRegistrationService;
    }

    public DriverEntity getById(Long id) {
        return driverRepository.getById(id);
    }

    public boolean existByEmail(String email) {
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
            firebaseRegistrationService.register(driverEntity.getEmail(), driverConfirm.getPassword());
            driverEntity.setPasswordSet(true);
            driverRepository.save(driverEntity);
            return true;
        } else return false;
    }
}
