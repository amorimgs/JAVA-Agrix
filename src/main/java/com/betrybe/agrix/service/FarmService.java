package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private final FarmRepository farmRepository;
  private final CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Created farm farm.
   *
   * @param farm the farm
   * @return the farm
   */
  public Farm createdFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  /**
   * Find all list.
   *
   * @return the list
   */
  public List<Farm> findAll() {
    return farmRepository.findAll();
  }

  /**
   * Find by id farm.
   *
   * @param id the id
   * @return the farm
   * @throws FarmNotFoundException the farm not found exception
   */
  public Farm findById(Long id) throws FarmNotFoundException {
    return farmRepository.findById(id).orElseThrow(FarmNotFoundException::new);
  }

  /**
   * Create crop crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   * @throws FarmNotFoundException the farm not found exception
   */
  public Crop createCrop(Long id, Crop crop) throws FarmNotFoundException {
    Farm farm = findById(id);
    crop.setFarmId(farm);
    return cropRepository.save(crop);
  }

  /**
   * Find all crops list.
   *
   * @param id the id
   * @return the list
   * @throws FarmNotFoundException the farm not found exception
   */
  public List<Crop> findAllCrops(Long id) throws FarmNotFoundException {
    Farm farm = findById(id);
    return farm.getCrops();
  }
}