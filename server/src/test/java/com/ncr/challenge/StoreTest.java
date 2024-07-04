package com.ncr.challenge;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ncr.challenge.entities.Store;
import com.ncr.challenge.repositories.StoreRepository;

@SpringBootTest
public class StoreTest {
  
  @Autowired
  private StoreRepository storeRepository;

  @Test
  void testGetStores() {
    Iterable<Store> stores = storeRepository.findAll();
    assert (6 == ((Collection<Store>) stores).size());
  }
}
