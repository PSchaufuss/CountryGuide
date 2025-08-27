package com.example.countries.model;

public class Country
{
    private String name;
    private String history;
    private String attractions;
    private int population;
    private String generalInfo;
    private String flagUrl;


    public Country(String flagUrl, String generalInfo, int population, String attractions, String history, String name)
    {
        this.flagUrl = flagUrl;
        this.generalInfo = generalInfo;
        this.population = population;
        this.attractions = attractions;
        this.history = history;
        this.name = name;
    }

    public Country()
    {

    }


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getHistory()
    {
        return history;
    }

    public void setHistory(String history)
    {
        this.history = history;
    }

    public String getAttractions()
    {
        return attractions;
    }

    public void setAttractions(String attractions)
    {
        this.attractions = attractions;
    }

    public int getPopulation()
    {
        return population;
    }

    public void setPopulation(int population)
    {
        this.population = population;
    }

    public String getGeneralInfo()
    {
        return generalInfo;
    }

    public void setGeneralInfo(String generalInfo)
    {
        this.generalInfo = generalInfo;
    }

    public String getFlagUrl()
    {
        return flagUrl;
    }

    public void setFlagUrl(String flagUrl) {
        this.flagUrl = flagUrl;
    }

    @Override
    public String toString()
    {
        return "Country{" + "name='" + name + '\'' +
                ", population=" + population +
                ", history='" + history + '\'' +
                ", attractions='" + attractions + '\'' +
                ", generalInfo='" + generalInfo + '\'' +
                ", flagUrl='" + flagUrl + '\'' +
                '}';
    }
}
