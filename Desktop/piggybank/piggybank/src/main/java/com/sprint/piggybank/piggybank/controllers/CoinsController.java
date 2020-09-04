package com.sprint.piggybank.piggybank.controllers;

import com.sprint.piggybank.piggybank.models.Coins;
import com.sprint.piggybank.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinsController {
    public List<Coins> coinList = new ArrayList<>();
    private String checkPlural(Coins coin) {
        if(coin.getQuantity() > 1){
            return coin.getNameplural();
        } else return coin.getName();
    }

    private float getTotal(){
        float total = 0;

        for(Coins coin: coinList){
            total +=coin.getValue() * coin.getQuantity();
        }
        return total;
    }

    @Autowired
    CoinRepository coinRepository;

    @GetMapping(value = "/total",
            produces = {"application/json"})

    public ResponseEntity<?> listTotal() {
        coinList.clear();
        coinRepository.findAll().iterator().forEachRemaining(coinList::add);

        for (Coins coin : coinList) {
            System.out.println(coin.getQuantity() + " " + checkPlural(coin));
        }

        System.out.print("The piggy bank holds $" + Math.round(getTotal()*100.0)/100.0);

        return new ResponseEntity<>(HttpStatus.OK);
    }



}
