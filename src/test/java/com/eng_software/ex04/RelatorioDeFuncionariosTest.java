package ex04;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class RelatorioDeFuncionariosTest {
    @Test
    void testSemFuncionariosNoBanco(){
        FuncionarioDAO funcDaoMock = mock(FuncionarioDAO.class);
        ReceitaFederal RFMock = mock(ReceitaFederal.class);
        RelatorioDeFuncionarios rel = new RelatorioDeFuncionarios(funcDaoMock);
        rel.setRf(RFMock);

        when(funcDaoMock.getFuncionariosBy("Dev"))
            .thenReturn();

        int resposta = rel.getFuncComCPFBloqueado("Dev");
        assertEquals(0, resposta);
    }

    @Test
    void testDoisFuncionariosTecnicosSemCPFBloqueado(){
        FuncionarioDAO funcDaoMock = mock(FuncionarioDAO.class);
        ReceitaFederal RFMock = mock(ReceitaFederal.class);
        RelatorioDeFuncionarios rel = new RelatorioDeFuncionarios(funcDaoMock);
        rel.setRf(RFMock);

        ArrayList<Funcionario> fakeList = new ArrayList<>();
        fakeList.add(new Funcionario(
                11,
                "Joao",
                "11122233344"
        ));
        fakeList.add(new Funcionario(
                33,
                "Maria",
                "22233344455"
        ));

        when(funcDaoMock.getFuncionariosBy("Tecnico"))
            .thenReturn(fakeList);
        when(RFMock.isCPFBloqueado("11122233344"))
                   .thenReturn(false);
        when(RFMock.isCPFBloqueado("22233344455"))
                .thenReturn(false);
    }

    @Test
    void testUmFuncionarioAnalistaCPFBloqueado(){
        FuncionarioDAO funcDaoMock = mock(FuncionarioDAO.class);
        ReceitaFederal RFMock = mock(ReceitaFederal.class);
        RelatorioDeFuncionarios rel = new RelatorioDeFuncionarios(funcDaoMock);
        rel.setRf(RFMock);

        ArrayList<Funcionario> fakeList = new ArrayList<>();
        fakeList.add(new Funcionario(
                11,
                "Joao",
                "11122233344"
        ));

        when(funcDaoMock.getFuncionariosBy("Analista"))
                .thenReturn(fakeList);
        when(RFMock.isCPFBloqueado("11122233344"))
                .thenReturn(true);
    }

    @Test
    void testDoisFuncionariosGerentesCPFBloquedo(){
        FuncionarioDAO funcDaoMock = mock(FuncionarioDAO.class);
        ReceitaFederal RFMock = mock(ReceitaFederal.class);
        RelatorioDeFuncionarios rel = new RelatorioDeFuncionarios(funcDaoMock);
        rel.setRf(RFMock);

        ArrayList<Funcionario> fakeList = new ArrayList<>();
        fakeList.add(new Funcionario(
                11,
                "Joao",
                "12345678900"
        ));
        fakeList.add(new Funcionario(
                22,
                "Silvia",
                "11122233344"
        ));
        fakeList.add(new Funcionario(
                33,
                "Pedro",
                "65432198723"
        ));
        fakeList.add(new Funcionario(
                44,
                "Judas",
                "09887665499"
        ));

        when(funcDaoMock.getFuncionariosBy("Gerente"))
                .thenReturn(fakeList);
        when(RFMock.isCPFBloqueado("11122233344"))
                .thenReturn(true);
        when(RFMock.isCPFBloqueado("09887665499"))
                .thenReturn(true);
        when(RFMock.isCPFBloqueado("12345678900"))
                .thenReturn(false);
        when(RFMock.isCPFBloqueado("65432198723"))
                .thenReturn(false);
    }

}
