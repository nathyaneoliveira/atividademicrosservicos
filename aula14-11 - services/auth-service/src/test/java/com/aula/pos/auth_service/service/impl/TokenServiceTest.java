package com.aula.pos.auth_service.service.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TokenServiceTest {
    @Test
    void rejectsJwtSecretsShorterThan32Bytes() {
        assertThrows(IllegalArgumentException.class, () -> new TokenService("too-short"));
    }
}
