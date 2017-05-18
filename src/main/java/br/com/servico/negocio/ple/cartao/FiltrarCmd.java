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

	int contador;

	@Override
	@SuppressWarnings({ "unchecked" })
	@Transactional(readOnly = true)
	public boolean execute(Context context) throws Exception {
		// retorno bruto, ou seja, retorno dos dados do banco de dados filtrado
		// somente pelo perído informado
		List<EmissaoListaDto> result = (List<EmissaoListaDto>) dao
				.filtrar((EmissaoFiltroDto) context.get("requisicao"));

		contador = 0;
		if (result != null) {
			result = result.stream().filter((r) -> {
				// condição 1 - retorno vazio é válido - indica que o cpf não
				// possui nenhum cartão da DFTrans
				if (r.getRetorno().length == 0) {
					return true;
					//return false;
				} else {
					boolean encontrou = false;
					// percorrer os registros do JSON
					for (Object retorno : r.getRetorno()) {
						String cartao = ((Map<String, Object>) retorno).get("Cartao").toString();
						String codigo = ((Map<String, Object>) retorno).get("Codigo").toString();
						String tipo = ((Map<String, Object>) retorno).get("Tipo").toString();

						// condição 2 - código TDMax igual, tipo Estudante e
						// Cartão igual a 0
						if (r.getCodigoTdmax().equals(codigo) && "Estudante".equals(tipo) && "0".equals(cartao)) {
							return true;
//							return false;
						} else if (r.getCodigoTdmax().equals(codigo)) {
							encontrou = true;
						}
					}
					// condição 3 - nenhum dos itens retornados no JSON possui o
					// código TDMax do registro principal
					if (!encontrou) {
						contador++;
						return true;
					}
				}
				// nenhuma condição satisfeita, logo o registro não é apto
				return false;
			}).collect(Collectors.toList());
		}

		// nova regra
		System.out.format("Total nova regra [%d]/n", contador);

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
