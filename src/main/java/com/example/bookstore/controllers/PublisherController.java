package com.example.bookstore.controllers;

import com.example.bookstore.dto.PublisherDto;
import com.example.bookstore.services.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/bookstore/publishers")
public class PublisherController {

    private final PublisherService publisherService;

    @PostMapping("/save")
    @Operation(
            description = "Save a new publisher",
            responses = {@ApiResponse(responseCode = "200", description = "Publisher saved successfully")}
    )
    public void savePublisher(@RequestBody PublisherDto publisherDto) {
        publisherService.save(publisherDto);
    }

    @PostMapping("/update")
    @Operation(
            description = "Update publisher",
            responses = {@ApiResponse(responseCode = "200", description = "Publisher updated successfully")}
    )
    public void updatePublisher(@RequestBody PublisherDto publisherDto) {
        publisherService.update(publisherDto);
    }

    @PostMapping("/delete/{id}")
    @Operation(
            description = "Delete publisher by id",
            responses = {@ApiResponse(responseCode = "200", description = "Publisher deleted successfully")}
    )
    public void deletePublisherById(@PathVariable Long id) {
        publisherService.deleteById(id);
    }

    @GetMapping("/get_all")
    @Operation(
            description = "Get list of all publishers",
            responses = {@ApiResponse(responseCode = "200", description = "Publishers retrieved successfully")}
    )
    public List<PublisherDto> getAllPublishers() {
        return publisherService.findAllPublisherDto();
    }

    @PostMapping("/get/{id}")
    @Operation(
            description = "Get publisher by id",
            responses = {@ApiResponse(responseCode = "200", description = "Publisher retrieved successfully")}
    )
    public PublisherDto getPublisherById(@PathVariable Long id) {
        return publisherService.findPublisherDtoById(id);
    }

}
