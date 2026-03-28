package by.tami.kinotower.screening.service;

import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.film.service.FilmService;
import by.tami.kinotower.hall.model.Hall;
import by.tami.kinotower.hall.service.HallService;
import by.tami.kinotower.screening.dto.CreateScreeningRequest;
import by.tami.kinotower.screening.dto.ScreeningDto;
import by.tami.kinotower.screening.repository.ScreeningRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScreeningService {

    private final ScreeningRepository screeningRepository;
    private final FilmService filmService;
    private final HallService hallService;


    public ScreeningDto createScreening(@Valid CreateScreeningRequest request) {
        Film film = filmService.getFilmById(request.getFilmId());
        Hall hall = hallService.getHallById(request.getHallId());

    }
}
