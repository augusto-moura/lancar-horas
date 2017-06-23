package zg.augusto

import groovy.transform.CompileStatic
import zg.augusto.dominio.enums.RolesFuncionario

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

class Usuario extends SecUser {

    private static final int[] PESOS_CPF = [11, 10, 9, 8, 7, 6, 5, 4, 3, 2]
    private static final DecimalFormat FORMATO_SALARIO = new DecimalFormat('#,##0.00', DecimalFormatSymbols.getInstance(new Locale('pt', 'BR')))

    static hasMany = [registros: Registro]

    static constraints = {
        cpf matches: /\d{3}\.\d{3}\.\d{3}-\d{2}/, validator: {
            return isCPFValido(it.replaceAll(/[.-]/, ''))
        }
    }

    static mapping = {
        table 'USUARIOS'
        version false

        nome blank: false
        cpf blank: false
        salario nullable: false
        registros lazy: false
    }

    String nome
    String cpf
    Date dataNascimento
    BigDecimal salario

    String getSalarioFormatado() {
        return FORMATO_SALARIO.format(salario)
    }

    @Override
    String toString() {
        return "$nome - $cpf"
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0
        int digito

        for (int indice = str.length() - 1; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1))
            soma += digito * peso[peso.length - str.length() + indice]
        }

        soma = 11 - soma % 11
        return soma > 9 ? 0 : soma
    }

    private static boolean isCPFValido(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            return false
        }

        Integer digito1 = calcularDigito(cpf.substring(0,9), PESOS_CPF)
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, PESOS_CPF)

        return cpf == (cpf.substring(0,9) + digito1.toString() + digito2.toString())
    }

}
