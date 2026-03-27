package by.tami.kinotower.hall.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetHallsParams {
    private String name = "";
    private Boolean is3d = null;
    private int page = 0;
    private int size = 10;
}
