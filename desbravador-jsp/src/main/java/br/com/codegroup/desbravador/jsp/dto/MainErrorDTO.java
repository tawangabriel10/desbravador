package br.com.codegroup.desbravador.jsp.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MainErrorDTO implements Serializable {
    private Integer status;
    private String message;

    private List<String> errors = new ArrayList<>();

    private Gson gson = new Gson();

    public MainErrorDTO(String json) {
        MainErrorDTO mainError =  gson.fromJson(json, MainErrorDTO.class);
        this.status = mainError.getStatus();
        this.message = mainError.getMessage();
    }
}
