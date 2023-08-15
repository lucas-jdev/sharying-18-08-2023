package com.tdd_in_legacy.app.model;

public class UsuarioModel {
    
    private String nome;
    private int idade;
    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && nome.length() > 3){
            this.nome = nome;
        } else throw new IllegalArgumentException("Nome inválido");
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        if (idade > 17) {
            this.idade = idade;
        } else throw new IllegalArgumentException("Idade inválida");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (isCPFParamInvalid(cpf))
            throw new IllegalArgumentException("CPF inválido");

        char dig10, dig11;
        int somaDigitosVezesPeso, resultado, peso;
        final int POSICAO_ZERO_ASCII = 48;
        final int CONSTANTE_DIVISAO = 11;

        somaDigitosVezesPeso = 0;
        peso = 10;
        for (int indice=0; indice<9; indice++) {
            int numero = (cpf.charAt(indice) - POSICAO_ZERO_ASCII);
            somaDigitosVezesPeso += (numero * peso);
            peso -= 1;
        }

        resultado = (somaDigitosVezesPeso % CONSTANTE_DIVISAO);
        if (resultado < 2)
            dig10 = '0';
        else dig10 = (char)(11 - resultado + POSICAO_ZERO_ASCII);

        somaDigitosVezesPeso = 0;
        peso = 11;
        for(int indice=0; indice<10; indice++) {
            int numero = (int)(cpf.charAt(indice) - POSICAO_ZERO_ASCII);
            somaDigitosVezesPeso += (numero * peso);
            peso -= 1;
        }

        resultado = (somaDigitosVezesPeso % CONSTANTE_DIVISAO);
        if (resultado < 2)
                dig11 = '0';
        else dig11 = (char)(11 - resultado + POSICAO_ZERO_ASCII);

        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                this.cpf = cpf;
        else throw new IllegalArgumentException("CPF inválido");
    }

    private boolean isCPFParamInvalid(String valor){
        return  valor == null ||
                valor.matches("00000000000|11111111111|22222222222|33333333333|44444444444|55555555555|66666666666|77777777777|88888888888|99999999999") 
                || (valor.length() != 11);
    }

    
    

}
