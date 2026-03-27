package by.tami.kinotower.hall.controller;

import by.tami.kinotower.hall.dto.CreateHallRequest;
import by.tami.kinotower.hall.dto.GetHallsParams;
import by.tami.kinotower.hall.dto.GetHallsResponse;
import by.tami.kinotower.hall.dto.HallDto;
import by.tami.kinotower.hall.service.HallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/halls")
@RequiredArgsConstructor
public class HallController {

    private final HallService hallService;

    @PostMapping
    public ResponseEntity<HallDto> createHall(
            @RequestBody @Valid CreateHallRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hallService.createHall(request));
    }

    @GetMapping
    public ResponseEntity<GetHallsResponse> getAllHalls(
            @ModelAttribute GetHallsParams params
    ) {
        return ResponseEntity.ok(hallService.getHalls(params));
    }

}
