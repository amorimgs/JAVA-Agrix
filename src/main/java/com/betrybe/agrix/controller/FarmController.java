package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropCreateDto;
import com.betrybe.agrix.controller.dto.FarmCreateDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.security.Role;
import com.betrybe.agrix.service.FarmService;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private final FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Created farm http entity.
   *
   * @param farmDto the farm dto
   * @return the http entity
   */
  @PostMapping
  public HttpEntity<FarmCreateDto> createdFarm(@RequestBody FarmDto farmDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(FarmCreateDto.fromEntity(
            farmService.createdFarm(FarmCreateDto.creationFarmDto(farmDto))));
  }

  /**
   * Find all http entity.
   *
   * @return the http entity
   */
  @GetMapping()
  public HttpEntity<List<FarmDto>> findAll() {

    List<FarmDto> allFarm = farmService.findAll().stream().map(FarmDto::fromEntity).toList();

    return ResponseEntity.status(HttpStatus.OK).body(allFarm);
  }

  /**
   * Find by id http entity.
   *
   * @param id the id
   * @return the http entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}")
  public HttpEntity<FarmDto> findById(@PathVariable Long id) throws FarmNotFoundException {

    return ResponseEntity.status(HttpStatus.OK).body(FarmDto.fromEntity(farmService.findById(id)));
  }

  /**
   * Creat crop http entity.
   *
   * @param id      the id
   * @param cropDto the crop dto
   * @return the http entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @PostMapping("{id}/crops")
  public HttpEntity<CropCreateDto> creatCrop(@PathVariable Long id,
      @RequestBody CropCreateDto cropDto)
      throws FarmNotFoundException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(CropCreateDto.fromCropDto(
            farmService.createCrop(id, CropCreateDto.createdCropDtop(cropDto))));
  }

  /**
   * Find all crops http entity.
   *
   * @param id the id
   * @return the http entity
   * @throws FarmNotFoundException the farm not found exception
   */
  @GetMapping("/{id}/crops")
  public HttpEntity<List<CropCreateDto>> findAllCrops(@PathVariable Long id)
      throws FarmNotFoundException {
    List<CropCreateDto> crops = farmService.findAllCrops(id).stream()
        .map(CropCreateDto::fromCropDto)
        .toList();

    return ResponseEntity.status(HttpStatus.OK).body(crops);

  }
}
