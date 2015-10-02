package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;

/**
 * Class for modelling and drawing a Planet.
 */
public class Planet extends Entity{
    private int numberOfCities;
    private City[] cities;
    private MissileBase missileBase;
    private final int SPACE_BETWEEN_CITIES = 30;
    private final int PLANET_HEIGHT = 5;
    private final int cityWidth;
    private final int CITY_HEIGHT = 50;
    private final int MISSILE_BASE_WIDTH = 60;

    public Planet(int numberOfCities){
        this.numberOfCities = numberOfCities;
        cities = new City[numberOfCities];
        cityWidth = (App.WINDOW_WIDTH - 2*SPACE_BETWEEN_CITIES  - MISSILE_BASE_WIDTH) / numberOfCities - SPACE_BETWEEN_CITIES;
        createCities();
        createMissleBase();
    }

    private void createCities(){
        int y = App.WINDOW_HEIGHT - CITY_HEIGHT;
        for(int cityIndex = 0; cityIndex < numberOfCities; cityIndex++){
            int x = SPACE_BETWEEN_CITIES + cityIndex * (SPACE_BETWEEN_CITIES + cityWidth);
            if(cityIndex >= numberOfCities / 2) x += MISSILE_BASE_WIDTH + SPACE_BETWEEN_CITIES; //leave space in middle of cities for missle base
            cities[cityIndex] = new City(x,y, cityWidth, CITY_HEIGHT);
        }
    }

    private void createMissleBase(){
        int height = CITY_HEIGHT /2;
        int citiesOnLeft = numberOfCities / 2;
        int x = citiesOnLeft * (cityWidth + SPACE_BETWEEN_CITIES) + SPACE_BETWEEN_CITIES;
        int y = App.WINDOW_HEIGHT - height;
        missileBase = new MissileBase(x,y, MISSILE_BASE_WIDTH, height);
    }


    public void draw(){
        //draw planet
        app.fill(255);
        app.rect(0, App.WINDOW_HEIGHT - PLANET_HEIGHT, App.WINDOW_WIDTH, PLANET_HEIGHT);

        //draw Missle Base
        missileBase.draw();

        //draw cities
        for(City city : cities) city.draw();
    }

    public int citiesRemaining(){
        int citiesRemaining = cities.length;
        for(City city : cities){
            if(city.isDestroyed()) citiesRemaining--;
        }
        return citiesRemaining;
    }

    public City[] getCities() {
        return cities;
    }

    public MissileBase getMissileBase() {
        return missileBase;
    }

    /**
     * Rebuilds first destroyed city found. If no destroyed cities, do nothing.
     */
    public void rebuildOneCity() {
        for(City city : cities){
            if(city.isDestroyed()){
                city.setDestroyed(false);
                return;
            }
        }
    }

    public int getMaxNumberOfCities() {
        return numberOfCities;
    }
}
