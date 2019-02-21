package com.acroot.iot.home.unit.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.acroot.iot.home.unit.entities.SwitchEntity;

@Repository
@Transactional
public interface SwitchRepository extends JpaRepository<SwitchEntity, Long>{

}