package br.com.alura.leilao.ui.activity;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import br.com.alura.leilao.BaseTesteIntegracao;
import br.com.alura.leilao.R;
import br.com.alura.leilao.formatter.FormatadorDeMoeda;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

public class LancesLeilaoTelaTest extends BaseTesteIntegracao {

    @Rule
    public ActivityTestRule<ListaLeilaoActivity> mainActivity =
            new ActivityTestRule<>(ListaLeilaoActivity.class, true, false);

    @Before
    public void setup() throws IOException {
        limpaBancoDeDadosDaApi();
        limpaBancoDeDadosInterno();
    }

    @Test
    public void deve_AtualizarLancesDoLeilao_QuandoReceberUmLance() throws IOException {
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        mainActivity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Usuários não encontrados"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));
        onView(allOf(withText("Não existe usuários cadastrados! Cadastre um usuário para propor o lance."),
                withId(android.R.id.message)))
                .check(matches(isDisplayed()));

        onView(allOf(withText("Cadastrar usuário"), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.lista_usuario_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.form_usuario_nome_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("Alex"),
                        closeSoftKeyboard());

        onView(allOf(withId(android.R.id.button1),
                withText("Adicionar"),
                isDisplayed()))
                .perform(scrollTo(), click());

        pressBack();

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Novo lance"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("200"),
                        closeSoftKeyboard());

        onView(allOf(withId(R.id.form_lance_usuario), isDisplayed()))
                .perform(click());
        onData(is(new Usuario(1, "Alex")))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withText("Propor"), isDisplayed()))
                .perform(click());

        FormatadorDeMoeda formatador = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance))
                .check(matches(allOf(withText(formatador.formata(200)),
                        isDisplayed())));
        onView(withId(R.id.lances_leilao_menor_lance))
                .check(matches(allOf(withText(formatador.formata(200)),
                        isDisplayed())));
        onView(withId(R.id.lances_leilao_maiores_lances))
                .check(matches(allOf(withText("200.0 - (1) Alex\n"),
                        isDisplayed())));
    }

    @Test
    public void deve_AtualizarLancesDoLeilao_QuandoReceberTresLances() throws IOException {
        tentaSalvarLeilaoNaApi(new Leilao("Carro"));

        mainActivity.launchActivity(new Intent());

        onView(withId(R.id.lista_leilao_recyclerview))
                .perform(actionOnItemAtPosition(0, click()));

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Usuários não encontrados"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));
        onView(allOf(withText("Não existe usuários cadastrados! Cadastre um usuário para propor o lance."),
                withId(android.R.id.message)))
                .check(matches(isDisplayed()));

        onView(allOf(withText("Cadastrar usuário"), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.lista_usuario_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.form_usuario_nome_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("Alex"),
                        closeSoftKeyboard());

        onView(allOf(withId(android.R.id.button1),
                withText("Adicionar"),
                isDisplayed()))
                .perform(scrollTo(), click());

        onView(allOf(withId(R.id.lista_usuario_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.form_usuario_nome_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("Fran"),
                        closeSoftKeyboard());

        onView(allOf(withId(android.R.id.button1),
                withText("Adicionar"),
                isDisplayed()))
                .perform(scrollTo(), click());

        pressBack();

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Novo lance"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("200"),
                        closeSoftKeyboard());

        onView(allOf(withId(R.id.form_lance_usuario), isDisplayed()))
                .perform(click());
        onData(is(new Usuario(1, "Alex")))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withText("Propor"), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Novo lance"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("300"),
                        closeSoftKeyboard());

        onView(allOf(withId(R.id.form_lance_usuario), isDisplayed()))
                .perform(click());
        onData(is(new Usuario(2, "Fran")))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withText("Propor"), isDisplayed()))
                .perform(click());

        onView(allOf(withId(R.id.lances_leilao_fab_adiciona),
                isDisplayed()))
                .perform(click());

        onView(allOf(withText("Novo lance"),
                withId(R.id.alertTitle)))
                .check(matches(isDisplayed()));

        onView(allOf(withId(R.id.form_lance_valor_edittext),
                isDisplayed()))
                .perform(click(),
                        typeText("400"),
                        closeSoftKeyboard());

        onView(allOf(withId(R.id.form_lance_usuario), isDisplayed()))
                .perform(click());
        onData(is(new Usuario(1, "Alex")))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withText("Propor"), isDisplayed()))
                .perform(click());

        FormatadorDeMoeda formatador = new FormatadorDeMoeda();
        onView(withId(R.id.lances_leilao_maior_lance))
                .check(matches(allOf(withText(formatador.formata(400)),
                        isDisplayed())));
        onView(withId(R.id.lances_leilao_menor_lance))
                .check(matches(allOf(withText(formatador.formata(200)),
                        isDisplayed())));
        onView(withId(R.id.lances_leilao_maiores_lances))
                .check(matches(allOf(withText("400.0 - (1) Alex\n" +
                                "300.0 - (2) Fran\n" +
                                "200.0 - (1) Alex\n"),
                        isDisplayed())));
    }

    @After
    public void teardown() throws IOException {
        limpaBancoDeDadosDaApi();
        limpaBancoDeDadosInterno();
    }

}
