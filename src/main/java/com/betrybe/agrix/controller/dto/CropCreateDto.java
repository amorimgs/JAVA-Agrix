package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Crop;
import java.time.LocalDate;

/**
 * The type Crop create dto.
 */
public record CropCreateDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                            LocalDate harvestDate, Long farmId) {

  /**
   * Created crop dtop crop.
   *
   * @param cropDto the crop dto
   * @return the crop
   */
  public static Crop createdCropDtop(CropCreateDto cropDto) {
    Crop crop = new Crop();
    crop.setName(cropDto.name());
    crop.setPlantedArea(cropDto.plantedArea());
    crop.setPlantedDate(cropDto.plantedDate());
    crop.setHarvestDate(cropDto.harvestDate());
    return crop;

  }

  /**
   * From crop dto crop create dto.
   *
   * @param crop the crop
   * @return the crop create dto
   */
  public static CropCreateDto fromCropDto(Crop crop) {
    return new CropCreateDto(crop.getId(), crop.getName(), crop.getPlantedArea(),
        crop.getPlantedDate(), crop.getHarvestDate(), crop.getFarmId().getId());
  }
}
