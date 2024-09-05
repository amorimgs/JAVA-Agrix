package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Fertilizer creation dto.
 */
public record FertilizerCreationDto(
    Long id,
    String name,
    String brand,
    String composition
) {

  /**
   * From entity fertilizer creation dto.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer creation dto
   */
  public static FertilizerCreationDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerCreationDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
