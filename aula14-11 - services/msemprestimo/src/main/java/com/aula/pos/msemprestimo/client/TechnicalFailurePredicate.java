package com.aula.pos.msemprestimo.client;

import feign.FeignException;

import java.util.function.Predicate;

public class TechnicalFailurePredicate implements Predicate<Throwable> {

    @Override
    public boolean test(Throwable throwable) {
        Throwable cause = throwable;
        while (cause != null) {
            if (cause instanceof FeignException feignException
                    && feignException.status() >= 400 && feignException.status() < 500) {
                return false;
            }
            cause = cause.getCause();
        }
        return true;
    }
}
