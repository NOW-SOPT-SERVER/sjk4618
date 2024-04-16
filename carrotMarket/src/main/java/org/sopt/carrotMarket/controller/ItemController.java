package org.sopt.carrotMarket.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.carrotMarket.service.ItemService;
import org.sopt.carrotMarket.service.dto.RegisterItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity registerItem(@RequestBody RegisterItemDTO registerItemDTO) {
        return ResponseEntity.created(URI.create(itemService.registerItem(registerItemDTO)))
                .build();
    }
}
