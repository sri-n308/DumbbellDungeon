package com.dumbbelldungeon.workouttracker.repository;

import com.dumbbelldungeon.workouttracker.entity.WeightWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutRepository extends JpaRepository<WeightWorkout, Long> {

    List<WeightWorkout> findByTypeLike(String type);
    List<WeightWorkout> findByTypeAndWeight(String type, int Weight);
}

