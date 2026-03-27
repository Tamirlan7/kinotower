package by.tami.kinotower.hall.service;

import by.tami.kinotower.hall.dto.CreateHallRequest;
import by.tami.kinotower.hall.dto.GetHallsParams;
import by.tami.kinotower.hall.dto.GetHallsResponse;
import by.tami.kinotower.hall.dto.HallDto;
import by.tami.kinotower.hall.mapper.HallMapper;
import by.tami.kinotower.hall.model.Hall;
import by.tami.kinotower.hall.repository.HallRepository;
import by.tami.kinotower.web.exception.BadRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HallService {

    private final HallRepository hallRepository;

    public HallDto createHall(@Valid CreateHallRequest request) {
        if (hallRepository.existsByName(request.getName())) {
            throw new BadRequestException("Hall with name " + request.getName() + " already exists");
        }

        Hall hall = new Hall();
        hall.setName(request.getName());
        hall.setRowsCount(request.getRowsCount());
        hall.setSeatsPerRow(request.getSeatsPerRow());
        hall.set3d(request.is3d());
        hall = hallRepository.save(hall);
        return HallMapper.toDto(hall);
    }

    public GetHallsResponse getHalls(GetHallsParams params) {
        Page<Hall> halls = hallRepository.findAllByNameAnd3d(
                params.getName(),
                params.getIs3d(),
                Pageable.ofSize(params.getPageSize()).withPage(params.getPage())
        );

        GetHallsResponse response = new GetHallsResponse();
        response.setData(halls.getContent().stream().map(HallMapper::toDto).toList());
        response.setTotalItems(halls.getTotalElements());
        response.setTotalPages(halls.getTotalPages());
        return response;
    }
}
