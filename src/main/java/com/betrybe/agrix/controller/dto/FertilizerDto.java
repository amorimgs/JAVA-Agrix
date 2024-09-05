package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Fertilizer;

/**
 * The type Fertilizer dto.
 */
public record FertilizerDto(String name, String brand, String composition) {

  /**
   * To entity fertilizer.
   *
   * @param fertilizerDto the fertilizer dto
   * @return the fertilizer
   */
  public Fertilizer toEntity(FertilizerDto fertilizerDto) {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(fertilizerDto.name());
    fertilizer.setBrand(fertilizerDto.brand());
    fertilizer.setComposition(fertilizerDto.composition());
    return fertilizer;
  }

  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(fertilizer.getName(), fertilizer.getBrand(),
        fertilizer.getComposition());
  }
}
