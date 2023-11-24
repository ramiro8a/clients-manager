package com.clients.domain.provider.account;

import com.clients.domain.provider.account.request.CuentaRequest;
import com.clients.domain.provider.account.response.CuentaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accounts-manager", url = "${account.url.ws}")
public interface AccountProvider {
    @PostMapping("/cuenta")
    CuentaResponse creaCuenta(@RequestBody CuentaRequest request);
}
