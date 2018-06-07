package com.herokuapp.backend.utils.idprovider;

import com.herokuapp.backend.auth.Role;
import com.herokuapp.backend.client.ClientServiceFacade;
import com.herokuapp.backend.corporation.CorporationServiceFacade;
import com.herokuapp.backend.driver.DriverServiceFacade;
import javassist.NotFoundException;
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

    Long getIdByEmailAndRole(String email, String role) throws NotFoundException {
        if (role.toUpperCase().equals(Role.CORPORATION.toString())) return corporationServiceFacade.getByEmail(email);
        if (role.toUpperCase().equals(Role.CLIENT.toString())) return clientServiceFacade.getIdByEmail(email);
        if (role.toUpperCase().equals(Role.DRIVER.toString())) return driverServiceFacade.getIdByEmail(email);
        else throw new IllegalArgumentException("Provided Role is unknown.");
    }
}
