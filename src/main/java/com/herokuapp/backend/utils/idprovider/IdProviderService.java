package com.herokuapp.backend.utils.idprovider;

import com.herokuapp.backend.auth.Role;
import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.corporation.CorporationServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import org.springframework.stereotype.Service;

@Service
public class IdProviderService {

    private final CorporationServiceFacade corporationServiceFacade;
    private final ClientServiceFacade clientServiceFacade;
    private final DriverServiceFacade driverServiceFacade;

    public IdProviderService(CorporationServiceFacade corporationServiceFacade, ClientServiceFacade clientServiceFacade, DriverServiceFacade driverServiceFacade) {
        this.corporationServiceFacade = corporationServiceFacade;
        this.clientServiceFacade = clientServiceFacade;
        this.driverServiceFacade = driverServiceFacade;
    }

    Long getIdByEmailAndRole(String email, Role role) {
        if (role == Role.CORPORATION) return corporationServiceFacade.getByEmail(email);
        if (role == Role.CLIENT) return clientServiceFacade.getIdByEmail(email);
        if (role == Role.DRIVER) return driverServiceFacade.getIdByEmail(email);
        else throw new IllegalArgumentException("Provided Role is unknown.");
    }
}
