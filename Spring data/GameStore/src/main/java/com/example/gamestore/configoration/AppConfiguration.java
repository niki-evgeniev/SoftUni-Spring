package com.example.gamestore.configoration;

import com.example.gamestore.model.dto.GameAddGameDto;
import com.example.gamestore.model.entity.Game;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(GameAddGameDto.class, Game.class)
                .addMappings(mapper ->
                        mapper.map(GameAddGameDto::getThubnailURL,
                                Game::setImageThumbnail));

        return modelMapper;
    }
}
