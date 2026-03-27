package by.tami.kinotower.genre.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGenresParams {
    private String name;
    private Integer page;
    private Integer pageSize;
}
