package br.com.codegroup.desbravador.jsp.api;

import br.com.codegroup.desbravador.jsp.dto.MainErrorDTO;
import br.com.codegroup.desbravador.jsp.dto.PessoaDTO;
import br.com.codegroup.desbravador.jsp.exception.BunisessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class PessoaRestTemplate {

    private static final String URL_BASE_BACKEND = "http://localhost:8081/api";

    private static final String PATH_PESSOA_URL = "/v1/pessoa";

    private final RestTemplate restTemplate;

    public PessoaDTO cadastrar(PessoaDTO pessoaDTO) {
        String url = URL_BASE_BACKEND + PATH_PESSOA_URL;
        try {
            HttpEntity<Object> httpRequest = new HttpEntity<>(pessoaDTO);
            ResponseEntity<PessoaDTO> response = restTemplate.postForEntity(
                    url,
                    httpRequest,
                    PessoaDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

    public List<PessoaDTO> buscarTodos() {
        String url = URL_BASE_BACKEND + PATH_PESSOA_URL;
        try {
            ResponseEntity<PessoaDTO[]> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    this.getHttpEntity(),
                    PessoaDTO[].class
            );

            return Objects.nonNull(response.getBody()) ? Arrays.asList(response.getBody()) : new ArrayList<>();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }


    public void excluir(Long id) {
        String url = URL_BASE_BACKEND + PATH_PESSOA_URL + "/" + id;
        try {
            restTemplate.exchange(
                    url,
                    HttpMethod.DELETE,
                    this.getHttpEntity(),
                    Void.class
            );
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

    public PessoaDTO consultarPorId(Long id) {
        String url = URL_BASE_BACKEND + PATH_PESSOA_URL + "/" + id;
        try {
            ResponseEntity<PessoaDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    this.getHttpEntity(),
                    PessoaDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

    public PessoaDTO alterar(PessoaDTO pessoaDTO) {
        String url = URL_BASE_BACKEND + PATH_PESSOA_URL + "/" + pessoaDTO.getId();
        HttpEntity<Object> httpRequest = new HttpEntity<>(pessoaDTO);
        try {
            ResponseEntity<PessoaDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.PUT,
                    httpRequest,
                    PessoaDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            MainErrorDTO mainError = new MainErrorDTO(e.getResponseBodyAsString());
            throw new BunisessException(mainError.getMessage());
        }
    }

    private HttpEntity<String> getHttpEntity() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity<>(headers);
    }

}
