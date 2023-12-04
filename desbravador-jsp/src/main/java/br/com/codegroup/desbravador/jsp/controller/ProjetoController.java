package br.com.codegroup.desbravador.jsp.controller;

import br.com.codegroup.desbravador.jsp.dto.ProjetoDTO;
import br.com.codegroup.desbravador.jsp.dto.VincularMembroDTO;
import br.com.codegroup.desbravador.jsp.enumeration.ProjetoRiscoEnum;
import br.com.codegroup.desbravador.jsp.enumeration.ProjetoStatusEnum;
import br.com.codegroup.desbravador.jsp.exception.BunisessException;
import br.com.codegroup.desbravador.jsp.service.ProjetoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@AllArgsConstructor
@RequestMapping("/projeto")
public class ProjetoController {

    private static final String VIEW_CADASTRAR_PROJETO = "cadastrar-projeto";
    private static final String VIEW_LISTAR_PROJETOS = "listar-projetos";
    private static final String VIEW_VINCULAR_PROJETO = "vincular-pessoa-projeto";

    private final ProjetoService projetoService;

    @GetMapping("/listar")
    public String viewListar(Model model) {
        model.addAttribute("listaProjetos", projetoService.buscarTodos());
        return VIEW_LISTAR_PROJETOS;
    }

    @GetMapping("/cadastrar")
    public String viewCadastrar(Model model) {
        model.addAttribute("projeto", new ProjetoDTO());
        model.addAttribute("path_url_projeto", "/desbravador/projeto/cadastrar");
        model.addAttribute("listaStatus", ProjetoStatusEnum.getMap());
        model.addAttribute("listaRisco", ProjetoRiscoEnum.getMap());
        model.addAttribute("listaPessoas", projetoService.getMapPessoas());
        return VIEW_CADASTRAR_PROJETO;
    }

    @PostMapping("/cadastrar")
    public RedirectView cadastrarProjeto(@ModelAttribute("projeto") ProjetoDTO projetoDTO, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            final RedirectView redirectView = new RedirectView("/projeto/listar", true);
            final ProjetoDTO projetoSalvo = projetoService.cadastrar(projetoDTO);
            redirectAttributes.addFlashAttribute("projetoSalvo", projetoSalvo);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Projeto cadastrado com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/cadastrar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            model.addAttribute("projeto", projetoDTO);
            return redirectView;
        }
    }

    @GetMapping(value = "/excluir/{id}")
    public RedirectView excluirProjeto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projetoService.excluir(id);

            final RedirectView redirectView = new RedirectView("/projeto/listar", true);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Projeto excluido com sucesso");
            return redirectView;

        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/listar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            return redirectView;
        }
    }

    @GetMapping(value = "/alterar/{id}")
    public String viewAlterar(@PathVariable Long id, ModelMap model) {
        final ProjetoDTO projetoDTO = projetoService.consultarPorId(id);
        model.addAttribute("projeto", projetoDTO);
        model.addAttribute("path_url_projeto", "/desbravador/projeto/alterar");
        model.addAttribute("listaStatus", ProjetoStatusEnum.getMap());
        model.addAttribute("listaRisco", ProjetoRiscoEnum.getMap());
        model.addAttribute("listaPessoas", projetoService.getMapPessoas());
        return VIEW_CADASTRAR_PROJETO;
    }

    @PostMapping(value = "/alterar")
    public RedirectView alterar(@Valid ProjetoDTO projetoDTO, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            if (result.hasErrors()) {
                return new RedirectView("/projeto/cadastrar", true);
            }

            final RedirectView redirectView = new RedirectView("/projeto/listar", true);
            final ProjetoDTO projetoSalvo = projetoService.alterar(projetoDTO);
            redirectAttributes.addFlashAttribute("projetoSalvo", projetoSalvo);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Projeto alterado com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/cadastrar", true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            model.addAttribute("projeto", projetoDTO);
            return redirectView;
        }
    }

    @GetMapping(value = "/vincular/{id}")
    public String viewVincular(@PathVariable Long id, ModelMap model) {
        final ProjetoDTO projetoDTO = projetoService.consultarPorId(id);
        model.addAttribute("projeto", projetoDTO);
        model.addAttribute("vincular", new VincularMembroDTO());
        model.addAttribute("listaPessoas", projetoService.getMapPessoas());
        return VIEW_VINCULAR_PROJETO;
    }

    @PostMapping(value = "/vincular")
    public RedirectView vincular(@Valid VincularMembroDTO vincularMembroDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return new RedirectView("/projeto/vincular", true);
            }

            final RedirectView redirectView = new RedirectView("/projeto/vincular/" + vincularMembroDTO.getIdProjeto(), true);
            projetoService.vincular(vincularMembroDTO);
            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Pessoa vinculada com sucesso");
            return redirectView;
        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/vincular/" + vincularMembroDTO.getIdProjeto(), true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            return redirectView;
        }
    }

    @GetMapping(value = "/desvincular/{idProjeto}/{idPessoa}")
    public RedirectView desvincular(@PathVariable("idProjeto") Long idProjeto, @PathVariable("idPessoa") Long idPessoa, RedirectAttributes redirectAttributes) {
        try {
            final VincularMembroDTO vincularMembroDTO = new VincularMembroDTO();
            vincularMembroDTO.setIdProjeto(idProjeto);
            vincularMembroDTO.setIdPessoa(idPessoa);
            projetoService.desvincular(vincularMembroDTO);

            final RedirectView redirectView = new RedirectView("/projeto/vincular/" + idProjeto, true);

            redirectAttributes.addFlashAttribute("isSuccess", true);
            redirectAttributes.addFlashAttribute("messageSuccess", "Pessoa desvinculada com sucesso");
            return redirectView;

        } catch(BunisessException ex) {
            final RedirectView redirectView = new RedirectView("/projeto/vincular/" + idProjeto, true);
            redirectAttributes.addFlashAttribute("isFail", true);
            redirectAttributes.addFlashAttribute("messageFail", ex.getMessage());
            return redirectView;
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }
}
