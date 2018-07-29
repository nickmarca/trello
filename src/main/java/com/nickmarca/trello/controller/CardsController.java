package com.nickmarca.trello.controller;

import com.nickmarca.trello.model.Card;
import com.nickmarca.trello.repository.CardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class CardsController {

    @Autowired
    private CardRepository cardRepository;

    @RequestMapping(value = "cards", method = RequestMethod.GET)
    public List<Card> list() {
        return cardRepository.findAll();
    }

    @RequestMapping(value = "cards/{id}", method = RequestMethod.GET)
    public Card get(@PathVariable Long id) {
        return cardRepository.getOne(id);
    }

    @RequestMapping(value = "cards/{id}", method = RequestMethod.PUT)
    public Card update(@PathVariable Long id, @RequestBody Card card) {
        Card existingCard = cardRepository.getOne(id);
        BeanUtils.copyProperties(card, existingCard);
        return cardRepository.saveAndFlush(card);
    }

    @RequestMapping(value = "cards", method = RequestMethod.POST)
    public Card create(@RequestBody Card card) {
        return cardRepository.saveAndFlush(card);
    }

    @RequestMapping(value = "cards/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        cardRepository.deleteById(id);
    }
}
