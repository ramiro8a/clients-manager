package com.clients.commons.api.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMsg {
    VALIDATE_REQUEST("ERROR-001", "Error en los datos enviados"),
    INTER_ERROR("ERROR-002", "Error interno");
    final String cod;
    final String msj;
}
