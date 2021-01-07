package br.com.dcc.api.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExceptionNotFound extends RuntimeException {
    private final String message;
}
