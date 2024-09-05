package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Farm;

/**
 * The type Farm create dto.
 */
public record FarmCreateDto(Long id, String name, Double size) {

  /**
   * Creation farm dto farm.
   *
   * @param farmDto the farm dto
   * @return the farm
   */
  public static Farm creationFarmDto(FarmDto farmDto) {
    return new Farm(farmDto.name(), farmDto.size());
  }

  /**
   * From entity farm create dto.
   *
   * @param farm the farm
   * @return the farm create dto
   */
  public static FarmCreateDto fromEntity(Farm farm) {
    return new FarmCreateDto(farm.getId(), farm.getName(), farm.getSize());
  }
}
