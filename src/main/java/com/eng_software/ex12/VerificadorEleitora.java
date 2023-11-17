package com.eng_software.ex12;

public class VerificadorEleitora {
    private CartorioEleitoral cartorioEleitoral;
    
    public VerificadorEleitora(CartorioEleitoral cartorioEleitoral) {
    this.cartorioEleitoral = cartorioEleitoral;
    }

    public String consultarSituacao(int idade, String cpf) throws Exception {
    if(idade < 0 || idade > 200) throw new Exception("idade invalida");

    if(cpf == null || cpf.length() != 11) throw new Exception("cpf invalido");

    if(idade < 16) return "nao pode votar";

    String status = cartorioEleitoral.verificar(cpf);
    if(status.equals("nao existe"))
    return "faca um titulo";
    else if(status.equals("pendencia"))
    return "regularize seu titulo";
    else if(status.equals("OK")) {
    String ret = "voto obrigatorio";
    if(idade <= 17 || idade > 70)
    ret = "voto facultativo";

 return ret;
 }
 return "erro desconhecido";
 }
}

