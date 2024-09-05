package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Fertilizer;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FertilizerRepository;
import com.betrybe.agrix.service.exception.CropNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository       the crop repository
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired

  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Find by id crop.
   *
   * @param id the id
   * @return the crop
   * @throws CropNotFoundException the crop not found exception
   */
  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  /**
   * Find by harvest date between list.
   *
   * @param start the start
   * @param end   the end
   * @return the list
   */
  public List<Crop> findByHarvestDateBetween(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }

  /**
   * Association crop and fertilizer string.
   *
   * @param cropId       the crop id
   * @param fertilizerId the fertilizer id
   * @return the string
   * @throws CropNotFoundException       the crop not found exception
   * @throws FertilizerNotFoundException the fertilizer not found exception
   */
  public String associationCropAndFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);
    fertilizer.getCrops().add(crop);
    fertilizerRepository.save(fertilizer);
    return "Fertilizante e plantação associados com sucesso!";
  }
}
