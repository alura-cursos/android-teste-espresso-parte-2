package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.BaseTesteIntegracao;
import br.com.alura.leilao.R;
import br.com.alura.leilao.model.Leilao;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class LancesLeilaoTelaTest extends BaseTesteIntegracao {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> mainActivity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
    }

    @Test
    public void deve_AtualizarLancesDoLeilao_QuandoReceberUmLance() throws IOException {
//        Salvar leilão na API
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

//        Inicializar a main Activity
        mainActivity.launchActivity(new Intent());

//        Clica no leilão
        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(actionOnItemAtPosition(0, click()));

//        Clica no fab da tela de lances do leilão
//
//        Verifica se aparece dialog de aviso por não ter usuáro com títilo e mensagem esperada
//
//        Clica no botão "Cadastrar Usuário"
//
//        Clica no fab tela de lista de usuários
//
//        Clica no EditText e preenche com o nome do usuário
//
//        Clica em Adicionar
//
//        Clica no back do Android
//
//        Clica no fab lances do leilão
//
//        Verifica visibilidade do dialog com o título esperado
//
//        Clica no edittext de valor e preenche
//
//        Seleciona o usuário
//
//        Clica no botão "Propor"
//
//        Fazer assertion para as views de maior e menor lance, e também, para os maiores lances
    }

    @After
    public void teardown() throws IOException {
        limpaBancoDeDadosDaApi();
    }

}
