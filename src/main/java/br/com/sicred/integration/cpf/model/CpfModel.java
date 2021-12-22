package br.com.sicred.integration.cpf.model;

import br.com.sicred.integration.cpf.enums.EnumCpf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CpfModel {
    private EnumCpf status;
}
