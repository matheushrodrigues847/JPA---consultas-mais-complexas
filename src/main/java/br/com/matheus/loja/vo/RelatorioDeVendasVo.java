package br.com.matheus.loja.vo;

import java.time.LocalDateTime;

public class RelatorioDeVendasVo {
    //objeto somente com valores
    // n e uma entidade

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDateTime dataDaUltimaVenda;

    public RelatorioDeVendasVo(String nomeProduto, Long quantidadeVendida, LocalDateTime dataDaUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataDaUltimaVenda = dataDaUltimaVenda;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDateTime getDataDaUltimaVenda() {
        return dataDaUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioDeVendasVo{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", quantidadeVendida=" + quantidadeVendida +
                ", dataDaUltimaVenda=" + dataDaUltimaVenda +
                '}';
    }
}
