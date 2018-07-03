package com.herokuapp.backend.corporation;

import com.herokuapp.backend.auth.FirebaseRegistrationService;
import com.herokuapp.backend.car.CarEntity;
import com.herokuapp.backend.car.CarServiceFacade;
import com.herokuapp.backend.driver.DriverDto;
import com.herokuapp.backend.driver.DriverEntity;
import com.herokuapp.backend.driver.DriverServiceFacade;
import com.herokuapp.backend.email.Email;
import com.herokuapp.backend.email.EmailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.herokuapp.backend.config.Keys.FRONT_URL;

@Service
public class CorporationService {

    private static final String CONFIRMATION_CONTENT = "To confirm your email address, please click: \n";
    private final CorporationRepository corpRepository;
    private final EmailService emailService;
    private final FirebaseRegistrationService firebaseRegistrationService;
    private final DriverServiceFacade driverService;
    private final CarServiceFacade carService;
    private Environment environment;

    public CorporationService(CorporationRepository corpRepository, EmailService emailService, FirebaseRegistrationService firebaseRegistrationService, DriverServiceFacade driverService, CarServiceFacade carService, Environment environment) {
        this.corpRepository = corpRepository;
        this.emailService = emailService;
        this.firebaseRegistrationService = firebaseRegistrationService;
        this.driverService = driverService;
        this.carService = carService;
        this.environment = environment;
    }

    public List<DriverDto> findDrivers(Long corporationId) {
        return driverService.findByCorporation(corporationId);
    }

    public void createDriver(DriverDto driver) {

        if (driverService.existByEmail(driver.getEmail())) {
            throw new IllegalArgumentException("The driver with this e-mail already exists.");
        }
        if (!corpRepository.existsById(driver.getCorporationId())) {
            throw new IllegalArgumentException("The corporation with this Id does not exist.");
        }
        if (driver.getCar() != null) {
            if (carService.existsByPlates(driver.getCar().getPlates().toUpperCase())) {
                System.out.println("STEP 4");
                throw new IllegalArgumentException("The plates number already exists or not provided.");
            }
        }
        DriverEntity entity = new DriverEntity();
        entity.setName(driver.getName());
        entity.setSurname(driver.getSurname());
        entity.setEmail(driver.getEmail());
        entity.setCorporation(corpRepository.getById(driver.getCorporationId()));
        entity.setPasswordSet(false);
        entity.setToken(RandomStringUtils.randomAlphabetic(20));
        entity.setSuspended(false);
        driverService.save(entity);
        sendConfirmationEmail(driver.getEmail(), entity.getToken());

        if (driver.getCar() != null) {

            CarEntity carEntity = new CarEntity();
            carEntity.setMake(driver.getCar().getMake());
            carEntity.setModel(driver.getCar().getModel());
            carEntity.setColor(driver.getCar().getColor());
            carEntity.setPlates(driver.getCar().getPlates().toUpperCase());
            carEntity.setDriver(entity);

            carService.save(carEntity);
            entity.setCar(carEntity);
            driverService.save(entity);
        }
    }

    public void changeDriverStatus(DriverDto driver) {
        if (driverService.existsById(driver.getId())) {
            DriverEntity entity = driverService.getById(driver.getId());
            entity.setSuspended(driver.getSuspended());
            driverService.save(entity);
        }
        throw new IllegalArgumentException("You can not enable/suspend a non-existing driver.");
    }

    void resendDriversToken(Long driverId) {
        if (driverId == null || !driverService.existsById(driverId))
            throw new IllegalArgumentException("The driver not provided or not found.");
        if (driverService.getById(driverId).getPasswordSet())
            throw new IllegalArgumentException("This driver already set the password.");
        else {
            DriverEntity driver = driverService.getById(driverId);
            sendConfirmationEmail(driver.getEmail(), driver.getToken());
        }
    }

    private void sendConfirmationEmail(String address, String token) {
        String content = CONFIRMATION_CONTENT + "<a href='" + environment.getRequiredProperty(FRONT_URL) + token + "'>here</a>";
        Email email = new Email(address, "Registration Confirmation", content);
        emailService.send(email);
    }

    CorporationDto createCorporation(CorporationDto corporation) throws
            ExecutionException, InterruptedException {
        if (!corpRepository.existsByEmail(corporation.getEmail())) {
            final CorporationEntity entity = new CorporationEntity();
            entity.setName(corporation.getName());
            entity.setEmail(corporation.getEmail());
            corpRepository.save(entity);
            firebaseRegistrationService.register(corporation.getEmail(), corporation.getPassword());
            return corporation;
        } else throw new IllegalArgumentException("Corporation with this e-mail already exists");
    }

    public void update(CorporationDto corporationDto) {
        if (corpRepository.existsById(corporationDto.getId())) {
            if (corporationDto.getName() == null)
                throw new IllegalArgumentException("The name for corporation is required");
            else {
                CorporationEntity entity = corpRepository.getById(corporationDto.getId());
                entity.setName(corporationDto.getName());
                corpRepository.save(entity);
            }
        } else throw new IllegalArgumentException("There is no corporation with this id");
    }

    public CorporationDto getById(Long id) {
        if (corpRepository.existsById(id)) {
            return toDto(corpRepository.getById(id));
        } else throw new IllegalArgumentException("There is no company with this id");
    }

    public String getName(Long id) {
        if (corpRepository.existsById(id)) {
            return corpRepository.getById(id).getName();
        } else throw new IllegalArgumentException("There is no company with this id");
    }

    private CorporationDto toDto(CorporationEntity corporationEntity) {
        return new CorporationDto(
                corporationEntity.getId(),
                corporationEntity.getName(),
                corporationEntity.getEmail());
    }
}