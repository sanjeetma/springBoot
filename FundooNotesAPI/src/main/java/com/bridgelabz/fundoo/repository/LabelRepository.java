package com.bridgelabz.fundoo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoo.model.LabelModel;

public interface LabelRepository extends JpaRepository<LabelModel, Long> {

}
