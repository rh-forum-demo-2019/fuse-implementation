package org.mycompany.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.example.beer.dto.Beer;


public class BeerService {

    private List<Beer> mBeerList;

    public BeerService() {
        mBeerList = new LinkedList<Beer>();
        Beer beer = new Beer();
        beer.setCountry("DK");
        beer.setName("1000 IBU - Mikkeller");
        beer.setRating(new BigDecimal(5));
        beer.setStatus("Available");
        beer.setType("IPA");
        mBeerList.add(beer);
    }
    public List<Beer> getBeers() {
        return mBeerList;
    }
    public Beer getBeerByName(String name) {
        return mBeerList.stream()
            .filter(beer -> beer.getName().startsWith(name))
            .findFirst()
            .orElse(null);
    }
}
