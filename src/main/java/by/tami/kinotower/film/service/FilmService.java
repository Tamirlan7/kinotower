package by.tami.kinotower.film.service;

import by.tami.kinotower.file.model.File;
import by.tami.kinotower.file.service.FileService;
import by.tami.kinotower.film.dto.FilmRequestDto;
import by.tami.kinotower.film.dto.FilmDto;
import by.tami.kinotower.film.dto.GetFilmsParams;
import by.tami.kinotower.film.dto.GetFilmsResponse;
import by.tami.kinotower.film.mapper.FilmMapper;
import by.tami.kinotower.film.model.Film;
import by.tami.kinotower.film.repository.FilmRepository;
import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genrefilm.service.GenreFilmService;
import by.tami.kinotower.web.exception.BadRequestException;
import by.tami.kinotower.web.util.CursorUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;
    private final FileService fileService;
    private final GenreFilmService genreFilmService;

    public GetFilmsResponse getAllFilms(GetFilmsParams params) {
        Long cursorId = CursorUtils.decodeCursor(params.getCursor());
        if (cursorId == null) cursorId = 0L;
        List<Film> films = filmRepository.findAllFiltered(
                params.getName(),
                cursorId,
                params.getSize() + 1
        );

        boolean hasNext = params.getSize() < films.size();

        if (hasNext) {
            films = films.subList(0, params.getSize());
        }

        var response = new GetFilmsResponse();
        response.setData(films.stream().map(FilmMapper::toDto).toList());

        if (hasNext && !films.isEmpty()) {
            Long lastId = films.getLast().getId();
            var nextCursor = CursorUtils.encodeCursor(lastId);
            response.setNextCursor(nextCursor);
            response.setHasNext(true);
        }

        return response;
    }

    public Optional<Film> findFilmByName(String name) {
        return filmRepository.findByName(name);
    }

    @Transactional
    public FilmDto createFilm(FilmRequestDto body) {
        if (findFilmByName(body.getName()).isPresent()) {
            throw new BadRequestException("Фильм с таким названием уже существует");
        }

        Film film = new Film();
        film.setName(body.getName());
        film.setDescription(body.getDescription());
        film.setLanguage(body.getLanguage());
        film.setAgeRating(body.getAgeRating());
        film.setBasePrice(body.getBasePrice());
        film.setDurationMin(body.getDurationMin());

        if (!Objects.requireNonNull(body.getPreviewImage().getContentType()).startsWith("image")) {
            throw new BadRequestException("Content-Type of preview image should be an image");
        }

        if (!Objects.requireNonNull(body.getFilmVideo().getContentType()).startsWith("video")) {
            throw new BadRequestException("Content-Type of film video should be a video");
        }

        File imageFile = fileService.saveFile(body.getPreviewImage());
        File videoFile = fileService.saveFile(body.getFilmVideo());

        film.setPreviewImage(imageFile);
        film.setFilmVideo(videoFile);

        film = filmRepository.save(film);
        Set<GenreDto> savedGenres = genreFilmService.saveGenresForFilm(film, body.getGenreIds());

        return FilmMapper.toDto(film, savedGenres);
    }
}
