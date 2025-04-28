package com.works.services;


import com.works.entities.Customer;
import com.works.entities.DMission;
import com.works.repositories.DMissionRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DMissionService {

    final DMissionRepository dMissionRepository;
    final HttpServletRequest request;


    public DMission save (DMission dMission) {

        Customer customer = (Customer) request.getSession().getAttribute("user");
        dMission.setCid(customer.getCid());
        dMissionRepository.save(dMission);
        return dMission;
    }

    public List<DMission> findAll() {
        Customer customer = (Customer) request.getSession().getAttribute("user");
        return dMissionRepository.findAllByCid(customer.getCid());
    }


    public ResponseEntity<DMission> dupdate(DMission dmission) {
        if (dmission.getMid() == null || dmission.getMid() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<DMission> optionalMission = dMissionRepository.findById(dmission.getMid());
        if (optionalMission.isPresent()) {
            DMission existingMission = optionalMission.get();

            Customer customer = (Customer) request.getSession().getAttribute("user");


            if (!existingMission.getCid().equals(customer.getCid())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }


            dmission.setCid(customer.getCid());
            dMissionRepository.saveAndFlush(dmission);

            return new ResponseEntity<>(dmission, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }







    public ResponseEntity deletemission(Long pid) {
        ResponseEntity res = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (pid == null || pid < 1) {
            return res;
        }

        Optional<DMission> productOptional = dMissionRepository.findById(pid);
        if (productOptional.isPresent()) {
            DMission dBook = productOptional.get();

            Customer customer = (Customer) request.getSession().getAttribute("user");

            if (dBook.getCid().equals(customer.getCid())) {
                dMissionRepository.deleteById(pid);
                res = new ResponseEntity<>(HttpStatus.OK);
            } else {

                res = new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }

        return res;
    }




}
