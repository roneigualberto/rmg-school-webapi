package com.gualberto.ronei.rmgschoolapi.infra.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Credential {

    private String username;

    private String password;

}
