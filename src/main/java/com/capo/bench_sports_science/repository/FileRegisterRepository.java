package com.capo.bench_sports_science.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capo.bench_sports_science.models.FileRegisterModel;

@Repository
public interface FileRegisterRepository extends JpaRepository<FileRegisterModel, Long>  {

}
