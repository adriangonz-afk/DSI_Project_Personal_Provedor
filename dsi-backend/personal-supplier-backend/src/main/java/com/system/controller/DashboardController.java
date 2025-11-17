package com.system.controller;

import com.system.dto.DashboardDTO;
import com.system.service.DashboardService;
import com.system.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/estadisticas")
    public ResponseEntity<DashboardDTO> getDashboardStatistics() {
        try {
            log.info("Obteniendo estadísticas del dashboard");
            DashboardDTO dashboardData = dashboardService.getDashboardData();
            return ResponseEntity.ok(dashboardData);
        } catch (ServiceException e) {
            log.error("Error al obtener estadísticas del dashboard", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            log.error("Error inesperado al obtener estadísticas del dashboard", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        try {
            log.info("Verificando estado del servicio dashboard");
            return ResponseEntity.ok("Dashboard service is running");
        } catch (Exception e) {
            log.error("Error en health check del dashboard", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Service unavailable");
        }
    }
}