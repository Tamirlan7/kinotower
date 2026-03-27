package by.tami.kinotower.hall.mapper;

import by.tami.kinotower.hall.dto.HallDto;
import by.tami.kinotower.hall.model.Hall;

public class HallMapper {
    public static HallDto toDto(Hall hall) {
        var dto =  new HallDto();
        dto.setId(hall.getId());
        dto.setName(hall.getName());
        dto.setRowsCount(hall.getRowsCount());
        dto.setSeatsPerRow(hall.getSeatsPerRow());
        dto.setIs3d(hall.is3d());
        return dto;
    }
}
