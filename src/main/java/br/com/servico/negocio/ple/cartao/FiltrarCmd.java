package br.com.servico.negocio.ple.cartao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ferramenta.UtilitarioExcel;
import br.com.servico.banco_dados.oracle.ple.dao.cartao.EmissaoDaoCustom;
import br.com.servico.transporte.ple.cartao.EmissaoFiltroDto;
import br.com.servico.transporte.ple.cartao.EmissaoListaDto;

@Service("PleCartaoEmissaoFiltrarCmd")
@Scope("prototype")
public class FiltrarCmd implements Command {

	@Autowired
	private EmissaoDaoCustom dao;

	@Override
	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public boolean execute(Context context) throws Exception {
		List<EmissaoListaDto> result = (List<EmissaoListaDto>) dao.filtrar((EmissaoFiltroDto) context.get("requisicao"));
		
		if (result != null) {
			result = result.stream().filter((r) -> {
				if (r.getRetorno().length == 0) {
					return true;
				} else {
					for (Object retorno : r.getRetorno()) {
						String codigo = ((Integer) ((Map<String, Object>) retorno).get("Codigo")).toString();
						String tipo = (String) ((Map<String, Object>) retorno).get("Tipo");
						if (r.getCodigoTdmax().equals(codigo) && "Estudante".equals(tipo)) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList());
		}
		
		List<String> cabecalho = new ArrayList<>();
		cabecalho.add("Codigo");
		cabecalho.add("Nome");
		List<Map<String, Object>> esbr = new ArrayList<>();
		result.stream().forEach(r -> {
			Map<String, Object> nm = new HashMap<>();
			nm.put("Codigo", r.getCodigoTdmax());
			nm.put("Nome", r.getNomeSolicitante());
			esbr.add(nm);
		});

		UtilitarioExcel.criarArquivoExcelDoMapa(esbr, cabecalho, "c:\\temp\\resultado.xlsx");

		context.put("resposta", null);
		return false;
	}

}
