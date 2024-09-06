package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.PersonCreateDto;
import com.betrybe.agrix.controller.dto.PersonDto;
import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Person controller.
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

  private final PersonService personService;

  /**
   * Instantiates a new Person controller.
   *
   * @param personService the person service
   */
  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Create person http entity.
   *
   * @param personCreateDto the person create dto
   * @return the http entity
   */
  @PostMapping
  public HttpEntity<PersonDto> createPerson(@RequestBody PersonCreateDto personCreateDto) {
    Person person = new Person();
    person.setUsername(personCreateDto.username());
    person.setPassword(personCreateDto.password());
    person.setRole(personCreateDto.role());

    Person personCreate = personService.create(person);

    PersonDto personDto = PersonDto.fromEntity(personCreate);

    return ResponseEntity.status(HttpStatus.CREATED).body(personDto);

  }
}
