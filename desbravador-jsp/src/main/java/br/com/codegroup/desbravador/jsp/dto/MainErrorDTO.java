package br.com.codegroup.desbravador.jsp.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainErrorDTO implements Serializable {
    private Integer status;
    private String message;

    private List<String> errors = new ArrayList<>();

    public MainErrorDTO(String json) {
        try {
            MainErrorDTO mainError = new ObjectMapper().readValue(json, MainErrorDTO.class);
            if (Objects.nonNull(mainError.getErrors()) && !mainError.getErrors().isEmpty()) {
                this.message = String.join(",", mainError.getErrors());
            } else {
                this.message = mainError.getMessage();
            }
            this.status = mainError.getStatus();
            this.errors = new ArrayList<>();
        } catch(JsonProcessingException ex) {
            this.status = 400;
            this.message = "";
            this.errors = new ArrayList<>();
        }
    }
}
