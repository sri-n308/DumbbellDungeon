package com.dumbbelldungeon.workouttracker.controller;

import com.dumbbelldungeon.workouttracker.entity.WeightWorkout;
import com.dumbbelldungeon.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WorkoutController implements WebMvcConfigurer {

    @Autowired
    private WorkoutRepository repo;

    @GetMapping({ "/"})
    public String homepage () {
        return "homepage";
    }

    @GetMapping("/addWorkout")
    public ModelAndView addWorkout () {

        ModelAndView mav = new ModelAndView("addWorkoutForm");
        WeightWorkout workout = new WeightWorkout();
        mav.addObject("workout", workout);
        return mav;

    }

    @PostMapping("/saveWorkout")
    public String saveWorkout (@ModelAttribute WeightWorkout workout) {
        //workout.setDateTime(LocalDateTime.now());
        repo.save(workout);
        return "redirect:/";
    }

    @GetMapping({"/list"})
    public ModelAndView getWorkouts() {
        ModelAndView mav = new ModelAndView("viewWorkouts");
        mav.addObject("workout", repo.findAll());
        return mav;
    }

    @GetMapping({"/graphs"})
    public String chartWorkouts(Model model) {
        List<WeightWorkout> workouts = repo.findByTypeAndWeight("Bench Press", 225);
        int[] reps = new int[workouts.size()];
        for (int i = 0; i< workouts.size(); i++){
            reps[i]=workouts.get(i).getReps();
        }
        model.addAttribute("reps", reps);
        return "graph";
        }

    public void addViewController(ViewControllerRegistry registry){
       // registry.addViewController("/graph").setViewName("graph");
    }
}



