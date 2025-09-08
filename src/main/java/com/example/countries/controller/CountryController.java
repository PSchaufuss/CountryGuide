package com.example.countries.controller;

import com.example.countries.model.Country;
import com.example.countries.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController
{
    private CountryService countryService;

    public CountryController(CountryService countryService)
    {
        this.countryService = countryService;
    }

    public void test2()
    {

    }


    private boolean doesCountryExist(Country country)
    {
        return (country.getName() != null && countryService.findCountryByName(country.getName()) != null);
    }

    @PostMapping("/add")
    ResponseEntity<Country> addCountry (@RequestBody Country country)
    {
        if (!doesCountryExist(country))
        {
            return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public ResponseEntity<List<Country>> getAllCountries()
    {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<Country> getCountry(@PathVariable String name)
    {
        Country country = countryService.getCountry(name);
        if (country != null)
        {
            return new ResponseEntity<>(country, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ResponseEntity<?> fordi vi både kan returnere et Country eller en String
    @PostMapping("/update")
    public ResponseEntity<?> updateCountry(@RequestBody Country country)
    {
        if(doesCountryExist(country))
        {
            return new ResponseEntity<>(countryService.updateCountry(country), HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Landet findes ikke.");
    }

    @PostMapping("/delete/{name}")
    public ResponseEntity<?> deleteCountry(@PathVariable String name)
    {
        Country deletedCountry = countryService.findCountryByName(name);
        if (deletedCountry == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Landet '" + name + "' kunne ikke findes.");
        }
        countryService.deleteByName(name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Landet '" + name + "' blev slettet.");
    }

}
