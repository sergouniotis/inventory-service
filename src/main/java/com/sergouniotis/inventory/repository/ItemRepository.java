package com.sergouniotis.inventory.repository;

import java.util.UUID;

import com.sergouniotis.inventory.domain.Item;

public class ItemRepository extends AbstractRepository<Item,UUID>{

    protected ItemRepository() {
        super(Item.class);
    }
    
}
