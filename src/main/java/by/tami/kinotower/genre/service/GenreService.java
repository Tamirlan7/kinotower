package by.tami.kinotower.genre.service;

import by.tami.kinotower.genre.dto.GenreDto;
import by.tami.kinotower.genre.dto.GetGenresParams;
import by.tami.kinotower.genre.dto.GetGenresResponse;
import by.tami.kinotower.genre.mapper.GenreMapper;
import by.tami.kinotower.genre.model.Genre;
import by.tami.kinotower.genre.repository.GenreRepository;
import by.tami.kinotower.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> findGenresByIds(Iterable<Long> genreIds) {
        return genreRepository.findAllById(genreIds);
    }

    public GetGenresResponse getGenres(GetGenresParams params) {
        Pageable pageable = Pageable
                .ofSize(params.getSize())
                .withPage(params.getPage());
        Page<Genre> genres = genreRepository.findAllByNameContainingIgnoreCase(params.getName(), pageable);

        var response = new GetGenresResponse();
        response.setData(genres.getContent().stream().map(GenreMapper::toDto).toList());
        response.setTotalItems(genres.getTotalElements());
        response.setTotalPages(genres.getTotalPages());
        return response;
    }

    public GenreDto getGenreById(Long genreId) {
        return GenreMapper.toDto(
                genreRepository
                        .findById(genreId)
                        .orElseThrow(() -> new NotFoundException("Genre with id " + genreId + " not found!"))
        );
    }
}
