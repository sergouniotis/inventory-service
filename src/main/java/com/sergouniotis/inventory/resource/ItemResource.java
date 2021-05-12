package com.sergouniotis.inventory.resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sergouniotis.inventory.domain.Item;
import com.sergouniotis.inventory.repository.ItemRepository;

/**
 *
 */
@Path("/items")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    @Inject
    ItemRepository itemRepository;

    @GET
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @POST
    @Transactional
    public Item create(Item item){
        return itemRepository.save(item);
    }

}