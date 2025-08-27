package com.example.countries.service;

import com.example.countries.model.Country;
import com.example.countries.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService
{
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository)
    {
        this.countryRepository = countryRepository;
    }

    public Country addCountry(Country country)
    {
        return countryRepository.addCountry(country);
    }

    public List<Country> getAllCountries()
    {
        return countryRepository.getAllCountries();
    }

    public Country findCountryByName(String name)
    {
        return countryRepository.findCountryByName(name);
    }

    public Country updateCountry(Country newCountry)
    {
        return countryRepository.updateCountry(newCountry);
    }

    public boolean deleteByName(String name)
    {
        return countryRepository.deleteByName(name);
    }




}
