package org.itsnick78.win_win_travel_tt.repositories;

import org.itsnick78.win_win_travel_tt.models.ProcessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProcessLogRepository extends JpaRepository<ProcessLog, UUID> {
    List<ProcessLog> findAllUsersById(UUID userId);
}
