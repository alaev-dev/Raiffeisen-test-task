package alaev.dev.raiffeisentesttask.controller;

import alaev.dev.raiffeisentesttask.controller.dto.SockDto;
import alaev.dev.raiffeisentesttask.exception.InvalidCottonPart;
import alaev.dev.raiffeisentesttask.exception.InvalidQuantity;
import alaev.dev.raiffeisentesttask.service.SockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final SockService service;

  @Autowired
  public Controller(SockService service) {
    this.service = service;
  }

  @PostMapping("/api/socks/income")
  public ResponseEntity<String> registerArrivalSocks(@RequestBody SockDto sockDto) {
    checkingInput(sockDto.cottonPart, sockDto.quantity);

    service.addSock(sockDto.color, sockDto.cottonPart, sockDto.quantity);
    return ResponseEntity.status(200).build();
  }

  private void checkingInput(Integer cottonPart, Integer quantity) {
    if (cottonPart < 0 || cottonPart > 100) {
      throw new InvalidCottonPart(String.valueOf(cottonPart));
    }

    if (quantity < 0) {
      throw new InvalidQuantity(String.valueOf(quantity));
    }
  }

  @GetMapping
  public ResponseEntity<String> getAllSocks(@RequestParam("color") String color,
                                            @RequestParam("cottonPart") Integer cottonPart,
                                            @RequestParam("quantity") Integer quantity) {
    return ResponseEntity.ok().build();
  }
}
