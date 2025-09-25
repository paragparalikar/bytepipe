package com.bytepype.web.connection.dto;

import com.bytepype.connection.ConnectionType;

public record ConnectionDTO(Long id, String name, String description, ConnectionType type) {
}
