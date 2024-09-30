package travelagency.travelagency.controller;

import travelagency.travelagency.model.Trip;
import travelagency.travelagency.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;


@Controller
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @GetMapping("/home")
    public String viewHomePage(Model model) {
        model.addAttribute("Trip", new Trip());
        List<Trip> trips = tripRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        for (Trip trip : trips) {
            trip.setFormattedDateFrom(trip.getDateFrom().format(formatter));
            trip.setFormattedDateTo(trip.getDateTo().format(formatter));
        }
        model.addAttribute("trips", trips);

        return "home";
    }

    @PostMapping("/trips")
    public String addTrip(@ModelAttribute Trip trip) {
        tripRepository.save(trip);
        return "redirect:/home";
    }

    @PostMapping("/edit")
    public String updateTrip(@ModelAttribute Trip trip) {
        Trip existingTrip = tripRepository.findById(trip.getId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        existingTrip.setPlaceName(trip.getPlaceName());
        existingTrip.setDescription(trip.getDescription());
        existingTrip.setDateFrom(trip.getDateFrom());
        existingTrip.setDateTo(trip.getDateTo());
        existingTrip.setImageUrl(trip.getImageUrl());
        existingTrip.setPrice(trip.getPrice());

        tripRepository.save(existingTrip);
        return "redirect:/home";
    }


    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable("id") Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trip ID:" + id));
        tripRepository.delete(trip);
        return "redirect:/home";
    }



}
