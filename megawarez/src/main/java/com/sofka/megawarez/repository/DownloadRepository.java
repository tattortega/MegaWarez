package com.sofka.megawarez.repository;

import com.sofka.megawarez.domain.Download;
import org.springframework.data.repository.CrudRepository;

public interface DownloadRepository extends CrudRepository<Download, Integer> {
}