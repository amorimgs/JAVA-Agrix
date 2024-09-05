package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Created fertilizer http entity.
   *
   * @param fertilizerDto the fertilizer dto
   * @return the http entity
   */
  @PostMapping()
  public HttpEntity<FertilizerCreationDto> createdFertilizer(
      @RequestBody FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = fertilizerService.createFertilizer(
        fertilizerDto.toEntity(fertilizerDto));
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FertilizerCreationDto.fromEntity(fertilizer));
  }

  /**
   * Find all http entity.
   *
   * @return the http entity
   */
  @GetMapping()
  public HttpEntity<List<FertilizerCreationDto>> findAll() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    return ResponseEntity.status(HttpStatus.OK)
        .body(fertilizers.stream().map(FertilizerCreationDto::fromEntity).toList());
  }

  /**
   * Find by id http entity.
   *
   * @param id the id
   * @return the http entity
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @GetMapping("/{id}")
  public HttpEntity<FertilizerCreationDto> findById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    return ResponseEntity.status(HttpStatus.OK)
        .body(FertilizerCreationDto.fromEntity(fertilizerService.findById(id)));
  }
}
