package com.example.bookstore.controllers;

import com.example.bookstore.dto.SeriesDto;
import com.example.bookstore.services.SeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/series")
public class SeriesController {

    private final SeriesService seriesService;

    @PostMapping("/save")
    @Operation(
            description = "Save a new series",
            responses = {@ApiResponse(responseCode = "200", description = "Series saved successfully")}
    )
    public void saveSeries(@RequestBody SeriesDto seriesDto) {
        seriesService.save(seriesDto);
    }

    @PostMapping("/update")
    @Operation(
            description = "Update series",
            responses = {@ApiResponse(responseCode = "200", description = "Series updated successfully")}
    )
    public void updateSeries(@RequestBody SeriesDto seriesDto) {
        seriesService.update(seriesDto);
    }

    @PostMapping("/delete/{id}")
    @Operation(
            description = "Delete series by id",
            responses = {@ApiResponse(responseCode = "200", description = "Series deleted successfully")}
    )
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteById(id);
    }

    @GetMapping("/get_by_publisher/{publisherId}")
    @Operation(
            description = "Get series by publisher id",
            responses = {@ApiResponse(responseCode = "200", description = "Series retrieved successfully")}
    )
    public List<SeriesDto> getSeriesByPublisher(@PathVariable Long publisherId) {
        return seriesService.findSeriesByPublisher(publisherId);
    }

    @GetMapping("/get_all")
    @Operation(
            description = "Get list of all series",
            responses = {@ApiResponse(responseCode = "200", description = "Series retrieved successfully")}
    )
    public List<SeriesDto> getAllSeries() {
        return seriesService.findAllSeriesDto();
    }

}
