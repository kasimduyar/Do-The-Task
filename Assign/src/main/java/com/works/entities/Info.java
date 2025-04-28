package com.works.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;

    private String url;
    private String ip;
    private String sessionID;
    private Long time;
    private String agent;
    private String email;

}
