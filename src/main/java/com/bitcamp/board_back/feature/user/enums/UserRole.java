package com.bitcamp.board_back.feature.user.enums;

public enum UserRole {

    ROLE_GUEST(Authority.ROLE_GUEST),
    ROLE_USER(Authority.ROLE_USER),
    ROLE_MANAGER(Authority.ROLE_MANAGER),
    ROLE_ADMIN(Authority.ROLE_ADMIN);

    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    private static class Authority {
        public static final String ROLE_GUEST = "ROLE_GUEST";
        public static final String ROLE_USER = "ROLE_GUEST";
        public static final String ROLE_MANAGER = "ROLE_MANAGER";
        public static final String ROLE_ADMIN = "ROLE_ADMIN";
    }

}
