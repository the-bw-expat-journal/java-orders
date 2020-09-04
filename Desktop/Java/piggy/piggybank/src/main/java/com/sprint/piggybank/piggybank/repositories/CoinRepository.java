package com.sprint.piggybank.piggybank.repositories;

import com.sprint.piggybank.piggybank.models.Coins;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coins, Long> {
}
