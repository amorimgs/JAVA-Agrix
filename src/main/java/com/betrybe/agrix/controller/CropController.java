package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropDto;
import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.controller.dto.FertilizerDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {


  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Find by id response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("{id}")
  public ResponseEntity<CropDto> findById(@PathVariable Long id) throws CropNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(CropDto.fromEntity(cropService.findById(id)));
  }

  /**
   * Find all response entity.
   *
   * @return the response entity
   */
  @GetMapping()
  @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
  public ResponseEntity<List<CropDto>> findAll() {
    return ResponseEntity.status(HttpStatus.OK)
        .body(cropService.findAll().stream().map(CropDto::fromEntity).toList());

  }

  /**
   * Find by harvest date between response entity.
   *
   * @param start the start
   * @param end   the end
   * @return the response entity
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> findByHarvestDateBetween(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> crops = cropService.findByHarvestDateBetween(start, end);
    return ResponseEntity.status(HttpStatus.OK)
        .body(crops.stream().map(CropDto::fromEntity).toList());
  }

  /**
   * Association crop and fertilizers response entity.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the response entity
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associationCropAndFertilizers(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) throws CropNotFoundException, FertilizerNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cropService.associationCropAndFertilizer(cropId, fertilizerId));
  }

  /**
   * Find all fertilizers response entity.
   *
   * @param id the id
   * @return the response entity
   * @throws CropNotFoundException the crop not found exception
   */
  @GetMapping("/{id}/fertilizers")
  public ResponseEntity<List<FertilizerCreationDto>> findAllFertilizers(@PathVariable Long id)
      throws CropNotFoundException {
    Crop crop = cropService.findById(id);
    List<Fertilizer> fertilizers = crop.getFertilizers();
    return ResponseEntity.status(HttpStatus.OK)
        .body(fertilizers.stream().map(FertilizerCreationDto::fromEntity).toList());
  }
}
