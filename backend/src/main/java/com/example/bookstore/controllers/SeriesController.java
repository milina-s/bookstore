package com.example.bookstore.controllers;

import com.example.bookstore.dto.SeriesDto;
import com.example.bookstore.services.SeriesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/series")
public class SeriesController {

    private final SeriesService seriesService;

    @ApiOperation(value = "Save series")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Series successfully saved")
    })
    @PostMapping("/save")
    public void saveSeries(@RequestBody SeriesDto seriesDto) {
        seriesService.save(seriesDto);
    }

    @ApiOperation(value = "Update series")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Series successfully updated")
    })
    @PostMapping("/update")
    public void updateSeries(@RequestBody SeriesDto seriesDto) {
        seriesService.update(seriesDto);
    }

    @ApiOperation(value = "Delete series")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Series successfully deleted")
    })
    @PostMapping("/delete/{id}")
    public void deleteSeries(@PathVariable Long id) {
        seriesService.deleteById(id);
    }

    @ApiOperation(value = "Get a list of series by publisher")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Series successfully retrieved")
    })
    @GetMapping("/get_by_publisher/{publisherId}")
    public List<SeriesDto> getSeriesByPublisher(@PathVariable Long publisherId) {
        return seriesService.findSeriesByPublisher(publisherId);
    }

    @ApiOperation(value = "Get a list of all series")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Series successfully retrieved")
    })
    @GetMapping("/get_all")
    public List<SeriesDto> getAllSeries() {
        return seriesService.findAllSeriesDto();
    }

}
