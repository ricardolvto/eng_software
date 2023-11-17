package com.eng_software.ex12;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.Test;

public class VerificadorEleitoralTest {

    @Test
    public void CpfNaoExiste() throws Exception
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("12345678902")).thenReturn("nao existe");

        String resposta = verEleitoral.consultarSituacao(20, "12345678902");
        assertEquals("faca um titulo",resposta );
    }

    @Test
    public void CpfComPendencia() throws Exception
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("11122233344")).thenReturn("pendencia");

        String resposta = verEleitoral.consultarSituacao(20, "11122233344");
        assertEquals("regularize seu titulo",resposta );
    }

    @Test
    public void CpfOK() throws Exception
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("55566677788")).thenReturn("OK");

        String resposta = verEleitoral.consultarSituacao(20, "55566677788");
        assertEquals("voto obrigatorio",resposta );
    }

    @Test
    public void CpfFacultativoNovo() throws Exception
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("45678912302")).thenReturn("OK");

        String resposta = verEleitoral.consultarSituacao(16, "45678912302");
        assertEquals("voto facultativo",resposta );
    }

    @Test
    public void CpfFacultativoVelho() throws Exception
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("75395185203")).thenReturn("OK");

        String resposta = verEleitoral.consultarSituacao(72, "75395185203");
        assertEquals("voto facultativo",resposta );
    }

    @Test
    public void MuitoNovo () throws Exception 
    {
        CartorioEleitoral CartMock = mock(CartorioEleitoral.class);
        VerificadorEleitora verEleitoral = new VerificadorEleitora(CartMock);
        
        when(CartMock.verificar("45678912302")).thenReturn("OK");

        String resposta = verEleitoral.consultarSituacao(15, "45678912302");
        assertEquals("nao pode votar",resposta );
    }


}

