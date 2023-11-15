package com.example.mobilele.services.impl;

import com.example.mobilele.model.DTO.BrandDTO;
import com.example.mobilele.model.DTO.ModelDTO;
import com.example.mobilele.model.entity.Brand;
import com.example.mobilele.model.entity.Model;
import com.example.mobilele.repository.BrandRepository;
import com.example.mobilele.repository.ModelRepository;
import com.example.mobilele.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDTO> getAllBrand() {

        return brandRepository.getAllBrands().stream()
                .map(brand -> new BrandDTO(
                        brand.getName(),
                        brand.getModels().stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::name))
                                .collect(Collectors.toList())
                ))
                .sorted(Comparator.comparing(BrandDTO::name))
                .collect(Collectors.toList());
    }
}
