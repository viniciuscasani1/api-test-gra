package br.com.golden.raspberry.dto;

import lombok.Data;

import java.util.List;

@Data
public class AwardIntervalResultDTO {
    private List<ProducerIntervalDTO> min;
    private List<ProducerIntervalDTO> max;

    public AwardIntervalResultDTO(List<ProducerIntervalDTO> min, List<ProducerIntervalDTO> max) {
        this.min = min;
        this.max = max;
    }

}
