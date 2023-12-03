package br.com.codegroup.desbravador.jsp.api;

import br.com.codegroup.desbravador.jsp.dto.MainErrorDTO;
import br.com.codegroup.desbravador.jsp.dto.VincularMembroDTO;
import br.com.codegroup.desbravador.jsp.exception.BunisessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class MembrosRestTemplate {

    private static final String URL_BASE_BACKEND = "http://localhost:8081/api";

    private static final String PATH_MEMBROS_URL = "/v1/membros";

    private final RestTemplate restTemplate;

    public void vincular(VincularMembroDTO vincularMembroDTO) {
        String url = URL_BASE_BACKEND + PATH_MEMBROS_URL + "/vincular";
        try {
            HttpEntity<Object> httpRequest = new HttpEntity<>(vincularMembroDTO);
            restTemplate.postForEntity(
                    url,
                    httpRequest,
                    Void.class
            );
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

    public void desvincular(VincularMembroDTO vincularMembroDTO) {
        String url = URL_BASE_BACKEND + PATH_MEMBROS_URL + "/desvincular";
        try {
            HttpEntity<Object> httpRequest = new HttpEntity<>(vincularMembroDTO);
            restTemplate.postForEntity(
                    url,
                    httpRequest,
                    Void.class
            );
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

}
