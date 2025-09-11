package com.example.countries.controller;

import com.example.countries.model.Country;
import com.example.countries.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController
{
    private final CountryService countryService;

    public CountryController(CountryService countryService)
    {
        this.countryService = countryService;
    }

    private boolean doesCountryExist(Country country)
    {
        return country != null
                && country.getName() != null
                && countryService.findCountryByName(country.getName()) != null;
    }

    // Gammel Responseentity formular
    /*ResponseEntity<Country> addCountry (@RequestBody Country country)
    {
        if (!doesCountryExist(country))
        {
            return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }*/
    @GetMapping("/add")
    public String addCountryForm(Model model)
    {
        model.addAttribute("formTitle", "Tilføj land");
        model.addAttribute("isEdit", false);
        model.addAttribute("country", new Country());
        return "country-form";
    }

    // Ny 'Gem fra formular':
    @PostMapping("/save")
    public String saveCountry(@ModelAttribute("country") Country country)
    {
        if (!doesCountryExist(country))
        {
            countryService.addCountry(country);
        }
        return "redirect:/countries";
    }


    /*
    @GetMapping()
    public ResponseEntity<List<Country>> getAllCountries()
    {
        return new ResponseEntity<>(countryService.getAllCountries(), HttpStatus.OK);
    }
     */
    // Fra "ResponseEntity<List<Country>> til String (view-navn) + Model med data
    @GetMapping()
    public String getAllCountries(@RequestParam(value = "q", required = false) String q, Model model)
    {
        List<Country> countryList;

        boolean hasQuery = (q != null && q.trim().length() >= 2);
        if (hasQuery)
        {
            countryList = countryService.searchByName(q);
        }
        else
        {
            countryList = countryService.getAllCountries();
        }

        model.addAttribute("countries", countryList);
        model.addAttribute("q", q == null ? "" : q.trim());
        model.addAttribute("hasQuery", hasQuery);
        model.addAttribute("count", countryList.size());

        return "countries";
    }

    /*
    @GetMapping("/{name}")
    public ResponseEntity<Country> getCountry(@PathVariable String name)
    {
        Country country = countryService.getCountry(name);
        if (country != null)
        {
            return new ResponseEntity<>(country, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     */
    // Fra ResponseEntity til String (view-navn) + Model med data
    @GetMapping("/{name}")
    public String getCountry(@PathVariable String name, Model model)
    {
        Country country = countryService.getCountry(name);
        if (country == null)
        {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "country";
    }

    @GetMapping("/{name}/edit")
    public String editCountryForm(@PathVariable String name, Model model)
    {
        Country country = countryService.getCountry(name);
        if (country == null) return "redirect:/countries";
        model.addAttribute("formTitle", "Opdater land");
        model.addAttribute("isEdit", true);
        model.addAttribute("country", country);
        return "country-form";
    }

   /* // ResponseEntity<?> fordi vi både kan returnere et Country eller en String
    @PostMapping("/update")
    public ResponseEntity<?> updateCountry(@RequestBody Country country)
    {
        if(doesCountryExist(country))
        {
            return new ResponseEntity<>(countryService.updateCountry(country), HttpStatus.ACCEPTED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Landet findes ikke.");
    }*/
    //Fra ResponseEntity og @RequestBody til String (redirect) + @ModelAttribute
    @PostMapping("/update")
   public String updateCountry(@ModelAttribute("country") Country country)
    {
        if (doesCountryExist(country))
        {
            countryService.updateCountry(country);
        }
        return "redirect:/countries";
    }

    /*
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
    }*/
    // Fra ResponseEntity til String(redirect)
    @PostMapping("/{name}/delete")
    public String deleteCountry(@PathVariable String name)
    {
        countryService.deleteByName(name);
        return "redirect:/countries";
    }

}
