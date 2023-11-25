package com.clients.domain.impl;

import com.clients.app.rest.request.ClienteRequest;
import com.clients.commons.exceptions.ProviderException;
import com.clients.domain.provider.account.AccountConnector;
import com.clients.domain.provider.account.AccountProvider;
import com.clients.domain.provider.account.request.CuentaRequest;
import com.clients.domain.provider.account.response.CuentaResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@AllArgsConstructor
@Service
public class AccountConnectorImpl implements AccountConnector {
    private final AccountProvider accounts;

    @CircuitBreaker(name="crearCuenta", fallbackMethod = "creaCuentaError")
    @Override
    public CuentaResponse creaCuenta(CuentaRequest request) {
        return accounts.creaCuenta(request);
    }
    public CuentaResponse creaCuentaError(ClienteRequest request, Throwable throwable){
        if(throwable.getCause() instanceof ConnectException)
            throw new ProviderException("00011", "El servidor de cunetas no esta disponible");

        return null;
    }
}
