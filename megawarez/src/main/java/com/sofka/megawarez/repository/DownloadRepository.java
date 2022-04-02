package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Download;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DownloadRepository extends JpaRepository<Download, Integer> {
}