package com.ocenanovivo.oceanovivo;	

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeteccaoService {

    private final DeteccaoRepository deteccaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LocalizacaoRepository localizacaoRepository;
    private final EspecieRepository especieRepository;
    private final OngRepository ongRepository;

    @Autowired
    public DeteccaoService(DeteccaoRepository deteccaoRepository, UsuarioRepository usuarioRepository,
                           LocalizacaoRepository localizacaoRepository, EspecieRepository especieRepository,
                           OngRepository ongRepository) {
        this.deteccaoRepository = deteccaoRepository;
        this.usuarioRepository = usuarioRepository;
        this.localizacaoRepository = localizacaoRepository;
        this.especieRepository = especieRepository;
        this.ongRepository = ongRepository;
    }

    public Deteccao save(DeteccaoDTO deteccaoDTO) {
        Deteccao deteccao = convertToEntity(deteccaoDTO);
        return deteccaoRepository.save(deteccao);
    }

    public Deteccao update(Long id, DeteccaoDTO deteccaoDTO) {
        if (!deteccaoRepository.existsById(id)) {
            throw new IllegalArgumentException("Detecção não encontrada");
        }
        Deteccao deteccao = convertToEntity(deteccaoDTO);
        deteccao.setId(id);
        return deteccaoRepository.save(deteccao);
    }

    public void delete(Long id) {
        deteccaoRepository.deleteById(id);
    }

    public Optional<Deteccao> findById(Long id) {
        return deteccaoRepository.findById(id);
    }

    public List<Deteccao> findAll() {
        return deteccaoRepository.findAll();
    }

    private Deteccao convertToEntity(DeteccaoDTO deteccaoDTO) {
        Deteccao deteccao = new Deteccao();
        deteccao.setUrlImagem(deteccaoDTO.getUrlImagem());
        deteccao.setDataDeteccao(deteccaoDTO.getDataDeteccao());

        Usuario usuario = usuarioRepository.findById(deteccaoDTO.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
        deteccao.setUsuario(usuario);

        Localizacao localizacao = localizacaoRepository.findById(deteccaoDTO.getLocalizacaoId())
                .orElseThrow(() -> new IllegalArgumentException("Localização não encontrada"));
        deteccao.setLocalizacao(localizacao);

        Especie especie = especieRepository.findById(deteccaoDTO.getEspecieId())
                .orElseThrow(() -> new IllegalArgumentException("Espécie não encontrada"));
        deteccao.setEspecie(especie);

        Set<Ong> ongs = deteccaoDTO.getOngIds().stream()
                .map(id -> ongRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("ONG não encontrada: " + id)))
                .collect(Collectors.toSet());
        deteccao.setOngs(ongs);

        return deteccao;
    }
}
