package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.StarImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {

    private static String STARS_FILE_PATH = "src/main/resources/files/json/stars.json";

    private final StarRepository starRepository;
    private final ConstellationRepository constellationRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public StarServiceImpl(StarRepository starRepository, ConstellationRepository constellationRepository,
                           Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(STARS_FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder sb = new StringBuilder();

        StarImportDto[] starImportDto = gson.fromJson(readStarsFileContent(), StarImportDto[].class);

        for (StarImportDto dto : starImportDto) {
            sb.append(System.lineSeparator());

            Optional<Star> findStarName = starRepository.findByName(dto.getName());

            if (findStarName.isPresent() || !validationUtil.isValid(dto)){
                sb.append("Invalid star");
            }else {
                Constellation constellation = constellationRepository.findById(dto.getConstellation())
                        .orElse(null);

                Locale.setDefault(new Locale("en", "US"));

                Star map = modelMapper.map(dto, Star.class);
                map.setConstellation(constellation);
                sb.append(String.format("Successfully imported star %s - %.2f light years",
                        dto.getName(), dto.getLightYears()));

                starRepository.save(map);
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String exportStars() {
        StringBuilder sb = new StringBuilder();

        List<Star> allStarNotObserved =
                starRepository.findAllByStarTypeAndObserversIsNullOrderByLightYearsAsc(StarType.RED_GIANT);

        allStarNotObserved.forEach(star -> sb.append(String.format("" +
                "Star: %s\n" +
                "   *Distance: %.2f light years\n" +
                "   **Description: %s\n" +
                "   ***Constellation: %s\n"
        ,star.getName(),star.getLightYears(), star.getDescription(),star.getConstellation().getName() )));

        return sb.toString().trim();
    }
}
