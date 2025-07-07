package br.com.golden.raspberry.dto;

import lombok.Data;

@Data
public class ProducerIntervalDTO {
    private String producer;
    private int interval;
    private int previousWin;
    private int followingWin;

    public ProducerIntervalDTO(String producer, int interval, int previousWin, int followingWin) {
        this.producer = producer;
        this.interval = interval;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

}
