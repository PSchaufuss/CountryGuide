package com.example.countries.repository;

import com.example.countries.model.Country;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryRepository
{
    private List<Country> countries;

    public CountryRepository()
    {
        countries = new ArrayList<>();
        countries.add(new Country("Danmark"));
        countries.add(new Country("Sverige"));
        countries.add(new Country("Norge"));
        countries.add(new Country("Finland"));
        countries.add(new Country("Grønland"));
    }

public Country addCountry(Country country)
{
    if (country == null || country.getName() == null || country.getName().isBlank())
    {
        return null;
    }
    if (country.getHistory() == null)     country.setHistory("");
    if (country.getAttractions() == null) country.setAttractions("");
    if (country.getGeneralInfo() == null) country.setGeneralInfo("");
    if (country.getFlagUrl() == null)     country.setFlagUrl("");
    if (country.getPopulation() == 0) country.setPopulation(0);

    countries.add(country);
    return country;
}

public Country getCountry(String name)
{
    return findCountryByName(name);
}

public List<Country> getAllCountries()
{
    return countries;
}

public Country findCountryByName(String name)
{
    for (Country country : countries)
    {
        if (country.getName().equalsIgnoreCase(name)) return country;
    }
    return null;
}

public Country updateCountry(Country newCountry)
{
    // Sætter parameteret newCountry til det nye navn for currentCountry, som er et eksisterende objekt i listen.
    Country currentCountry = findCountryByName(newCountry.getName());

    if (currentCountry == null) return null;
    if (newCountry.getGeneralInfo() != null) currentCountry.setGeneralInfo(newCountry.getGeneralInfo());
    if (newCountry.getHistory() != null) currentCountry.setHistory(newCountry.getHistory());
    if (newCountry.getAttractions() != null) currentCountry.setAttractions(newCountry.getAttractions());
    if (newCountry.getFlagUrl() != null) currentCountry.setFlagUrl(newCountry.getFlagUrl());
    if (newCountry.getPopulation() != 0) currentCountry.setPopulation(newCountry.getPopulation());

    return currentCountry;
}

public boolean deleteByName(String name)
{
    Country country = findCountryByName(name);
    return country != null && countries.remove(country);
}

}
