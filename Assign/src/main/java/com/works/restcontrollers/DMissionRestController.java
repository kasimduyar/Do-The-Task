package com.works.restcontrollers;


import com.works.entities.DMission;
import com.works.repositories.DMissionRepository;
import com.works.services.DMissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("mission")
@Validated
public class DMissionRestController {

    final DMissionService dMissionService;

    @PostMapping("msave")
    public DMission save (@Validated @RequestBody DMission dMission) {
        return dMissionService.save(dMission);
    }

    @GetMapping("mlist")
    public List<DMission> findAll() {
        return dMissionService.findAll();
    }

    @PutMapping("update")
    public ResponseEntity<DMission> update(@Valid @RequestBody DMission dMission) {
        return dMissionService.dupdate(dMission);
    }

    @DeleteMapping("delete/{mid}")
    public ResponseEntity delete( @PathVariable Long mid) {
        return dMissionService.deletemission(mid);
    }




}
