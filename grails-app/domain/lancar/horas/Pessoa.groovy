package lancar.horas

class Pessoa {

    private static final int[] PESOS_CPF = [11, 10, 9, 8, 7, 6, 5, 4, 3, 2];

    static constraints = {
        cpf validator: { isCPFValido(it.replaceAll(/[.-]/, '')) }, matches: /\d{3}\.\d{3}\.\d{3}-\d{2}/
    }

    static mapping = {
        table 'PESSOA'
        id column: 'ID_PESSOA'

        nome column: 'DS_NOME'
        cpf column: 'DS_CPF'
        dataNascimento column: 'DT_NASCIMENTO'
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
        if ((cpf==null) || (cpf.length()!=11)) return false

        Integer digito1 = calcularDigito(cpf.substring(0,9), PESOS_CPF)
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, PESOS_CPF)
        return cpf == (cpf.substring(0,9) + digito1.toString() + digito2.toString())
    }

    String nome
    String cpf
    Date dataNascimento

    String toString() {
        return "$nome - $cpf"
    }
}
