package com.example.bookstore.controllers;

import com.example.bookstore.dto.PublisherDto;
import com.example.bookstore.services.PublisherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @ApiOperation(value = "Save publisher")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Publisher successfully saved")
    })
    @PostMapping("/save")
    public void savePublisher(@RequestBody PublisherDto publisherDto) {
        publisherService.save(publisherDto);
    }

    @ApiOperation(value = "Update publisher")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Publisher successfully updated")
    })
    @PostMapping("/update")
    public void updatePublisher(@RequestBody PublisherDto publisherDto) {
        publisherService.update(publisherDto);
    }

    @ApiOperation(value = "Delete publisher by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Publisher successfully deleted")
    })
    @PostMapping("/delete/{id}")
    public void deletePublisherById(@PathVariable Long id) {
        publisherService.deleteById(id);
    }

    @ApiOperation(value = "Get all publishers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Publishers successfully found")
    })
    @GetMapping("/get_all")
    public List<PublisherDto> getAllPublishers() {
        return publisherService.findAllPublisherDto();
    }

    @ApiOperation(value = "Get publisher by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Publisher successfully found")
    })
    @PostMapping("/get/{id}")
    public PublisherDto getPublisherById(@PathVariable Long id) {
        return publisherService.findPublisherDtoById(id);
    }

}
