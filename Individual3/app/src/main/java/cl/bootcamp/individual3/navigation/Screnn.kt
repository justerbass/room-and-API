package cl.bootcamp.individual3.navigation

sealed class Screnn (val route: String){
    object HomeScrenn: Screnn("home_screnn")
    object DetailsScrenn: Screnn("DetailView/{id}")
}