package com.herokuapp.backend.auth;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.herokuapp.backend.client.ClientEntity;
import com.herokuapp.backend.client.ClientRepository;
import com.herokuapp.backend.corporation.CorporationEntity;
import com.herokuapp.backend.corporation.CorporationRepository;
import com.herokuapp.backend.driver.DriverEntity;
import com.herokuapp.backend.driver.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class AuthProvider extends AbstractUserDetailsAuthenticationProvider {

    private final DriverRepository driverRepository;
    private final CorporationRepository corporationRepository;
    private final ClientRepository clientRepository;

    public AuthProvider(DriverRepository driverRepository, CorporationRepository corporationRepository, ClientRepository clientRepository) {
        this.driverRepository = driverRepository;
        this.corporationRepository = corporationRepository;
        this.clientRepository = clientRepository;
    }

    @Autowired
    private FirebaseAuth firebaseAuth;

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync((String) authentication.getCredentials());
        try {
            FirebaseToken token = task.get();
            DriverEntity driverEntity = driverRepository.findByEmail(token.getEmail());
            CorporationEntity corporationEntity = corporationRepository.findByEmail(token.getEmail());
            ClientEntity clientEntity = clientRepository.findByEmail(token.getEmail());

            User user = new User(token.getEmail(), null);
            if (driverEntity != null) user.setDriverId(driverEntity.getId());
            if (corporationEntity != null) user.setCorporationId(corporationEntity.getId());
            if (clientEntity != null) user.setClientId(clientEntity.getId());
            return user;
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }
    }
}
