package org.acme.application;

import io.soabase.recordbuilder.core.RecordBuilder;

@RecordBuilder
public record UserDto(String id, String username, String email, String fullName) {
}
