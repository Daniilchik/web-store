package org.mdasolutions.web.Repos;

import org.mdasolutions.web.Entities.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepo extends JpaRepository<Services, Long> {
}
