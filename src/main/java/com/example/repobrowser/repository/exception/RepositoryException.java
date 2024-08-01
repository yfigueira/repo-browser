package com.example.repobrowser.repository.exception;

public class RepositoryException extends RuntimeException{

    public RepositoryException(String message) {
        super(message);
    }

    public static UsernameNotFoundException usernameNotFound(String username) {
        return new UsernameNotFoundException("Username [ %s ] not found".formatted(username));
    }

    public static MissingArgumentException missingUsernameArgument() {
        return new MissingArgumentException("Username argument missing");
    }

    public static class UsernameNotFoundException extends RuntimeException {
        public UsernameNotFoundException(String message) {
            super(message);
        }
    }

    public static class MissingArgumentException extends RuntimeException {
        public MissingArgumentException(String message) {
            super(message);
        }
    }
}
