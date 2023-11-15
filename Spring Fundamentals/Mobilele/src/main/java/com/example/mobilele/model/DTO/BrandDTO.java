package com.example.mobilele.model.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record BrandDTO(
        String name,

        List<ModelDTO> models) {
}
