package com.evaluacion.java.registroDeUsuariosApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {
    private String number;
    private String citycode;
    private String contrycode;
}
